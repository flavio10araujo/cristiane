package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "t010_table_db_domain")
public class TableDatabaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private DatabaseDomain databaseDomain;
	private Table table;

	public DatabaseDomain getDatabaseDomain() {
		return databaseDomain;
	}

	public void setDatabaseDomain(DatabaseDomain databaseDomain) {
		this.databaseDomain = databaseDomain;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
