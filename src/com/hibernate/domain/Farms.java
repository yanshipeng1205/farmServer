package com.hibernate.domain;

import java.io.Serializable;

public class Farms implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mac;
	private Integer id;
	private Users account;
	private String farmName;
	private Double longitude;
	private Double latitude;
	
	public String getFarmName() {
		return farmName;
	}
	public String getMac() {
		return mac;
	}	
	public Users getAccount() {
		return account;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setAccount(Users account) {
		this.account = account;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
}
