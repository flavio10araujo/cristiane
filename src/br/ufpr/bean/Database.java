package br.ufpr.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t001_database")
public class Database implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c001_database_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "c001_database_name", nullable = false)
	private String name;

	@OneToMany(mappedBy="database")
	private Set<DatabaseDomain> domains;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DatabaseDomain> getDomains() {
		if (domains == null) {
			domains = new HashSet<DatabaseDomain>();
		}
		return domains;
	}

	public void setDomains(Set<DatabaseDomain> domains) {
		this.domains = domains;
	}

}
