package com.turbobooks.model.business.manager;

import org.apache.log4j.Logger;

import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.checkoutservice.ICheckoutService;
import com.turbobooks.model.services.exception.CheckoutException;
import com.turbobooks.model.services.exception.ItemException;
import com.turbobooks.model.services.itemservice.IItemManagerService;

/**
 * Contains implementation of business logic for service management
 * 
 * @author brandonmeyer
 *
 */
public class DAOManager extends ManagerSuperType {

	// private static DAOManager _instance;
	static Logger log = Logger.getLogger("com.turbobookslog4jsample");
	private ICheckoutService checkoutService;
	private IItemManagerService itemManagerService;

	public enum Services {
		ADDITEM("AddItem"), GETITEM("GetItem"), REMOVEITEM("RemoveItem"), UPDATEITEM("UpdateItem"),
		CHECKOUTITEM("CheckoutItem"), ISITEMAVAILABLE("IsItemAvailable");

		private Services(final String service) {
			this.service = service;
		}

		public String service;

		public String getLabel() {
			return service;
		}

	}

	/**
	 * keep the constructor private to prevent instantiation by outside callers.
	 */
	private DAOManager() {
		// construct object . . .
	}

	/**
	 * Assures that there is only one DAOManager created.
	 * 
	 * @return TurbobooksManager instance
	 * 
	 *         public static synchronized DAOManager getInstance() { if (_instance
	 *         == null) { _instance = new DAOManager(); } return _instance; }
	 */

	public void setCheckoutService(ICheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}

	public void setItemManagerService(IItemManagerService itemManagerService) {
		this.itemManagerService = itemManagerService;
	}

	/**
	 * Generic method that all clients of this class can call to perform certain
	 * actions.
	 * 
	 * @param commandString Holds the service name to be invoked *
	 * @param item          Holds application specific domain state
	 * @return false if action failed true if action is successful
	 */
	@Override
	public boolean performAction(final String commandString, final Item item, final String memberId) {
		boolean action = false;

		// Item Management Services
		if (commandString.equals(TurbobooksManager.Services.ADDITEM.getLabel())) {
			action = addItem(IItemManagerService.NAME, item);
		} else if (commandString.equals(TurbobooksManager.Services.REMOVEITEM.getLabel())) {
			action = removeItem(IItemManagerService.NAME, item);
		} else if (commandString.equals(TurbobooksManager.Services.UPDATEITEM.getLabel())) {
			action = updateItem(IItemManagerService.NAME, item);
		}
		// Begin Checkout Services
		else if (commandString.equals(TurbobooksManager.Services.CHECKOUTITEM.getLabel())) {
			action = checkoutItem(ICheckoutService.NAME, item.getUuid(), memberId);
		} else if (commandString.equals(TurbobooksManager.Services.ISITEMAVAILABLE.getLabel())) {
			action = isItemAvailable(ICheckoutService.NAME, item.getUuid());
		}

		return action;
	}

	/**
	 * Adds an item to the collection
	 * 
	 * @param commandString
	 * @param item
	 * @return
	 */
	public boolean addItem(String commandString, Item item) {
		boolean isAdded = false;

		/*
		 * ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 * IItemManagerService iItemManagerService;
		 * 
		 * try { iItemManagerService = (IItemManagerService)
		 * serviceFactory.getService(commandString); isAdded =
		 * iItemManagerService.addItem(item); } catch (ServiceLoadException e1) {
		 * log.error("TurbobooksManager::addItem failed " + e1); } catch (ItemException
		 * e) { log.error("TurbobooksManager::addItem failed " + e); }
		 */
		if (itemManagerService != null) {
			try {
				isAdded = itemManagerService.addItem(item);
			} catch (ItemException e) {
				log.error("TurbobooksManager::addItem failed " + e);
			}
		}

		return isAdded;
	}

	/**
	 * Removes an item from the collection
	 * 
	 * @param commandString
	 * @param item
	 * @return
	 */
	public boolean removeItem(String commandString, Item item) {
		boolean isRemoved = false;

		/*
		 * ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 * IItemManagerService iItemManagerService;
		 * 
		 * try { iItemManagerService = (IItemManagerService)
		 * serviceFactory.getService(commandString); isRemoved =
		 * iItemManagerService.removeItem(item); } catch (ServiceLoadException e1) {
		 * log.error("TurbobooksManager::removeItem failed " + e1); } catch
		 * (ItemException e) { log.error("TurbobooksManager::removeItem failed " + e); }
		 */
		if (itemManagerService != null) {
			try {
				isRemoved = itemManagerService.removeItem(item);
			} catch (ItemException e) {
				log.error("TurbobooksManager::removeItem failed " + e);
			}
		}

		return isRemoved;
	}

	/**
	 * Retrieves an item from the collection
	 * 
	 * @param commandString
	 * @param item
	 * @return
	 */
	public Item getItem(String commandString, String uuid) {
		Item updatedItem = null;

		/*
		 * ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 * IItemManagerService iItemManagerService;
		 * 
		 * try { iItemManagerService = (IItemManagerService)
		 * serviceFactory.getService(commandString); return
		 * iItemManagerService.getItem(uuid); } catch (ServiceLoadException e1) {
		 * log.error("TurbobooksManager::getItem failed " + e1); } catch (ItemException
		 * e) { log.error("TurbobooksManager::getItem failed " + e); }
		 */

		if (itemManagerService != null) {
			try {
				updatedItem = itemManagerService.getItem(uuid);
			} catch (ItemException e) {
				log.error("TurbobooksManager::getItem failed " + e);
			}
		}

		return updatedItem;
	}

	/**
	 * Updates an item from the collection
	 * 
	 * @param commandString
	 * @param item
	 * @return
	 */
	public boolean updateItem(String commandString, Item item) {
		boolean isUpdated = false;

		/*
		 * ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 * IItemManagerService iItemManagerService;
		 * 
		 * try { iItemManagerService = (IItemManagerService)
		 * serviceFactory.getService(commandString); isUpdated =
		 * iItemManagerService.updateItem(item); } catch (ServiceLoadException e1) {
		 * log.error("TurbobooksManager::updateItem failed " + e1); } catch
		 * (ItemException e) { log.error("TurbobooksManager::updateItem failed " + e); }
		 */

		if (itemManagerService != null) {
			try {
				isUpdated = itemManagerService.updateItem(item);
			} catch (ItemException e) {
				log.error("TurbobooksManager::updateItem failed " + e);
			}
		}

		return isUpdated;
	}

	/**
	 * Checks an item out from the collection
	 * 
	 * @param commandString
	 * @param item
	 * @param member
	 * 
	 * @return
	 */
	public boolean checkoutItem(final String commandString, final String uuid, final String memberId) {
		boolean isCheckedOut = false;

		/*
		 * ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 * ICheckoutService checkoutService;
		 * 
		 * try { checkoutService = (ICheckoutService)
		 * serviceFactory.getService(commandString); isCheckedOut =
		 * checkoutService.checkoutItem(memberId, uuid); } catch (ServiceLoadException
		 * e1) { log.error("TurbobooksManager::checkoutItem failed " + e1); } catch
		 * (CheckoutException e) { log.error("TurbobooksManager::checkoutItem failed " +
		 * e); } catch (ItemException e) {
		 * log.error("TurbobooksManager::checkoutItem failed " + e); }
		 */

		if (checkoutService != null) {

			try {
				isCheckedOut = checkoutService.checkoutItem(memberId, uuid);
			} catch (CheckoutException e) {
				log.error("TurbobooksManager::checkoutItem failed " + e);
			} catch (ItemException e) {
				log.error("TurbobooksManager::checkoutItem failed " + e);
			}
		}

		return isCheckedOut;
	}

	/**
	 * Checks if an item is available
	 * 
	 * @param commandString
	 * @param item
	 * @return
	 */
	public boolean isItemAvailable(final String commandString, final String uuid) {
		boolean isItemAvailable = false;

		/*
		 * ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 * ICheckoutService checkoutService;
		 * 
		 * try { checkoutService = (ICheckoutService)
		 * serviceFactory.getService(commandString); isItemAvailable =
		 * checkoutService.isItemAvailable(uuid); } catch (ServiceLoadException e1) {
		 * log.error("TurbobooksManager::isItemAvailable failed " + e1); } catch
		 * (CheckoutException e) {
		 * log.error("TurbobooksManager::isItemAvailable failed " + e); }
		 */

		if (checkoutService != null) {

			try {
				isItemAvailable = checkoutService.isItemAvailable(uuid);
			} catch (CheckoutException e) {
				log.error("TurbobooksManager::isItemAvailable failed " + e);
			}
		}

		return isItemAvailable;
	}
}