package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "t003_column")
public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@javax.persistence.Column(name = "c003_column_id", nullable = false, unique = true)
	private Long id;

	@javax.persistence.Column(name = "c003_physical_name", nullable = false)
	private String physicalName;

	@javax.persistence.Column(name = "c003_logical_name", nullable = false)
	private String logicalName;
	
	@javax.persistence.Column(name = "c003_logical_name_2", nullable = false)
	private String logicalName2;

	@ManyToOne
	@JoinColumn(name = "c002_table_id")
	private Table table;

	@javax.persistence.Column(name = "c003_ind_primary_key", nullable = false)
	private boolean primaryKey;

	@javax.persistence.Column(name = "c003_ind_foreign_key", nullable = false)
	private boolean foreignKey;

	@ManyToOne
	@JoinColumn(name = "c002_fk_table_id")
	private Table fkTable;

	@ManyToOne
	@JoinColumn(name = "c005_datatype_id")
	private Datatype datatype;
	
	@javax.persistence.Column(name = "c003_ind_description", nullable = false)
	private boolean indDescription;
	
	@javax.persistence.Column(name = "c003_ind_associative_key", nullable = false)
	private boolean indAssociativeKey;
	
	@javax.persistence.Column(name = "c003_ak_column_id_1")
	private Column akColumnId1;
	
	@javax.persistence.Column(name = "c003_ak_column_n")
	private Column akColumnN;
	
	@javax.persistence.Column(name = "c003_ind_column_check", nullable = false)
	private boolean indColumnCheck;
	
	@ManyToOne
	@JoinColumn(name = "c002_ak_table_id_1")
	private Table akTableId1;
	
	@ManyToOne
	@JoinColumn(name = "c002_ak_table_id_n")
	private Table akTableIdN;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhysicalName() {
		return physicalName;
	}

	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}

	public String getLogicalName() {
		return logicalName;
	}

	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	public String getLogicalName2() {
		return logicalName2;
	}

	public void setLogicalName2(String logicalName2) {
		this.logicalName2 = logicalName2;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public Table getFkTable() {
		return fkTable;
	}

	public void setFkTable(Table fkTable) {
		this.fkTable = fkTable;
	}

	public Datatype getDatatype() {
		return datatype;
	}

	public void setDatatype(Datatype datatype) {
		this.datatype = datatype;
	}

	public boolean isIndDescription() {
		return indDescription;
	}

	public void setIndDescription(boolean indDescription) {
		this.indDescription = indDescription;
	}

	public boolean isIndAssociativeKey() {
		return indAssociativeKey;
	}

	public void setIndAssociativeKey(boolean indAssociativeKey) {
		this.indAssociativeKey = indAssociativeKey;
	}
	
	public Column getAkColumnId1() {
		return akColumnId1;
	}

	public void setAkColumnId1(Column akColumnId1) {
		this.akColumnId1 = akColumnId1;
	}

	public Column getAkColumnN() {
		return akColumnN;
	}

	public void setAkColumnN(Column akColumnN) {
		this.akColumnN = akColumnN;
	}

	public boolean isIndColumnCheck() {
		return indColumnCheck;
	}

	public void setIndColumnCheck(boolean indColumnCheck) {
		this.indColumnCheck = indColumnCheck;
	}

	public Table getAkTableId1() {
		return akTableId1;
	}

	public void setAkTableId1(Table akTableId1) {
		this.akTableId1 = akTableId1;
	}

	public Table getAkTableIdN() {
		return akTableIdN;
	}

	public void setAkTableIdN(Table akTableIdN) {
		this.akTableIdN = akTableIdN;
	}
}