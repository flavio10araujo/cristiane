package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t018_datatype_onto")
public class DatatypeOnto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c018_datatype_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "c018_description", nullable = false)
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name = "c005_datatype_db_id")
	private DatatypeDb datatypeDb;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DatatypeDb getDatatypeDb() {
		return datatypeDb;
	}

	public void setDatatypeDb(DatatypeDb datatypeDb) {
		this.datatypeDb = datatypeDb;
	}
}