package com.turbobooks.model.services.itemservice;

import org.apache.log4j.Logger;

import com.turbobooks.model.dao.ITurbobooksDao;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.exception.ItemException;

/**
 * Implements the item management related services
 * 
 * @author brandonmeyer
 *
 */
public class ItemManagerServiceImpl implements IItemManagerService {
	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	private ITurbobooksDao turbobooksDao;

	public void setTurbobooksDao(ITurbobooksDao turbobooksDao) {
		this.turbobooksDao = turbobooksDao;
	}

	/*
	 * Adds an item to storage
	 */
	@Override
	public boolean addItem(Item item) throws ItemException {

		boolean status = false;

		/*
		 * try {
		 * 
		 * ITurbobooksDao turbobooksDao = DAOFactory.getDao();
		 * 
		 * status = turbobooksDao.addItem(item); } catch (DaoLoadException ex) {
		 * log.error("DAO Load Exception", ex); }
		 */

		if (turbobooksDao != null) {
			status = turbobooksDao.addItem(item);
		}

		return status;
	}

	/*
	 * Removes an item from a catalog
	 */
	@Override
	public boolean removeItem(Item item) throws ItemException {

		boolean status = false;

		/*
		 * try {
		 * 
		 * ITurbobooksDao turbobooksDao = DAOFactory.getDao();
		 * 
		 * status = turbobooksDao.removeItem(item); } catch (DaoLoadException ex) {
		 * log.error("DAO Load Exception", ex); }
		 */
		if (turbobooksDao != null) {
			status = turbobooksDao.removeItem(item);
		}

		return status;
	}

	/*
	 * Gets an existing item from storage
	 */
	@Override
	public Item getItem(final String uuid) throws ItemException {

		Item item = null;
		/*
		 * try {
		 * 
		 * ITurbobooksDao turbobooksDao = DAOFactory.getDao();
		 * 
		 * item = turbobooksDao.getItem(uuid);
		 * 
		 * } catch (DaoLoadException ex) { log.error("DAO Load Exception", ex); }
		 */
		if (turbobooksDao != null) {
			item = turbobooksDao.getItem(uuid);
		}

		return item;
	}

	/*
	 * Updates an existing item in storage
	 */
	@Override
	public boolean updateItem(Item item) throws ItemException {

		boolean status = false;

		/*
		 * try {
		 * 
		 * ITurbobooksDao turbobooksDao = DAOFactory.getDao();
		 * 
		 * status = turbobooksDao.updateItem(item);
		 * 
		 * } catch (DaoLoadException ex) { log.error("DAO Load Exception", ex); }
		 */

		if (turbobooksDao != null) {
			status = turbobooksDao.updateItem(item);
		}

		return status;
	}
}
