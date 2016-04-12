package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.CheckSubject;

public class CheckSubjectDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<CheckSubject> getAll() {
		Criteria criteria = getSession().createCriteria(CheckSubject.class);
		return criteria.list();
	}
	
	public CheckSubject getByDescription(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(CheckSubject.class);
		criteria.add(Restrictions.eq("description", description.toLowerCase()));
		
		try {
			return (CheckSubject) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}