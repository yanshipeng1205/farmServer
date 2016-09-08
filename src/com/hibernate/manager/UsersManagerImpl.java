package com.hibernate.manager;

import java.sql.Date;

import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.hibernate.dao.UsersDao;
import com.hibernate.dao.UsersDaoImpl;
import com.hibernate.domain.Users;
import com.hibernate.util.HibernateUtil;

public class UsersManagerImpl implements UsersManager {

	private  UsersDao usersDao = new UsersDaoImpl();
	@Override
	public  boolean verify(String account, String password) {
		try
		{
System.out.println("Account:"+account);
System.out.println("password:"+password);
			HibernateUtil.beginTransaction();
			String sql= "select u from com.hibernate.domain.Users u "
					   +"WHERE u.account= :account AND u.password= :password";
			Query query = HibernateUtil.getSession().createQuery(sql)
						.setParameter("account", account)
						.setParameter("password", password);
			Users user = usersDao.findOne(query);
System.out.println("Users Account:"+user.getAccount());
			HibernateUtil.commitTransaction();
			if(user != null)
				return true;
		}
		catch(Exception e)
		{
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());  java.lang.RuntimeException: java.lang.String cannot be cast to com.hibernate.domain.Users
		}
		return false;
	}
	@Override
	public boolean register(String account, String password, String name, String email) {
		try
		{
			HibernateUtil.beginTransaction();
			
			Users user = new Users();
			user.setAccount(account);
			user.setPassword(password);
			user.setName(name);	
			user.setEmail(email);
			user.setOnlineStatus(new Integer(0));
			user.setRegisterDate(Users.getCurrentSqlDate());
			
			HibernateUtil.getSession().save(user);
			HibernateUtil.commitTransaction();
			return true;
		}
		//java.sql.SQLException Duplicate entry 之后把这项加上
		catch(Exception e)
		{
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());  java.lang.RuntimeException: java.lang.String cannot be cast to com.hibernate.domain.Users
		}
		return false;
	}
}
