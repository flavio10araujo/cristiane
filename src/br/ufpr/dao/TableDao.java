package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;
import br.ufpr.bean.Table;

public class TableDao extends GenericDao {

	/**
	 * 
	 * @param databaseId
	 * @param physicalName
	 * @return
	 */
	public Table getByPhysicalName(Long databaseId, String physicalName) {
		if (physicalName == null || "".equals(physicalName)) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		Table retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Table.class);
			criteria.add(Restrictions.eq("database.id", databaseId));
			criteria.add(Restrictions.eq("physicalName", physicalName));
			
			try {
				retorno = (Table) criteria.list().get(0);
			}
			catch (Exception e) {
				retorno = null;
			}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
	
	/**
	 * 
	 * @param database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Table> getByDatabase(Database database) {
		if (database == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		List<Table> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Table.class);
			criteria.add(Restrictions.eq("database", database));
			
			try {
				retorno = criteria.list();
			}
			catch (Exception e) {
				retorno = null;
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
}