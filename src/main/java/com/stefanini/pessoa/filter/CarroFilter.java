package com.stefanini.pessoa.filter;


import java.io.Serializable;
import java.sql.Date;



public class CarroFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idcarro;
	public String manufacturer;
	public String model;
	public Long modelyear;
	public String category;
	
	public Long getIdcarro() {
		return idcarro;
	}
	public void setIdcarro(Long idcarro) {
		this.idcarro = idcarro;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Long getModelyear() {
		return modelyear;
	}
	public void setModelyear(Long modelyear) {
		this.modelyear = modelyear;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
