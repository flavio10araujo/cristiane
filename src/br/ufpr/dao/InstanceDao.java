package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Instance;

public class InstanceDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<Instance> listAll() {
		Session session = getSession();
		Transaction tx = null;
		List<Instance> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Instance.class);
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
	
	public Instance getByClass(br.ufpr.bean.Class clazz) {
		if (clazz == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		Instance retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(br.ufpr.bean.Instance.class);
			criteria.add(Restrictions.eq("clazz", clazz));
			
			try {
				retorno = (Instance) criteria.list().get(0);
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