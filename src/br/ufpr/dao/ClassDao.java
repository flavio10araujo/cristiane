package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.CheckSubject;
import br.ufpr.bean.Table;

public class ClassDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<br.ufpr.bean.Class> listAll() {
		Session session = getSession();
		Transaction tx = null;
		List<br.ufpr.bean.Class> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(br.ufpr.bean.Class.class);
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
	
	public br.ufpr.bean.Class getByCheckSubject(CheckSubject checkSubject) {
		if (checkSubject == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		 br.ufpr.bean.Class retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(br.ufpr.bean.Class.class);
			criteria.add(Restrictions.eq("checkSubject", checkSubject));
			
			try {
				retorno = (br.ufpr.bean.Class) criteria.list().get(0);
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
	public List<br.ufpr.bean.Class> getWhenCheckSubjectNotNull() {
		Session session = getSession();
		Transaction tx = null;
		List<br.ufpr.bean.Class> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(br.ufpr.bean.Class.class);
			criteria.add(Restrictions.isNotNull("checkSubject"));
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
	
	public br.ufpr.bean.Class getByTable(Table table) {
		if (table == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		br.ufpr.bean.Class retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(br.ufpr.bean.Class.class);
			criteria.add(Restrictions.eq("table", table));
			
			try {
				retorno = (br.ufpr.bean.Class) criteria.list().get(0);
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