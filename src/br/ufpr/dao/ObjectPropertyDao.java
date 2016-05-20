package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufpr.bean.ObjectProperty;

public class ObjectPropertyDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<ObjectProperty> listAll() {
		Session session = getSession();
		Transaction tx = null;
		List<ObjectProperty> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ObjectProperty.class);
			retorno = criteria.list();
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