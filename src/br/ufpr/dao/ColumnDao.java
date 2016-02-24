package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Column;

public class ColumnDao extends GenericDao {

	public Column getByPhysicalName(Long tableId, String physicalName) {
		Criteria criteria = getSession().createCriteria(Column.class);
		criteria.add(Restrictions.eq("table.id", tableId));
		criteria.add(Restrictions.eq("physicalName", physicalName));
		return (Column) criteria.list().get(0);
	}
}