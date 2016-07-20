package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t010_table_db_domain")
public class TableDatabaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c008_database_domain_id", nullable = false)
	private DatabaseDomain databaseDomain;

	@Id
	@ManyToOne
	@JoinColumn(name = "c002_table_id", nullable = false)
	private Table table;

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof TableDatabaseDomain)) {
			return false;
		}
		
		TableDatabaseDomain other = (TableDatabaseDomain) o;
		
	    if (this.databaseDomain.getId() != other.databaseDomain.getId()) {
	    	return false;
	    }
	    
	    if (this.table.getId() != other.table.getId()) {
	    	return false;
	    }

	    return true;
	}

	public int hashCode() {
	    return (int) this.databaseDomain.getId().hashCode() * this.table.getId().hashCode();
	}

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