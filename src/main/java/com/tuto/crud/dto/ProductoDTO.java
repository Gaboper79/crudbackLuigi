package com.tuto.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDTO {

	@NotBlank
	private String name;

	@Min(0)
	private float price;

	public ProductoDTO(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public ProductoDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
