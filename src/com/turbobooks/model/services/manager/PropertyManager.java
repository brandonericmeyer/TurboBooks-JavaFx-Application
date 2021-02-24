package com.turbobooks.model.services.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.turbobooks.model.business.exception.PropertyFileNotFoundException;

/**
 * Manages property loading for the application
 * 
 * @author brandonmeyer
 *
 */
public class PropertyManager {
	private static Properties properties;
	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	/**
	 * Load the properties file so its contents are available for classes in the
	 * Model tier.
	 * 
	 * @param propertyFileLocation
	 * @throws PropertyFileNotFoundException
	 */
	public static void loadProperties(String propertyFileLocation) throws PropertyFileNotFoundException {
		properties = new Properties();
		FileInputStream sf = null;
		try {
			sf = new FileInputStream(propertyFileLocation);
			properties.load(sf);

			log.info("\nProperty file successfully loaded from location: " + propertyFileLocation);
			log.info("\nProperty Contents: " + properties.toString());

		} catch (FileNotFoundException fnfe) {
			log.error("Property file not found at location" + propertyFileLocation, fnfe);
			throw new PropertyFileNotFoundException("Property File cannot be found.", fnfe);
		} catch (IOException ioe) {
			log.error("IOException while loading Properties file.");
			throw new PropertyFileNotFoundException("IOException while loading Properties file.", ioe);
		} catch (Exception excp) {
			log.error("Exception while loading Properties file.");
			throw new PropertyFileNotFoundException("Exception while loading Properties file.", excp);
		} finally {
			if (sf != null) {
				try {
					sf.close();
				} catch (IOException e) {
					// Can't do much here if exceptions occur, other then logging
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This methods returns the Value for the passed key.
	 * 
	 * @param key - key whose value needs to be returned
	 * @return String - value for the passed key
	 */
	static public String getPropertyValue(String key) {
		log.info("\nProperty key: " + key);

		return properties.getProperty(key);
	}
}