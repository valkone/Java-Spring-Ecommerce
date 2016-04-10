package com.valentin.shop.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable{

	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "ROLE")
	private String role;

	private static final long serialVersionUID = 3;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
