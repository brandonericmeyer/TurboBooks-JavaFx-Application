package com.turbobooks.model.services.factory;

import com.turbobooks.model.business.exception.ServiceLoadException;
import com.turbobooks.model.services.IService;
import com.turbobooks.model.services.manager.SAXPropertyManager;

/**
 * @author brandonmeyer
 *
 */
public class ServiceFactory {

	private ServiceFactory() {
	}

	private static ServiceFactory serviceFactoryInstance;

	public static ServiceFactory getInstance() {
		if (serviceFactoryInstance == null)
			serviceFactoryInstance = new ServiceFactory();
		return serviceFactoryInstance;
	}

	/**
	 * 
	 * @param serviceName
	 * @return
	 * @throws ServiceLoadException
	 */
	public IService getService(String serviceName) throws ServiceLoadException {
		try {
			Class<?> c = Class.forName(getImplName(serviceName));
			return (IService) c.newInstance();
		} catch (Exception excp) {
			serviceName = serviceName + " not loaded";
			throw new ServiceLoadException(serviceName, excp);
		}
	}

	/**
	 * 
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	private String getImplName(String serviceName) throws Exception {

		// return PropertyManager.getPropertyValue(serviceName);
		return SAXPropertyManager.getPropertyValue(serviceName);
	}
}