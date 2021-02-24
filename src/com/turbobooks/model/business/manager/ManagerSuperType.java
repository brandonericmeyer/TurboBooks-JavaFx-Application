package com.turbobooks.model.business.manager;

import org.apache.log4j.Logger;

import com.turbobooks.model.business.exception.PropertyFileNotFoundException;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.manager.SAXPropertyManager;

/**
 * Abstract class contains all business logic for management of services
 * 
 * @author brandonmeyer
 *
 */
public abstract class ManagerSuperType {
	static Logger log = Logger.getLogger("com.turbobookslog4jsample");
	private static SAXPropertyManager saxPropertyManager;

	static {
		try {
			ManagerSuperType.loadProperties();
		} catch (PropertyFileNotFoundException propertyFileNotFoundException) {
			log.error("Application Properties failed to be loaded - Application exiting...");
			System.exit(1);
		}
	}

	/**
	 * Generic method that all clients of this class can call to perform certain
	 * actions.
	 * 
	 * @param commandString Holds the service name to be invoked
	 * @param item          Holds application specific domain state
	 * @return false if action failed true if action is successful
	 */
	public abstract boolean performAction(final String commandString, final Item item, final String memberId);

	/**
	 * Loads the property file into memory so its available for use by all tiers
	 * (business and below)
	 * 
	 * @throws PropertyFileNotFoundException Properties file could not be loaded.
	 */
	public static void loadProperties() throws PropertyFileNotFoundException {

		String propertyFileLocation = ".//config//";

		if (propertyFileLocation != null) {
			saxPropertyManager = new SAXPropertyManager();
			saxPropertyManager.loadProperties(propertyFileLocation);
		} else {
			log.error("Property file location not set. Passed in value is: " + propertyFileLocation + ".");
			throw new PropertyFileNotFoundException("Property file location not set", null);
		}

		// PropertyManager.loadProperties(propertyFileLocation);
	}
}
