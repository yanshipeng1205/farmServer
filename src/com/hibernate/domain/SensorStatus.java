package com.hibernate.domain;

import java.io.Serializable;

public class SensorStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Farms farmId;
	private java.sql.Date date;
	private java.sql.Time time;
	private Integer lightIntensity;
	private Integer airTemperature;
	private Integer airHumidity;
	private Integer soilTemperature;
	private Integer soilHumidity;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public Farms getFarmId() {
		return farmId;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public java.sql.Time getTime() {
		return time;
	}
	public Integer getLightIntensity() {
		return lightIntensity;
	}
	public Integer getAirTemperature() {
		return airTemperature;
	}
	public Integer getAirHumidity() {
		return airHumidity;
	}
	public Integer getSoilTemperature() {
		return soilTemperature;
	}
	public Integer getSoilHumidity() {
		return soilHumidity;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFarmId(Farms farmId) {
		this.farmId = farmId;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public void setTime(java.sql.Time time) {
		this.time = time;
	}
	public void setLightIntensity(Integer lightIntensity) {
		this.lightIntensity = lightIntensity;
	}
	public void setAirTemperature(Integer airTemperature) {
		this.airTemperature = airTemperature;
	}
	public void setAirHumidity(Integer airHumidity) {
		this.airHumidity = airHumidity;
	}
	public void setSoilTemperature(Integer soilTemperature) {
		this.soilTemperature = soilTemperature;
	}
	public void setSoilHumidity(Integer soilHumidity) {
		this.soilHumidity = soilHumidity;
	}

	
	
	
}
