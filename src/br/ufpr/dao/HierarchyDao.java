package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;

import br.ufpr.bean.Hierarchy;

public class HierarchyDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<Hierarchy> listAll() {
		Criteria criteria = getSession().createCriteria(Hierarchy.class);
		return criteria.list();
	}
}