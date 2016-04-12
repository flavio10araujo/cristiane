package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.CheckSubject;
import br.ufpr.bean.CheckValue;

public class CheckValueDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<CheckValue> getByCheckSubject(CheckSubject checkSubject) {
		if (checkSubject == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(CheckValue.class);
		criteria.add(Restrictions.eq("checkSubject", checkSubject));
		
		return criteria.list();
	}
	
	public CheckValue getByDescriptionAndAbreviation(String description, String abreviation) {
		if (description == null || "".equals(description) || abreviation == null || "".equals(abreviation)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(CheckValue.class);
		criteria.add(Restrictions.eq("description", description));
		criteria.add(Restrictions.eq("abreviation", abreviation));
		
		try {
			return (CheckValue) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}