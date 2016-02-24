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
@javax.persistence.Table(name = "t002_table")
public class Table implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c002_table_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "c002_table_type", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "c001_database_id")
	private Database database;

	@Column(name = "c002_physical_name", nullable = false)
	private String physicalName;

	@Column(name = "c002_logical_name", nullable = false)
	private String logicalName;

	@Column(name = "c002_ind_associative", nullable = false)
	private boolean associative;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public String getPhysicalName() {
		return physicalName;
	}

	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}

	public String getLogicalName() {
		return logicalName;
	}

	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	public boolean isAssociative() {
		return associative;
	}

	public void setAssociative(boolean associative) {
		this.associative = associative;
	}
}