package br.ufpr.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			HibernateFactory.initialize();
		} catch (Throwable ex) {
			System.err.println("Error initializing hibernate :" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

}
