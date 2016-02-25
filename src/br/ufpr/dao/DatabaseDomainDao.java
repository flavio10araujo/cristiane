package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatabaseDomain;

public class DatabaseDomainDao extends GenericDao {

	public DatabaseDomain getByDescription(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(DatabaseDomain.class);
		criteria.add(Restrictions.eq("description", description.toLowerCase()));
		
		try {
			return (DatabaseDomain) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}