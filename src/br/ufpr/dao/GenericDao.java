package br.ufpr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		startOperation();
		SessionImplementor sim = (SessionImplementor) session;
		return sim.connection();
	}

	/**
	 * Executar uma sequência de queries e apenas commitar se todas rodarem corretamente.
	 * 
	 * @param sql
	 * @throws SQLException
	 */
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

	/**
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList<Map> executeQuery(String sql) throws SQLException {
		ResultSet resultSet = null;
		ArrayList<Map> list = null;

		try {
			Statement stmt = getConnection().createStatement();
			resultSet = stmt.executeQuery(sql);
			list = gerarArrayListFromResultSet(resultSet);
			tx.commit();
			stmt.close();
		}
		catch (HibernateException e) {
			handleException(e);
		} finally {
			HibernateFactory.close(session);
		}

		return list;
	}

	@SuppressWarnings("rawtypes")
	private ArrayList<Map> gerarArrayListFromResultSet(ResultSet rs) throws SQLException {
		
		ArrayList<Map> list = new ArrayList<Map>(rs.getFetchSize());
		ResultSetMetaData rsmd = null;
		
		try {
			rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount(); // Number of columns returned by the query.
			List<String> cols = new ArrayList<String>(numCols); // ArrayList that will hold the column names.
			
			for (int i = 1; i <= numCols; i++) {
				cols.add(rsmd.getColumnName(i));
			}
			
			rsmd = null;

			while (rs.next()) {
				Map<String, Object> values = new HashMap<String, Object>(numCols);
				
				for (int i = 1; i <= numCols; i++) {
					values.put(cols.get(i - 1), rs.getObject(i));
				}
				
				list.add(values);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
}