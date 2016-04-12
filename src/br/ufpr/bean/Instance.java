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
@javax.persistence.Table(name = "t015_instance")
public class Instance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c015_instance_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "c015_instance_description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "c013_datatype_property_id")
	private DatatypeProperty datatypeProperty;
	
	@ManyToOne
	@JoinColumn(name = "c011_class_id")
	private br.ufpr.bean.Class clazz;
	
	@ManyToOne
	@JoinColumn(name = "c016_ontology_id")
	private Ontology ontology;

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

	public DatatypeProperty getDatatypeProperty() {
		return datatypeProperty;
	}

	public void setDatatypeProperty(DatatypeProperty datatypeProperty) {
		this.datatypeProperty = datatypeProperty;
	}

	public br.ufpr.bean.Class getClazz() {
		return clazz;
	}

	public void setClazz(br.ufpr.bean.Class clazz) {
		this.clazz = clazz;
	}

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}
}