package com.turbobooks.model.dao.hibernate;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.turbobooks.model.dao.ITurbobooksDao;
import com.turbobooks.model.domain.Book;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.exception.ItemException;
import com.turbobooks.model.services.factory.HibernateSessionFactory;

/**
 * Uses simple CRUD ops to persist data
 * 
 * @author brandonmeyer
 *
 */
public class TurbobooksHibernateDaoImpl implements ITurbobooksDao {

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	private Session fetchSession() {
		log.info("******Fetching Hibernate Session");

		Session session = HibernateSessionFactory.currentSession();

		return session;

	}

	/*
	 * Adds an item to storage
	 */
	@Override
	public boolean addItem(final Item item) throws ItemException {
		boolean isAdded = false;
		log.info("-------------------------------");
		log.info("Using Hibernate Implementation for addItem");
		log.info("-------------------------------");

		if (item != null) {
			Transaction tx = null;
			try {
				Session session = fetchSession();
				tx = session.beginTransaction();
				log.info("About to save item: " + item.toString());

				session.save(item);
				tx.commit();
				isAdded = true;
				log.info("Item added successfully!");
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				log.error(e.getClass() + ": " + e.getMessage(), e);
			} finally {
				HibernateSessionFactory.closeSessionAndFactory();
			}
		}

		return isAdded;
	}

	/*
	 * Removes an item from a catalog
	 */
	@Override
	public boolean removeItem(final Item item) throws ItemException {
		boolean isRemoved = false;
		log.info("-------------------------------");
		log.info("Using Hibernate Implementation for removing item");
		log.info("-------------------------------");

		if (item != null) {
			log.info("About to remove an Item!");
			Transaction tx = null;
			try {
				Session session = fetchSession();
				tx = session.beginTransaction();

				session.delete(item);
				tx.commit();
				isRemoved = true;
				log.info("Item removed successfully!");
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				log.error(e.getClass() + ": " + e.getMessage(), e);
			} finally {
				HibernateSessionFactory.closeSessionAndFactory();
			}
		}

		return isRemoved;
	}

	/*
	 * Gets an existing item from storage
	 */
	@Override
	public Item getItem(final String uuid) throws ItemException {
		log.info("-------------------------------");
		log.info("Using Hibernate Implementation for retrieving item");
		log.info("-------------------------------");
		Item retrievedItem = null;

		if (uuid != null && !uuid.isEmpty()) {
			Transaction tx = null;
			try {
				Session session = fetchSession();
				tx = session.beginTransaction();

				log.info("About to retrieve item with uuid: " + uuid);

				Query query = session.createQuery("from Item where uuid = :uuid ");
				query.setParameter("uuid", uuid);

				retrievedItem = (Item) query.uniqueResult();

				tx.commit();

				log.info("Item retrieved successfully!");
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				log.error(e.getClass() + ": " + e.getMessage(), e);
			} finally {
				HibernateSessionFactory.closeSessionAndFactory();
			}
		} else {
			log.warn("\nUUID is NULL!");
		}

		if (retrievedItem == null) {
			log.warn("\nItem not found or NULL!");
		}

		return retrievedItem;
	}

	/*
	 * Updates an existing item in storage
	 */
	@Override
	public boolean updateItem(final Item item) throws ItemException {
		boolean isUpdated = false;
		log.info("-------------------------------");
		log.info("Using Hibernate Implementation for updating an item");
		log.info("-------------------------------");

		if (item != null && item instanceof Book) {
			log.info("About to update an Item of type Book!");
			Transaction tx = null;
			try {
				Session session = fetchSession();
				tx = session.beginTransaction();

				session.update(item);
				tx.commit();
				isUpdated = true;
				log.info("Item of type Book updated successfully!");
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				log.error(e.getClass() + ": " + e.getMessage(), e);
			} finally {
				HibernateSessionFactory.closeSessionAndFactory();
			}
		}

		return isUpdated;
	}

	@Override
	public ConcurrentHashMap<String, Item> retrieveItems() throws ItemException {
		// Intentionally empty
		return null;
	}

	@Override
	public boolean storeItems(ConcurrentHashMap<String, Item> itemMap) throws ItemException {
		// Intentionally empty
		return false;
	}

	@Override
	public boolean removeItemFromStorage(String uuid) throws ItemException {
		// Intentionally empty
		return false;
	}

}
