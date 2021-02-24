package com.turbobooks.model.services.factory;

import com.turbobooks.model.business.exception.DaoLoadException;
import com.turbobooks.model.dao.ITurbobooksDao;
import com.turbobooks.model.services.manager.SAXPropertyManager;

public class DAOFactory {
	/**
	 * Calls PropertyManager to fetch the DAO Implementation and returns it.
	 * 
	 * @return TurbobooksDao
	 */
	public static ITurbobooksDao getDao() throws DaoLoadException {

		Class c;
		Object o = null;
		try {

			String daoImplString = SAXPropertyManager.getPropertyValue("TurbobooksDao");

			c = Class.forName(daoImplString);
			o = c.newInstance();

		} catch (ClassNotFoundException e) {
			throw new DaoLoadException("Class not found", e);
		} catch (InstantiationException e) {
			throw new DaoLoadException("Instantiation Issue", e);
		} catch (IllegalAccessException e) {
			throw new DaoLoadException("Illegal Access Issue", e);
		}
		return (ITurbobooksDao) o;
	}

}