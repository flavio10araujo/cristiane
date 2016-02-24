package br.ufpr.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t007_check_subject")
public class CheckSubject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c007_check_subject_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "c007_description", nullable = false)
	private String description;

}
