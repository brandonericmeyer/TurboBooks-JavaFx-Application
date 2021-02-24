package com.turbobooks.model.services.checkoutservice;

import org.apache.log4j.Logger;

import com.turbobooks.model.dao.ITurbobooksDao;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.exception.CheckoutException;
import com.turbobooks.model.services.exception.ItemException;

/**
 * Implements ICheckoutService for checking items out
 * 
 * @author brandonmeyer
 *
 */
public class CheckoutServiceImpl implements ICheckoutService {

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");
	private ITurbobooksDao turbobooksDao;

	public void setTurbobooksDao(ITurbobooksDao turbobooksDao) {
		this.turbobooksDao = turbobooksDao;
	}

	/*
	 * Checks an item out and returns true on success
	 */
	@Override
	public boolean checkoutItem(final String memberId, final String uuid) throws CheckoutException, ItemException {

		Item updatedItem = null;
		/*
		 * ITurbobooksDao turbobooksDao = null;
		 * 
		 * try { turbobooksDao = DAOFactory.getDao(); updatedItem =
		 * turbobooksDao.getItem(uuid);
		 * 
		 * } catch (DaoLoadException ex) { log.error("DAO Load Exception", ex); } catch
		 * (ItemException e) { log.error("TurbobooksManager::getItem failed " + e); }
		 * 
		 */

		if (turbobooksDao != null) {

			updatedItem = turbobooksDao.getItem(uuid);

			if (updatedItem != null && updatedItem.isAvailable()) {

				updatedItem.setAvailable(false);
				updatedItem.setMemberNumber(memberId);

				return turbobooksDao.updateItem(updatedItem);
			}
		}

		return false;
	}

	/*
	 * Checks if an item is available for checkout
	 */
	@Override
	public boolean isItemAvailable(final String uuid) throws CheckoutException {

		Item storedItem = null;

		/*
		 * try {
		 * 
		 * ITurbobooksDao turbobooksDao = DAOFactory.getDao();
		 * 
		 * storedItem = turbobooksDao.getItem(uuid);
		 * 
		 * } catch (DaoLoadException ex) { log.error("DAO Load Exception", ex); } catch
		 * (ItemException e) { log.error("TurbobooksManager::getItem failed " + e); }
		 */

		if (turbobooksDao != null) {

			try {
				storedItem = turbobooksDao.getItem(uuid);
			} catch (ItemException e) {
				log.error("TurbobooksManager::getItem failed " + e);
			}

			if (storedItem != null) {
				return storedItem.isAvailable();
			} else {
				return false;
			} // end if
		}
		return false;
	}
}
