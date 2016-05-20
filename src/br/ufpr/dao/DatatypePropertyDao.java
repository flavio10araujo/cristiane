package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypeProperty;

public class DatatypePropertyDao extends GenericDao {

	public DatatypeProperty getByDatatypeProperty(DatatypeProperty datatypeProperty) {
		Session session = getSession();
		Transaction tx = null;
		DatatypeProperty retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypeProperty.class);
			criteria.add(Restrictions.eq("description", datatypeProperty.getDescription()));
			criteria.add(Restrictions.eq("ontology", datatypeProperty.getOntology()));
			criteria.add(Restrictions.eq("datatypeOnto", datatypeProperty.getDatatypeOnto()));
			
			try {
				retorno = (DatatypeProperty) criteria.list().get(0);
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
	
	@SuppressWarnings("unchecked")
	public List<DatatypeProperty> getByIndCommonConceptAndIndDescription(boolean indCommonConcept, boolean indDescription) {
		Session session = getSession();
		Transaction tx = null;
		List<DatatypeProperty> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypeProperty.class);
			criteria.add(Restrictions.eq("indCommonConcept", indCommonConcept));
			criteria.add(Restrictions.eq("indDescription", indDescription));
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
	public List<DatatypeProperty> getByIndCommonConcept(boolean indCommonConcept) {
		Session session = getSession();
		Transaction tx = null;
		List<DatatypeProperty> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypeProperty.class);
			criteria.add(Restrictions.eq("indCommonConcept", indCommonConcept));
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
	public List<DatatypeProperty> getByIndDescription(boolean indDescription) {
		Session session = getSession();
		Transaction tx = null;
		List<DatatypeProperty> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypeProperty.class);
			criteria.add(Restrictions.eq("indDescription", indDescription));
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