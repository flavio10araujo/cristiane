package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t012_hierarchy")
public class Hierarchy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "c011_super_class_id")
	private br.ufpr.bean.Class superClass;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "c011_sub_class_id")
	private br.ufpr.bean.Class subClass;
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof Hierarchy)) {
			return false;
		}
		
		Hierarchy other = (Hierarchy) o;
		
	    if (this.superClass.getId() != other.superClass.getId()) {
	    	return false;
	    }
	    
	    if (this.subClass.getId() != other.subClass.getId()) {
	    	return false;
	    }

	    return true;
	}
	
	public int hashCode() {
	    return (int) this.superClass.getId().hashCode() * this.subClass.getId().hashCode();
	}

	public br.ufpr.bean.Class getSuperClass() {
		return superClass;
	}

	public void setSuperClass(br.ufpr.bean.Class superClass) {
		this.superClass = superClass;
	}

	public br.ufpr.bean.Class getSubClass() {
		return subClass;
	}

	public void setSubClass(br.ufpr.bean.Class subClass) {
		this.subClass = subClass;
	}
}