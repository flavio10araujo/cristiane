package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.CheckSubject;
import br.ufpr.bean.Table;

public class ClassDao extends GenericDao {

	public br.ufpr.bean.Class getByCheckSubject(CheckSubject checkSubject) {
		if (checkSubject == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(br.ufpr.bean.Class.class);
		criteria.add(Restrictions.eq("checkSubject", checkSubject));
		
		try {
			return (br.ufpr.bean.Class) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<br.ufpr.bean.Class> getWhenCheckSubjectNotNull() {
		Criteria criteria = getSession().createCriteria(br.ufpr.bean.Class.class);
		criteria.add(Restrictions.isNotNull("checkSubject"));
		return criteria.list();
	}
	
	public br.ufpr.bean.Class getByTable(Table table) {
		if (table == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(br.ufpr.bean.Class.class);
		criteria.add(Restrictions.eq("table", table));
		
		try {
			return (br.ufpr.bean.Class) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}