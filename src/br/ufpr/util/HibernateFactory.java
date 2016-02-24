package br.ufpr.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

	private static SessionFactory sessionFactory;
	
	public static void initialize() {
		try {
			Configuration config = new Configuration().configure(HibernateFactory.class.getResource("/resources/hibernate.cfg.xml"));
			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Error creating SessionFactory :" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session openSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static void closeFactory() {
		if (sessionFactory != null) {
			try {
				sessionFactory.close();
			} catch (HibernateException ignored) {
				System.out.println("Couldn't close SessionFactory");
			}
		}
	}

	public static void close(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ignored) {
				System.out.println("Couldn't close Session");
			}
		}
	}

	public static void rollback(Transaction tx) {
		try {
			if (tx != null) {
				tx.rollback();
			}
		} catch (HibernateException ignored) {
			System.out.println("Couldn't rollback Transaction");
		}
	}

}
