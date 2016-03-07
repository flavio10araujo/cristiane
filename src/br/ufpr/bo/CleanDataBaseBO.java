package br.ufpr.bo;

import java.sql.SQLException;

import br.ufpr.dao.DatabaseDao;

public class CleanDataBaseBO {
	
	DatabaseDao databaseDao = new DatabaseDao();

	/**
	 * Fun��o utilizada para apagar todos os dados do banco.
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public void cleanDataBase() throws SQLException {
		databaseDao.cleanDataBase();
	}
}