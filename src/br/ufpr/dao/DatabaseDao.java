package br.ufpr.dao;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Database;

public class DatabaseDao extends GenericDao {
	
	public Database get(Long id){
		Criteria criteria = getSession().createCriteria(Database.class);
		criteria.add(Restrictions.eq("id", id));
		return (Database) criteria.list().get(0);
	}

	public Database getByName(String name){
		Criteria criteria = getSession().createCriteria(Database.class);
		criteria.add(Restrictions.eq("name", name));
		return (Database) criteria.list().get(0);
	}
	
	public void cleanDataBase() throws SQLException {
		String[] sqls = new String[7];
		
		//SELECT * FROM rdbtoonto.t011_class;
		sqls[0] = "DELETE FROM t009_column_check_value";
		sqls[1] = "DELETE FROM t006_check_value";
		sqls[2] = "DELETE FROM t003_column";
		sqls[3] = "DELETE FROM t010_table_db_domain";
		sqls[4] = "DELETE FROM t008_database_domain";
		sqls[5] = "DELETE FROM t002_table";
		sqls[6] = "DELETE FROM t001_database";
		
		executeQuery(sqls);
	}
}