package br.ufpr.bo;

import br.ufpr.bean.Database;
import br.ufpr.dao.DatabaseDao;

public class CleanDataBaseBO {
	
	DatabaseDao databaseDao = new DatabaseDao();

	/**
	 * Função utilizada para apagar todos os dados relacionados ao banco passado.
	 * 
	 * @param form
	 * @return
	 */
	public Database cleanDataBase(String databaseName) {
		Database database = null;
		
		try {
			database = databaseDao.getByName(databaseName.toLowerCase());
		}
		catch (Exception e) {
			return database;
		}
		
		databaseDao.delete(database);
		
		return database;
	}
}