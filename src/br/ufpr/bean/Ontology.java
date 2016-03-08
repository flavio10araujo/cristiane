package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t016_ontology")
public class Ontology implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@javax.persistence.Column(name = "c016_ontology_id", nullable = false, unique = true)
	private Long id;

	@javax.persistence.Column(name = "c016_ontology_name", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "c001_database_id")
	private Database database;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
}