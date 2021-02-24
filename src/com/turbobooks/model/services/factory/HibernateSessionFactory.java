package com.turbobooks.model.services.factory;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;;

public class HibernateSessionFactory {

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	private static final ThreadLocal threadLocal = new ThreadLocal();

	private static org.hibernate.SessionFactory sessionFactory;

	private HibernateSessionFactory() {

	}

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 *
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				try {
					sessionFactory = new Configuration().configure().buildSessionFactory();

				} catch (Exception e) {
					log.error("%%%% Error Creating SessionFactory %%%%", e);
				}
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	public static void closeFactory() throws HibernateException {
		closeSessionAndFactory();
	}

	public static void closeSessionAndFactory() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}

		if (sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}
}