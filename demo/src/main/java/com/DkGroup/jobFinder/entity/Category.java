package com.DkGroup.jobFinder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedNativeQuery(name = "category.findById", query = "SELECT name FROM positions_category WHERE id = ?1")
@Table(name = "positions_category")
public class Category {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value = "id")
	private int id;
	
	@Column(name="name")
	@JsonProperty(value = "name")
	private String name;
	
	public Category() {}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
