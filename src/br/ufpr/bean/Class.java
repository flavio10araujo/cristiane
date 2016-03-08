package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t011_class")
public class Class implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@javax.persistence.Column(name = "c011_class_id", nullable = false, unique = true)
	private Long id;
	
	@javax.persistence.Column(name = "c011_class_name", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "c008_database_domain_id")
	private DatabaseDomain databaseDomain;
	
	@ManyToOne
	@JoinColumn(name = "c003_column_id")
	private Column column;
	
	@ManyToOne
	@JoinColumn(name = "c016_ontology_id")
	private Ontology ontology;
	
	@ManyToOne
	@JoinColumn(name = "c002_table_id")
	private Table table;
	
	@ManyToOne
	@JoinColumn(name = "c007_check_subject_id")
	private CheckSubject checkSubject;

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

	public DatabaseDomain getDatabaseDomain() {
		return databaseDomain;
	}

	public void setDatabaseDomain(DatabaseDomain databaseDomain) {
		this.databaseDomain = databaseDomain;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public CheckSubject getCheckSubject() {
		return checkSubject;
	}

	public void setCheckSubject(CheckSubject checkSubject) {
		this.checkSubject = checkSubject;
	}
}