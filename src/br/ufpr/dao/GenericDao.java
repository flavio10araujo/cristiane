package br.ufpr.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionImplementor;

import br.ufpr.util.HibernateFactory;

public abstract class GenericDao {

	private Session session;
	private Transaction tx;

	protected Session getSession() {
		return HibernateFactory.openSession();
	}

	protected void startOperation() throws HibernateException {
		session = HibernateFactory.openSession();
		tx = session.beginTransaction();
	}

	protected void handleException(HibernateException e) throws HibernateException {
		HibernateFactory.rollback(tx);
		throw e;
	}

	public void saveOrUpdate(Object obj) {
		try {
			startOperation();
			session.saveOrUpdate(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
	}

	public void delete(Object obj) {
		try {
			startOperation();
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object find(Class clazz, Long id) {
		Object obj = null;
		try {
			startOperation();
			obj = session.load(clazz, id);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
		return obj;
	}

	@SuppressWarnings("rawtypes")
	public List findAll(Class clazz) {
		List objects = null;
		try {
			startOperation();
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	protected Connection getConnection() throws SQLException {
		startOperation();
		SessionImplementor sim = (SessionImplementor) session;
		return sim.connection();
	}

	protected void executeQuery(String[] sql) throws SQLException {
		try {
			Statement stmt = getConnection().createStatement();
			for (int i = 0; i < sql.length; i++) {
				stmt.executeUpdate(sql[i]);
			}
			tx.commit();
			stmt.close();
		}
		catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}
	}
}