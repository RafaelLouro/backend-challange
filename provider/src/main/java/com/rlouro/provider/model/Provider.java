package com.rlouro.provider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PROVIDER")
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PR_ID")
	private Long id;

	@Size(max = 50)
	@Column(name = "PR_NAME", nullable = false)
	private String name;

	@Size(max = 100)
	@Column(name = "PR_ADDRESS", nullable = false)
	private String address;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Provider() {
	}

	public Provider(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
}
