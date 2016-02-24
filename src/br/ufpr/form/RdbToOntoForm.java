package br.ufpr.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class RdbToOntoForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String databaseName;
	private FormFile databaseStructure;
	private FormFile databaseRecords;
	
	public String getDatabaseName() {
		return databaseName;
	}
	
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	public FormFile getDatabaseStructure() {
		return databaseStructure;
	}
	
	public void setDatabaseStructure(FormFile databaseStructure) {
		this.databaseStructure = databaseStructure;
	}
	
	public FormFile getDatabaseRecords() {
		return databaseRecords;
	}
	
	public void setDatabaseRecords(FormFile databaseRecords) {
		this.databaseRecords = databaseRecords;
	}
}