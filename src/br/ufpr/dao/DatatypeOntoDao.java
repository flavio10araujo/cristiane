package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypeDb;
import br.ufpr.bean.DatatypeOnto;

public class DatatypeOntoDao extends GenericDao {

	public DatatypeOnto getByDatatypeDb(DatatypeDb datatypeDb) {
		Criteria criteria = getSession().createCriteria(DatatypeOnto.class);
		criteria.add(Restrictions.eq("datatypeDb", datatypeDb));
		return (DatatypeOnto) criteria.list().get(0);
	}
}