package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypePropertyDomain;

public class DatatypePropertyDomainDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<DatatypePropertyDomain> getByDatatypePropertyID(Long datatypePropertyID) {
		Session session = getSession();
		Transaction tx = null;
		List<DatatypePropertyDomain> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(DatatypePropertyDomain.class);
			criteria.add(Restrictions.eq("datatypeProperty.id", datatypePropertyID));
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