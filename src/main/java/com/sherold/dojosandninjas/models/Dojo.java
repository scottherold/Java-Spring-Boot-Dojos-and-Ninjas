package com.sherold.dojosandninjas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // Designates class as DB object 
@Table(name="dojos") // Maps table for DB ojbect
public class Dojo {
	// <----- Attributes ----->
	@Id // DB id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long id;
	@NotNull(message="Dojo name cannot be null!")
	@Size(min=2, max=20, message="Dojo must be between 2 and 20 characters!")
	private String name;
	@Column(updatable=false) // Data is immutable after initial instantiation
	private Date createdAt;
	private Date updatedAt;
	// Established 1:n relationship
	// mappedBy = table to map PK
	// FetchType.LAZY = established relationship when assigned
	// "List" due to "One" object being tied to "Many" objects
	@OneToMany(mappedBy="dojo", fetch = FetchType.LAZY)
	private List<Ninja> ninjas;
	
	// <----- Constructors ----->
	public Dojo() {
	}

	public Dojo(String name) {
		this.name = name;
	}

	public Dojo(String name, List<Ninja> ninjas) {
		this.name = name;
		this.ninjas = ninjas;
	}

	// <----- Getters/Setters ----->
	// name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ninjas
	public List<Ninja> getNinjas() {
		return ninjas;
	}

	public void setNinjas(List<Ninja> ninjas) {
		this.ninjas = ninjas;
	}

	// Getters ONLY
	// id
	public Long getId() {
		return id;
	}

	// createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	// updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	// <----- Methods ----->
	@PrePersist // Method executes at time of initial instantiation
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate // Method executes at time of update
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
