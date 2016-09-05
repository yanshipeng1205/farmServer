package com.hibernate.manager;

public interface UsersManager {
	public boolean verify(String account, String password);

	public boolean register(String account, String password, String name, String email);
}
