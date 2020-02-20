package com.stefanini.pessoa.model;

import java.beans.Transient;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Carro {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idcarro;
	
	@NotNull
	@Column(name = "manufacturer")
	private String manufacturer;
	
	@NotNull
	private String model;	
	
	@NotNull
	private Long modelyear;
	
	@NotNull
	private String category;
	
	private BigDecimal weekday;
	private BigDecimal weekdayloyalty;
	private BigDecimal weekendday;
	private BigDecimal weekenddayloyalty;
	
	@Column(insertable = false, updatable = false)	
	private String weekdayaux;
	@Column(insertable = false, updatable = false)
	private String weekdayloyaltyaux;
	@Column(insertable = false, updatable = false)
	private String weekenddayaux;
	@Column(insertable = false, updatable = false)
	private String weekenddayloyaltyaux;
	
		
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
		
	public BigDecimal getWeekday() {
		return weekday;
	}
	public void setWeekday(BigDecimal weekday) {
		this.weekday = weekday;
	}
	public BigDecimal getWeekdayloyalty() {
		return weekdayloyalty;
	}
	public void setWeekdayloyalty(BigDecimal weekdayloyalty) {
		this.weekdayloyalty = weekdayloyalty;
	}
	public BigDecimal getWeekendday() {
		return weekendday;
	}
	public void setWeekendday(BigDecimal weekendday) {
		this.weekendday = weekendday;
	}
	public BigDecimal getWeekenddayloyalty() {
		return weekenddayloyalty;
	}
	public void setWeekenddayloyalty(BigDecimal weekenddayloyalty) {
		this.weekenddayloyalty = weekenddayloyalty;
	}
	
	@Transient 
	public String getWeekdayaux() {
		return weekdayaux;
	}
	public void setWeekdayaux(String weekdayaux) {
		this.weekdayaux = weekdayaux;
	}
	
	@Transient 
	public String getWeekdayloyaltyaux() {
		return weekdayloyaltyaux;
	}
	
	
	public void setWeekdayloyaltyaux(String weekdayloyaltyaux) {
		this.weekdayloyaltyaux = weekdayloyaltyaux;
	}
	
	@Transient 
	public String getWeekenddayaux() {
		return weekenddayaux;
	}
	public void setWeekenddayaux(String weekenddayaux) {
		this.weekenddayaux = weekenddayaux;
	}
	
	@Transient 
	public String getWeekenddayloyaltyaux() {
		return weekenddayloyaltyaux;
	}
		
	public void setWeekenddayloyaltyaux(String weekenddayloyaltyaux) {
		this.weekenddayloyaltyaux = weekenddayloyaltyaux;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcarro == null) ? 0 : idcarro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (idcarro == null) {
			if (other.idcarro != null)
				return false;
		} else if (!idcarro.equals(other.idcarro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Carro [idcarro=" + idcarro + ", manufacturer=" + manufacturer + ", model=" + model + ", modelyear="
				+ modelyear + ", category=" + category + ", weekday=" + weekday + ", weekdayloyalty=" + weekdayloyalty
				+ ", weekendday=" + weekendday + ", weekenddayloyalty=" + weekenddayloyalty + "]";
	}
	
	
	
}
