package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.CheckSubject;
import br.ufpr.bean.CheckValue;

public class CheckValueDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<CheckValue> getByCheckSubject(CheckSubject checkSubject) {
		if (checkSubject == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		List<CheckValue> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CheckValue.class);
			criteria.add(Restrictions.eq("checkSubject", checkSubject));
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
	
	public CheckValue getByDescriptionAndAbreviation(String description, String abreviation) {
		if (description == null || "".equals(description) || abreviation == null || "".equals(abreviation)) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		CheckValue retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CheckValue.class);
			criteria.add(Restrictions.eq("description", description));
			criteria.add(Restrictions.eq("abreviation", abreviation));
			
			try {
				retorno = (CheckValue) criteria.list().get(0);
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