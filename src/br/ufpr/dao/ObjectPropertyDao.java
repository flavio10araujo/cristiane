package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
	
	@SuppressWarnings("unchecked")
	public List<ObjectProperty> getByIndInverseFunctional(Boolean indInverseFunctional) {
		Session session = getSession();
		Transaction tx = null;
		List<ObjectProperty> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ObjectProperty.class);
			criteria.add(Restrictions.eq("indInverseFunctional", indInverseFunctional));
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
	
	@SuppressWarnings("unchecked")
	public List<ObjectProperty> getByIndInverseFunctionalAndMinCardinality(Boolean indInverseFunctional, Boolean minCardinality) {
		Session session = getSession();
		Transaction tx = null;
		List<ObjectProperty> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ObjectProperty.class);
			criteria.add(Restrictions.eq("indInverseFunctional", indInverseFunctional));
			criteria.add(Restrictions.eq("minCardinality", minCardinality));
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