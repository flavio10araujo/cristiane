package br.ufpr.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Column> getByIndDescriptionAndIndColumnCheck(Long databaseId, boolean indDescription, boolean indColumnCheck) {
		Criteria criteria = getSession().createCriteria(Column.class);
		criteria.add(Restrictions.eq("indDescription", indDescription));
		criteria.add(Restrictions.eq("indColumnCheck", indColumnCheck));
		
		@SuppressWarnings("unchecked")
		List<Column> columns = criteria.list();
		
		if (columns == null || columns.size() == 0) {
			return null;
		}
		
		List<Column> retorno = new ArrayList<Column>();
		
		for (Column column : columns) {
			// Buscar apenas as colunas do database passado.
			if (column.getTable().getDatabase().getId() == databaseId) {
				retorno.add(column);
			}
		}
		
		return retorno;
	}
}