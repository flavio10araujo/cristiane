package br.ufpr.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ufpr.bean.Type;
import br.ufpr.util.Util;

public class TypeDao extends GenericDao {

	public Type getByNameDatabase(String description) {
		if (description == null || "".equals(description)) {
			return null;
		}
		
		Session session = getSession();
		Transaction tx = null;
		Type retorno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Type.class);
			criteria.add(Restrictions.eq("nameDatabase", description.toLowerCase()));
			
			try {
				retorno = (Type) criteria.list().get(0);
			}
			catch (Exception e) {
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
	
	@SuppressWarnings("rawtypes")
	public int getQtd() throws SQLException {
		String sql = "SELECT COUNT(1) QTD FROM tb_type";
		ArrayList<Map> res = executeQuery(sql);
		Iterator<Map> iter = res.iterator();
		int qtd = 0;

		while(iter.hasNext()) {
			Map row = (Map) iter.next();
			qtd = Integer.parseInt(Util.getNullText(row.get("QTD")));
		}

		return qtd;
	}
	
	public void insertTypes() throws SQLException {
		String[] sqls = new String[32];
		
		sqls[0] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('char', 'string')";
		sqls[1] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('nchar', 'string')";
		sqls[2] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('varchar2', 'string')";
		sqls[3] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('varchar', 'string')";
		sqls[4] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('nvarchar', 'string')";
		sqls[5] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('nvarchar2', 'string')";
		sqls[6] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('lob', 'string')";
		sqls[7] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('long', 'string')";
		sqls[8] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('text', 'string')";
		sqls[9] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('tinytext', 'string')";
		sqls[10] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('mediumtext', 'string')";
		sqls[11] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('longtext', 'string')";
		
		sqls[12] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('bit', 'boolean')";
		sqls[13] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('byte', 'boolean')";
		sqls[14] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('boolean', 'boolean')";
		
		sqls[15] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('int', 'decimal')";
		sqls[16] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('integer', 'decimal')";
		sqls[17] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('mediumint', 'decimal')";
		sqls[18] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('smallint', 'decimal')";
		sqls[19] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('tinyint', 'decimal')";
		sqls[20] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('bigint', 'decimal')";
		sqls[21] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('decimal', 'decimal')";
		sqls[22] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('numeric', 'decimal')";
		
		sqls[23] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('float', 'float')";
		sqls[24] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('real', 'float')";
		sqls[25] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('number', 'float')";
		
		sqls[26] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('double', 'double')";
		
		sqls[27] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('year', 'date')";
		sqls[28] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('date', 'date')";
		
		sqls[29] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('time', 'time')";
		
		sqls[30] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('timestamp', 'datetime')";
		
		sqls[31] = "INSERT INTO tb_type (name_database, name_xml) VALUES ('interval', 'duration')";

		executeQuery(sqls);
	}
}