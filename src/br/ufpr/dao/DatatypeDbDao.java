package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypeDb;

public class DatatypeDbDao extends GenericDao {

	public DatatypeDb getByDescription(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		DatatypeDb retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypeDb.class);
			criteria.add(Restrictions.eq("description", description.toLowerCase()));
			
			try {
				retorno = (DatatypeDb) criteria.list().get(0);
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