package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t004_record")
public class Record implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c004_record_id", nullable = false, unique = true)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "c002_table_id")
	private Table table;
	
	@Column(name = "c004_table_columns")
	private String tableColumns;
	
	@Column(name = "c004_column_values")
	private String columnvalues;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getTableColumns() {
		return tableColumns;
	}

	public void setTableColumns(String tableColumns) {
		this.tableColumns = tableColumns;
	}

	public String getColumnvalues() {
		return columnvalues;
	}

	public void setColumnvalues(String columnvalues) {
		this.columnvalues = columnvalues;
	}
}