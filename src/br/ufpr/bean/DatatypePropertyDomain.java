package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t014_datatype_property_domain")
public class DatatypePropertyDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c013_datatype_property_id")
	private DatatypeProperty datatypeProperty;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "c011_class_id_domain")
	private br.ufpr.bean.Class classDomain;
	
	@Column(name = "c014_datatype_subproperty_of")
	private boolean datatypeSubpropertyOf;
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof DatatypePropertyDomain)) {
			return false;
		}
		
		DatatypePropertyDomain other = (DatatypePropertyDomain) o;
		
	    if (this.datatypeProperty.getId() != other.datatypeProperty.getId()) {
	    	return false;
	    }
	    
	    if (this.classDomain.getId() != other.classDomain.getId()) {
	    	return false;
	    }

	    return true;
	}
	
	public int hashCode() {
	    return (int) this.datatypeProperty.getId().hashCode() * this.classDomain.getId().hashCode();
	}

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

	public boolean isDatatypeSubpropertyOf() {
		return datatypeSubpropertyOf;
	}

	public void setDatatypeSubpropertyOf(boolean datatypeSubpropertyOf) {
		this.datatypeSubpropertyOf = datatypeSubpropertyOf;
	}
}