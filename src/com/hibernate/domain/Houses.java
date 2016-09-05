package com.hibernate.domain;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Houses implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mac;
	private String account;
	private Point2D  location;
	
	public String getMac() {
		return mac;
	}
	public String getAccount() {
		return account;
	}
	public Point2D getLocation() {
		return location;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setLocation(Point2D location) {
		this.location = location;
	}
	
}
