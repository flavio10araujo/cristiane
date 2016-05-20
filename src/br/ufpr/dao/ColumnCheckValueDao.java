package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Column;
import br.ufpr.bean.ColumnCheckValue;

public class ColumnCheckValueDao extends GenericDao {

	public ColumnCheckValue getByColumn(Column column) {
		if (column == null) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		ColumnCheckValue retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ColumnCheckValue.class);
			criteria.add(Restrictions.eq("column", column));
			
			try {
				retorno = (ColumnCheckValue) criteria.list().get(0);
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