package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.ObjectProperty;
import br.ufpr.bean.ObjectPropertyDomainRange;

public class ObjectPropertyDomainRangeDao extends GenericDao {

	public ObjectPropertyDomainRange findByObjectProperty(ObjectProperty objectProperty) {
		if (objectProperty == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(ObjectPropertyDomainRange.class);
		criteria.add(Restrictions.eq("objectProperty", objectProperty));
		
		try {
			return (ObjectPropertyDomainRange) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}