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
@javax.persistence.Table(name = "t019_object_property")
public class ObjectProperty implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c019_object_property_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "c019_object_property_description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "c016_ontology_id")
	private Ontology ontology;
	
	@Column(name = "c019_ind_inverse_functional")
	private Boolean indInverseFunctional;
	
	@Column(name = "c019_min_cardinality")
	private Boolean minCardinality;
	
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

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}

	public Boolean getIndInverseFunctional() {
		return indInverseFunctional;
	}

	public void setIndInverseFunctional(Boolean indInverseFunctional) {
		this.indInverseFunctional = indInverseFunctional;
	}

	public Boolean getMinCardinality() {
		return minCardinality;
	}

	public void setMinCardinality(Boolean minCardinality) {
		this.minCardinality = minCardinality;
	}
}