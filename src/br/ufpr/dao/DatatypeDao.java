package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypeDb;

public class DatatypeDao extends GenericDao {

	public DatatypeDb getByDescription(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(DatatypeDb.class);
		criteria.add(Restrictions.eq("description", description.toLowerCase()));
		
		try {
			return (DatatypeDb) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}