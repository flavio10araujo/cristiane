package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t009_column_check_value")
public class ColumnCheckValue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "c003_column_id", nullable = false)
	private Column column;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "c006_check_value_id", nullable = false)
	private CheckValue checkValue;
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if(!(o instanceof ColumnCheckValue)) {
			return false;
		}
		
		ColumnCheckValue other = (ColumnCheckValue) o;
		
	    if (this.column.getId() != other.column.getId()) {
	    	return false;
	    }
	    
	    if (this.checkValue.getId() != other.checkValue.getId()) {
	    	return false;
	    }

	    return true;
	}
	
	public int hashCode() {
	    return (int) this.column.getId().hashCode() * this.checkValue.getId().hashCode();
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public CheckValue getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(CheckValue checkValue) {
		this.checkValue = checkValue;
	}
}