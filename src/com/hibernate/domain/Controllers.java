package com.hibernate.domain;

import java.io.Serializable;

public class Controllers implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Farms farmId;
	private java.sql.Date date;
	private java.sql.Time time;
	private Integer pumpDuty;
	private Integer lightLevel;
	private Integer fanDuty;
	private Integer steerDuty;
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
	public Integer getPumpDuty() {
		return pumpDuty;
	}
	public Integer getLightLevel() {
		return lightLevel;
	}
	public Integer getFanDuty() {
		return fanDuty;
	}
	public Integer getSteerDuty() {
		return steerDuty;
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
	public void setPumpDuty(Integer pumpDuty) {
		this.pumpDuty = pumpDuty;
	}
	public void setLightLevel(Integer lightLevel) {
		this.lightLevel = lightLevel;
	}
	public void setFanDuty(Integer fanDuty) {
		this.fanDuty = fanDuty;
	}
	public void setSteerDuty(Integer steerDuty) {
		this.steerDuty = steerDuty;
	}
	
	
	
}
