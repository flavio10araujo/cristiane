package br.ufpr.bean;

import java.io.Serializable;

public class DatabaseStructureVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String physicalType;
	private String namePhysicalElement;
	
	public String getPhysicalType() {
		return physicalType;
	}
	public void setPhysicalType(String physicalType) {
		this.physicalType = physicalType;
	}
	public String getNamePhysicalElement() {
		return namePhysicalElement;
	}
	public void setNamePhysicalElement(String namePhysicalElement) {
		this.namePhysicalElement = namePhysicalElement;
	}

}
