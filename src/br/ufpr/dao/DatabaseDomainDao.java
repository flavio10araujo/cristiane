package br.ufpr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;
import br.ufpr.bean.DatabaseDomain;

public class DatabaseDomainDao extends GenericDao {

	/**
	 * 
	 * @param database
	 * @param description
	 * @return
	 */
	public DatabaseDomain getByDescription(Database database, String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(DatabaseDomain.class);
		criteria.add(Restrictions.eq("database", database));
		criteria.add(Restrictions.eq("description", description.toLowerCase()));
		
		try {
			return (DatabaseDomain) criteria.list().get(0);
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
	public List<DatabaseDomain> getByDatabase(Database database) {
		if (database == null) {
			return null;
		}
		
		Criteria criteria = getSession().createCriteria(DatabaseDomain.class);
		criteria.add(Restrictions.eq("database", database));
		
		try {
			return criteria.list();
		}
		catch (Exception e) {
			return null;
		}
	}
}