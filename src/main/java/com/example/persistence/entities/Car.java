package com.example.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String model;

	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@JsonIgnore
	private Employee emp;

	public Car() {

	}

	public Car(String model) {
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Employee getEmp() {
		return this.emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
}
