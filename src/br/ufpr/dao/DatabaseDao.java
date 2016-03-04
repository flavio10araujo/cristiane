package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;

public class DatabaseDao extends GenericDao {
	
	public Database get(Long id){
		Criteria criteria = getSession().createCriteria(Database.class);
		criteria.add(Restrictions.eq("id", id));
		return (Database) criteria.list().get(0);
	}

	public Database getByName(String name){
		Criteria criteria = getSession().createCriteria(Database.class);
		criteria.add(Restrictions.eq("name", name));
		return (Database) criteria.list().get(0);
	}
}