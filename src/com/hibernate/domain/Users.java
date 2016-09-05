package com.hibernate.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	private String account;
	private String password;
	private Date   registerDate;
	private String name;//nickname
	private Integer onlineStatus;
	private String email;
	public Users()
	{
		onlineStatus=new Integer(0);
	}
	public String getAccount() {
		return account;
	}
	public String getPassword() {
		return password;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public String getName() {
		return name;
	}
	public Integer getOnlineStatus() {
		return onlineStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//把获得当前日期的函数放哪里比较好
	public static java.sql.Date getCurrentSqlDate()
	{
		Calendar calendar = Calendar.getInstance();
		java.util.Date curTime  = calendar.getTime();
		java.sql.Date curDate = new java.sql.Date(curTime.getTime());
		return curDate;
	}
	
}
	