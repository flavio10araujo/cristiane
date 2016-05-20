package br.ufpr.dao;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;

public class DatabaseDao extends GenericDao {
	
	public Database get(Long id){
		Session session = getSession();
		Transaction tx = null;
		Database retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Database.class);
			criteria.add(Restrictions.eq("id", id));
			retorno = (Database) criteria.list().get(0);
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

	public Database getByName(String name){
		Session session = getSession();
		Transaction tx = null;
		Database retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Database.class);
			criteria.add(Restrictions.eq("name", name));
			retorno = (Database) criteria.list().get(0);
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
	
	public void cleanDataBase() throws SQLException {
		String[] sqls = new String[21];
		
		sqls[0] = "DELETE FROM t009_column_check_value";
		sqls[1] = "DELETE FROM t006_check_value";
		sqls[2] = "DELETE FROM t012_hierarchy";
		sqls[3] = "DELETE FROM t015_instance";
		sqls[4] = "DELETE FROM t014_datatype_property_domain";
		sqls[5] = "DELETE FROM t022_object_property_domain_range";
		sqls[6] = "DELETE FROM t011_class";
		sqls[7] = "DELETE FROM t020_column_to_datatype_property";
		sqls[8] = "DELETE FROM t021_column_to_object_property";
		sqls[9] = "DELETE FROM t019_object_property";
		sqls[10] = "DELETE FROM t022_object_property_domain_range";
		sqls[11] = "DELETE FROM t003_column";
		sqls[12] = "DELETE FROM t010_table_db_domain";
		sqls[13] = "DELETE FROM t008_database_domain";
		sqls[14] = "DELETE FROM t002_table";
		sqls[15] = "DELETE FROM t013_datatype_property";
		sqls[16] = "DELETE FROM t016_ontology";
		sqls[17] = "DELETE FROM t001_database";
		sqls[18] = "DELETE FROM t007_check_subject";
		sqls[19] = "DELETE FROM t018_datatype_onto";
		sqls[20] = "DELETE FROM t005_datatype_db";

		executeQuery(sqls);
	}
}