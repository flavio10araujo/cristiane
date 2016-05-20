package br.ufpr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Column;

public class ColumnDao extends GenericDao {

	public Column getByPhysicalName(Long tableId, String physicalName) {
		Session session = getSession();
		Transaction tx = null;
		Column retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Column.class);
			criteria.add(Restrictions.eq("table.id", tableId));
			criteria.add(Restrictions.eq("physicalName", physicalName));
			retorno = (Column) criteria.list().get(0);
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
	
	/**
	 * 
	 * 
	 * @param databaseId
	 * @param indColumnCheck
	 * @param indPrimaryKey
	 * @param indForeignKey
	 * @param indUniqueKey
	 * @return
	 */
	public List<Column> getByIndsColumncheckPrimarykeyForeignkeyUniquekey(Long databaseId, boolean indColumnCheck, boolean indPrimaryKey, boolean indForeignKey, boolean indUniqueKey) {
		Session session = getSession();
		Transaction tx = null;
		List<Column> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Column.class);
			criteria.add(Restrictions.eq("indColumnCheck", indColumnCheck));
			criteria.add(Restrictions.eq("primaryKey", indPrimaryKey));
			criteria.add(Restrictions.eq("foreignKey", indForeignKey));
			criteria.add(Restrictions.eq("uniqueKey", indUniqueKey));
			
			@SuppressWarnings("unchecked")
			List<Column> columns = criteria.list();
			
			if (columns == null || columns.size() == 0) {
				retorno = null;
			}
			else { 
				retorno = filterColumnsByDatabase(databaseId, columns);
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
	
	public List<Column> getByIndDescriptionAndIndColumnCheck(Long databaseId, boolean indDescription, boolean indColumnCheck) {
		Session session = getSession();
		Transaction tx = null;
		List<Column> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Column.class);
			criteria.add(Restrictions.eq("indDescription", indDescription));
			criteria.add(Restrictions.eq("indColumnCheck", indColumnCheck));
			
			@SuppressWarnings("unchecked")
			List<Column> columns = criteria.list();
			
			if (columns == null || columns.size() == 0) {
				retorno = null;
			}
			else {
				retorno = filterColumnsByDatabase(databaseId, columns);
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
	
	public List<Column> filterColumnsByDatabase(Long databaseId, List<Column> columns) {
		List<Column> retorno = new ArrayList<Column>();
		
		for (Column column : columns) {
			// Buscar apenas as colunas do database passado.
			if (column.getTable().getDatabase().getId() == databaseId) {
				retorno.add(column);
			}
		}
		
		return retorno;
	}
	
	public List<Column> getByIndPrimarykey(Long databaseId, boolean indPrimaryKey) {
		Session session = getSession();
		Transaction tx = null;
		List<Column> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Column.class);
			criteria.add(Restrictions.eq("primaryKey", indPrimaryKey));
			
			@SuppressWarnings("unchecked")
			List<Column> columns = criteria.list();
			
			if (columns == null || columns.size() == 0) {
				retorno = null;
			}
			else {
				retorno = filterColumnsByDatabase(databaseId, columns);
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
	
	public List<Column> getByIndForeignkey(Long databaseId, boolean indForeignKey) {
		Session session = getSession();
		Transaction tx = null;
		List<Column> retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Column.class);
			criteria.add(Restrictions.eq("foreignKey", indForeignKey));
			
			@SuppressWarnings("unchecked")
			List<Column> columns = criteria.list();
			
			if (columns == null || columns.size() == 0) {
				retorno = null;
			}
			else {
				retorno = filterColumnsByDatabase(databaseId, columns);
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
	
	public Column getByIndPrimaryKeyTableAndId(Column column) {
		Session session = getSession();
		Transaction tx = null;
		Column retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Column.class);
			criteria.add(Restrictions.eq("primaryKey", column.isPrimaryKey()));
			criteria.add(Restrictions.eq("table.id", column.getTable().getId()));
			criteria.add(Restrictions.ne("id", column.getId()));
			
			try {
				retorno = (Column) criteria.list().get(0);
			}
			catch(Exception e) {
				retorno = null;
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}

		return retorno;
	}
}