package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.ObjectProperty;
import br.ufpr.bean.ObjectPropertyDomainRange;

public class ObjectPropertyDomainRangeDao extends GenericDao {

	public ObjectPropertyDomainRange findByObjectProperty(ObjectProperty objectProperty) {
		if (objectProperty == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		ObjectPropertyDomainRange retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ObjectPropertyDomainRange.class);
			criteria.add(Restrictions.eq("objectProperty", objectProperty));
			
			try {
				retorno = (ObjectPropertyDomainRange) criteria.list().get(0);
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