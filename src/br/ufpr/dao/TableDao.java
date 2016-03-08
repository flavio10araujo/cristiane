package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;
import br.ufpr.bean.Table;

public class TableDao extends GenericDao {

	/**
	 * 
	 * @param databaseId
	 * @param physicalName
	 * @return
	 */
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
	
	/**
	 * 
	 * @param database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Table> getByDatabase(Database database) {
		if (database == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(Table.class);
		criteria.add(Restrictions.eq("database", database));
		
		try {
			return criteria.list();
		}
		catch (Exception e) {
			return null;
		}
	}
}