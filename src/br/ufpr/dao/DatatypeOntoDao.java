package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypeDb;
import br.ufpr.bean.DatatypeOnto;

public class DatatypeOntoDao extends GenericDao {

	public DatatypeOnto getByDatatypeDb(DatatypeDb datatypeDb) {
		Session session = getSession();
		Transaction tx = null;
		DatatypeOnto retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypeOnto.class);
			criteria.add(Restrictions.eq("datatypeDb", datatypeDb));
			
			try {
				retorno = (DatatypeOnto) criteria.list().get(0);
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