package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;

import br.ufpr.bean.ObjectProperty;

public class ObjectPropertyDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<ObjectProperty> listAll() {
		Criteria criteria = getSession().createCriteria(ObjectProperty.class);
		return criteria.list();
	}
}