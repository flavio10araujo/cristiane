package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;
import br.ufpr.bean.DatabaseDomain;

public class DatabaseDomainDao extends GenericDao {

	/**
	 * 
	 * @param database
	 * @param description
	 * @return
	 */
	public DatabaseDomain getByDescription(Database database, String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		DatabaseDomain retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatabaseDomain.class);
			criteria.add(Restrictions.eq("database", database));
			criteria.add(Restrictions.eq("description", description.toLowerCase()));
			
			try {
				retorno = (DatabaseDomain) criteria.list().get(0);
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
	public List<DatabaseDomain> getByDatabase(Database database) {
		if (database == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		List<DatabaseDomain> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatabaseDomain.class);
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