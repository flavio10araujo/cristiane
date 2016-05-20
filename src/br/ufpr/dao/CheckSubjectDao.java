package br.ufpr.dao;

import java.util.List;

import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.CheckSubject;

public class CheckSubjectDao extends GenericDao {

	public List<?> exemplo() {
		Session session = getSession();
		Transaction tx = null;
		List<?> retorno = null;

		try {
			tx = session.beginTransaction();
			// código (save, list, etc)
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
	public List<CheckSubject> getAll() {
		Session session = getSession();
		Transaction tx = null;
		List<CheckSubject> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CheckSubject.class);
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
	
	public CheckSubject getByDescription(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		CheckSubject retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CheckSubject.class);
			criteria.add(Restrictions.eq("description", description.toLowerCase()));
			
			try {
				retorno = (CheckSubject) criteria.list().get(0);
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