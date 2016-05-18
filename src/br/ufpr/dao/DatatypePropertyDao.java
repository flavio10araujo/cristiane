package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.DatatypeProperty;

public class DatatypePropertyDao extends GenericDao {

	public DatatypeProperty getByDatatypeProperty(DatatypeProperty datatypeProperty) {
		Criteria criteria = getSession().createCriteria(DatatypeProperty.class);
		criteria.add(Restrictions.eq("description", datatypeProperty.getDescription()));
		criteria.add(Restrictions.eq("ontology", datatypeProperty.getOntology()));
		criteria.add(Restrictions.eq("datatypeOnto", datatypeProperty.getDatatypeOnto()));
		
		try {
			return (DatatypeProperty) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DatatypeProperty> getByIndCommonConceptAndIndDescription(Boolean indCommonConcept, Boolean indDescription) {
		Criteria criteria = getSession().createCriteria(DatatypeProperty.class);
		criteria.add(Restrictions.eq("indCommonConcept", indCommonConcept));
		criteria.add(Restrictions.eq("indDescription", indDescription));
		return criteria.list();
	}
}