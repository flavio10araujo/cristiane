package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t020_column_to_datatype_property")
public class ColumnToDatatypeProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c003_column_id", nullable = false)
	private Column column;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "c013_datatype_property_id", nullable = false)
	private DatatypeProperty datatypeProperty;
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof ColumnToDatatypeProperty)) {
			return false;
		}
		
		ColumnToDatatypeProperty other = (ColumnToDatatypeProperty) o;
		
	    if (this.column.getId() != other.column.getId()) {
	    	return false;
	    }
	    
	    if (this.datatypeProperty.getId() != other.datatypeProperty.getId()) {
	    	return false;
	    }

	    return true;
	}
	
	public int hashCode() {
	    return (int) this.column.getId().hashCode() * this.datatypeProperty.getId().hashCode();
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public DatatypeProperty getDatatypeProperty() {
		return datatypeProperty;
	}

	public void setDatatypeProperty(DatatypeProperty datatypeProperty) {
		this.datatypeProperty = datatypeProperty;
	}
}