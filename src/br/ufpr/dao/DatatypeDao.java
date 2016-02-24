package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Datatype;

public class DatatypeDao extends GenericDao {

	public Datatype getByDescription(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(Datatype.class);
		criteria.add(Restrictions.eq("description", description.toLowerCase()));
		
		try {
			return (Datatype) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}