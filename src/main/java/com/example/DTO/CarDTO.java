package com.example.DTO;

public class CarDTO {
	private String model;
	private Long id;

	public CarDTO(String model, Long id) {
		super();
		this.model = model;
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
