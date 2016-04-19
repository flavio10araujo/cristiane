package br.ufpr.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Column;
import br.ufpr.bean.ColumnCheckValue;

public class ColumnCheckValueDao extends GenericDao {

	public ColumnCheckValue getByColumn(Column column) {
		if (column == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(ColumnCheckValue.class);
		criteria.add(Restrictions.eq("column", column));
		
		try {
			return (ColumnCheckValue) criteria.list().get(0);
		}
		catch (Exception e) {
			return null;
		}
	}
}