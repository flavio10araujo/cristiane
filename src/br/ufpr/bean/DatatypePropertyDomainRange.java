package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t014_datatype_property_domain_range")
public class DatatypePropertyDomainRange implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c013_datatype_property_id")
	private DatatypeProperty datatypeProperty;
	
	@ManyToOne
	@JoinColumn(name = "c011_class_id_domain")
	private br.ufpr.bean.Class classDomain;
	
	@ManyToOne
	@JoinColumn(name = "c011_class_id_range")
	private br.ufpr.bean.Class classRange;

	public DatatypeProperty getDatatypeProperty() {
		return datatypeProperty;
	}

	public void setDatatypeProperty(DatatypeProperty datatypeProperty) {
		this.datatypeProperty = datatypeProperty;
	}

	public br.ufpr.bean.Class getClassDomain() {
		return classDomain;
	}

	public void setClassDomain(br.ufpr.bean.Class classDomain) {
		this.classDomain = classDomain;
	}

	public br.ufpr.bean.Class getClassRange() {
		return classRange;
	}

	public void setClassRange(br.ufpr.bean.Class classRange) {
		this.classRange = classRange;
	}
}