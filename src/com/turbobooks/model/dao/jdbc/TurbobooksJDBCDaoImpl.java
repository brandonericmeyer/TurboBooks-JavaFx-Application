package com.turbobooks.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.turbobooks.model.dao.ITurbobooksDao;
import com.turbobooks.model.domain.Book;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.exception.ItemException;
import com.turbobooks.model.services.manager.SAXPropertyManager;

public class TurbobooksJDBCDaoImpl implements ITurbobooksDao {

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");
	ConcurrentHashMap<String, Item> itemMap = new ConcurrentHashMap<String, Item>();

	private Connection fetchConnection() {
		log.info("Fetching Database Connection");

		Connection conn = null;

		String url = SAXPropertyManager.getPropertyValue("jdbc.url");
		String userid = SAXPropertyManager.getPropertyValue("jdbc.user");
		String password = SAXPropertyManager.getPropertyValue("jdbc.password");

		// load and register JDBC driver then connect to database
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(url, userid, password);
		} catch (SQLException e) {
			log.error("Could not load and register JDBC driver or connect to database.");
			log.error(e.getClass() + ": " + e.getMessage(), e);
		}
		return conn;
	}

	private ConcurrentHashMap<String, Item> buildRetrievedItems(final ResultSet resultSet) {
		log.info("Inside buildRetrievedItems");
		ConcurrentHashMap<String, Item> itemMap = null;

		try {
			if (resultSet.isBeforeFirst()) {
				itemMap = new ConcurrentHashMap<String, Item>();
			}

			while (resultSet.next()) {
				Item retrievedItem = new Book(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getBoolean(8));

				itemMap.put(retrievedItem.getUuid(), retrievedItem);

			}
		} catch (SQLException e) {
			log.error(e.getClass() + ": " + e.getMessage(), e);
		}
		return itemMap;
	}

	@Override
	/**
	 * Retrieves item map from storage
	 * 
	 * @return
	 * @throws ItemException
	 */
	public ConcurrentHashMap<String, Item> retrieveItems() throws ItemException {
		ConcurrentHashMap<String, Item> itemMap = new ConcurrentHashMap<String, Item>();

		ResultSet rset = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = fetchConnection();

			if (conn != null) {
				stmt = conn.createStatement();

				String selectQuery = "SELECT * from Book;";

				rset = stmt.executeQuery(selectQuery);

				itemMap = buildRetrievedItems(rset);
			}
		} catch (SQLException e) {
			log.error(e.getClass() + ": " + e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error(e.getClass() + ": " + e.getMessage(), e);
			}
		}

		return itemMap;
	}

	/**
	 * Removes item from database
	 * 
	 * @param uuid
	 * @throws ItemException
	 */
	@Override
	public boolean removeItemFromStorage(final String uuid) throws ItemException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = fetchConnection();

			if (conn != null) {
				stmt = conn.createStatement();

				String deleteUpdate = "DELETE FROM Book WHERE id='" + uuid + "';";

				stmt.executeUpdate(deleteUpdate);

			}
		} catch (SQLException e) {
			log.error(e.getClass() + ": " + e.getMessage(), e);
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error(e.getClass() + ": " + e.getMessage(), e);
			}
		}

		log.info("\nItem was sucessfully removed!");

		return true;
	}

	/**
	 * Stores item map in file
	 * 
	 * @param itemMap
	 * @throws ItemException
	 */
	@Override
	public boolean storeItems(final ConcurrentHashMap<String, Item> itemMap) throws ItemException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = fetchConnection();

			if (conn != null) {
				stmt = conn.createStatement();

				for (Item item : itemMap.values()) {
					Book book = (Book) item;
					String catalog = ""; // book.getCatalog().getName();
					String isbn = book.getIsbn();
					String firstname = book.getFirstName();
					String lastname = book.getLastName();
					String title = "";// book.getTitle();
					String id = book.getUuid();// book.getUuid();
					String membernumber = "";// book.getMemberNumber();
					boolean status = book.isAvailable();

					String insertQuery = "REPLACE INTO Book (catalog, isbn, firstname, lastname, title, id, membernumber, status)\r\n"
							+ "VALUES ('" + catalog + "','" + isbn + "','" + firstname + "','" + lastname + "','"
							+ title + "','" + id + "', '" + membernumber + "', " + status + ");";

					stmt.executeQuery(insertQuery);
				}

			}
		} catch (SQLException e) {
			log.error(e.getClass() + ": " + e.getMessage(), e);
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error(e.getClass() + ": " + e.getMessage(), e);
			}
		}

		log.info("\nItem was sucessfully stored!");

		return true;
	}

	/*
	 * Adds an item to storage
	 */
	@Override
	public boolean addItem(final Item item) throws ItemException {
		log.info("-------------------------------");
		log.info("Using JDBC Implementation for addItem");
		log.info("-------------------------------");

		if (item != null) {
			// If item is found in the map, the item is already stored
			if (itemMap.containsKey(item.getUuid())) {
				return true;
			} else {

				// Check stored items
				itemMap = retrieveItems();

				if (itemMap == null) {
					log.info("\nNothing in database, adding item locally then storing!");
					itemMap = new ConcurrentHashMap<String, Item>();
					itemMap.put(item.getUuid(), item);
					return storeItems(itemMap);
				}

				// Check database
				if (itemMap.containsKey(item.getUuid())) {
					return true;
				} else {
					itemMap.put(item.getUuid(), item);
					return storeItems(itemMap);
				}
			}
		}

		log.warn("\nCannot add item, item is null!");

		return false;
	}

	/*
	 * Removes an item from a catalog
	 */
	@Override
	public boolean removeItem(final Item item) throws ItemException {
		log.info("-------------------------------");
		log.info("Using JDBC Implementation for removing item");
		log.info("-------------------------------");

		if (item != null) {
			if (itemMap.containsKey(item.getUuid())) {
				// Contains item, remove item from map then store
				itemMap.remove(item.getUuid());
				return storeItems(itemMap);
			} else {
				itemMap = retrieveItems();

				if (itemMap == null) {
					log.info("\nItem was not found in database!");
					return false;
				}

				if (itemMap.containsKey(item.getUuid())) {
					// Contains item, remove item from database
					return removeItemFromStorage(item.getUuid());
				}
			}
		}

		log.warn("\nCannot remove item, key not found!");

		return false;
	}

	/*
	 * Gets an existing item from storage
	 */
	@Override
	public Item getItem(final String uuid) throws ItemException {
		if (uuid != null && !uuid.isEmpty()) {
			if (itemMap.containsKey(uuid)) {
				// Contains item, return item
				return itemMap.get(uuid);
			} else {
				itemMap = retrieveItems();

				if (itemMap == null) {
					return null;
				}

				if (itemMap.containsKey(uuid)) {
					// Contains item, return item
					return itemMap.get(uuid);
				}
			}

		}

		log.warn("\nCannot return item, not found or null!");

		return null;
	}

	/*
	 * Updates an existing item in storage
	 */
	@Override
	public boolean updateItem(final Item item) throws ItemException {
		if (item != null) {
			if (itemMap.containsKey(item.getUuid())) {
				itemMap.put(item.getUuid(), item);
				return storeItems(itemMap);
			} else {
				itemMap = retrieveItems();

				if (itemMap == null) {
					return false;
				}

				if (itemMap.containsKey(item.getUuid())) {
					itemMap.put(item.getUuid(), item);
					return storeItems(itemMap);
				}
			}
		}

		log.warn("\nCannot update item, not found!");

		return false;
	}

}