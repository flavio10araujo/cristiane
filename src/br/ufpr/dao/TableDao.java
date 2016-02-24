package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Table;

public class TableDao extends GenericDao {

	public Table getByPhysicalName(Long databaseId, String physicalName) {
		if (physicalName == null || "".equals(physicalName)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(Table.class);
		criteria.add(Restrictions.eq("database.id", databaseId));
		criteria.add(Restrictions.eq("physicalName", physicalName));
		
		try {
			return (Table) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}