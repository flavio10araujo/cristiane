package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t021_column_to_object_property")
public class ColumnToObjectProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c003_column_id", nullable = false)
	private Column column;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "c019_object_property_id", nullable = false)
	private ObjectProperty objectProperty;
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof ColumnToObjectProperty)) {
			return false;
		}
		
		ColumnToObjectProperty other = (ColumnToObjectProperty) o;
		
	    if (this.column.getId() != other.column.getId()) {
	    	return false;
	    }
	    
	    if (this.objectProperty.getId() != other.objectProperty.getId()) {
	    	return false;
	    }

	    return true;
	}
	
	public int hashCode() {
	    return (int) this.column.getId().hashCode() * this.objectProperty.getId().hashCode();
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public ObjectProperty getObjectProperty() {
		return objectProperty;
	}

	public void setObjectProperty(ObjectProperty objectProperty) {
		this.objectProperty = objectProperty;
	}
}