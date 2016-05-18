package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypePropertyDomain;

public class DatatypePropertyDomainDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public List<DatatypePropertyDomain> getByDatatypePropertyID(Long datatypePropertyID) {
		Criteria criteria = getSession().createCriteria(DatatypePropertyDomain.class);
		criteria.add(Restrictions.eq("datatypeProperty.id", datatypePropertyID));
		return criteria.list();
	}
}