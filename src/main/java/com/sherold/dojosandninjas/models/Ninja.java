package com.sherold.dojosandninjas.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // Designates class as DB object 
@Table(name="ninjas") // Maps table for DB object
public class Ninja {
	// <----- Attributes ----->
	@Id // DB id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long id;
	@NotNull(message="First name cannot be null!")
	@Size(min=2, max=20, message="First Name must be between 2 and 20 characters!")
	private String firstName;
	@NotNull(message="Last name cannot be null!")
	@Size(min=2, max=20, message="Last Name must be between 2 and 20 characters!")
	private String lastName;
	@NotNull(message="Age cannot be blank!")
	@Min(18)
	@Max(150)
	private Integer age;
	@Column(updatable=false) // Data is immutable after initial instantiation
	private Date createdAt;
	private Date updatedAt;
	// Established n:1 relationship
	// FetchType.LAZY = established relationship when assigned
	// JoinColumn links the PK to the relationship table ID
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dojo_id")
	private Dojo dojo;
	
	// <----- Constructors ----->
	public Ninja() {
	}

	public Ninja(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Ninja(String firstName, String lastName, Integer age, Dojo dojo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dojo = dojo;
	}
	
	// <----- Getters/Setters ----->
	// firstName
	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	// lastName
	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	// age
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	// dojo
	public Dojo getDojo() {
		return dojo;
	}

	public void setDojo(Dojo dojo) {
		this.dojo = dojo;
	}

	// Getters only
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
