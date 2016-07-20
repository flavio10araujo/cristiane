package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t022_object_property_domain_range")
public class ObjectPropertyDomainRange implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c019_object_property_id")
	private ObjectProperty objectProperty;
	
	@ManyToOne
	@JoinColumn(name = "c011_class_id_domain")
	private br.ufpr.bean.Class classDomain;
	
	@ManyToOne
	@JoinColumn(name = "c011_class_id_range")
	private br.ufpr.bean.Class classRange;
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof ObjectPropertyDomainRange)) {
			return false;
		}
		
		ObjectPropertyDomainRange other = (ObjectPropertyDomainRange) o;
		
	    if (this.objectProperty.getId() != other.objectProperty.getId()) {
	    	return false;
	    }

	    return true;
	}
	
	public int hashCode() {
	    return (int) this.objectProperty.getId().hashCode();
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

	public ObjectProperty getObjectProperty() {
		return objectProperty;
	}

	public void setObjectProperty(ObjectProperty objectProperty) {
		this.objectProperty = objectProperty;
	}
}