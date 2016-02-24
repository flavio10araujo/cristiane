package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t006_check_value")
public class CheckValue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c006_check_value_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "c006_description", nullable = false)
	private String description;

	@Column(name = "c006_abreviation", nullable = true)
	private String abreviation;

	private CheckSubject checkSubject;

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

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public CheckSubject getCheckSubject() {
		return checkSubject;
	}

	public void setCheckSubject(CheckSubject checkSubject) {
		this.checkSubject = checkSubject;
	}

}
