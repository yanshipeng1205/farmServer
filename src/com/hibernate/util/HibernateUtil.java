package com.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry=null; 
	static{
		Configuration configuration=new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
	}
	
	private HibernateUtil(){}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public static Session beginTransaction()
	{
		Session session=getSession();
		session.beginTransaction();
		return session;
	}
	
	public static void commitTransaction()
	{
		getSession().getTransaction().commit();
	}
	
	public static void rollbackTransaction()
	{
		getSession().getTransaction().rollback();
	}
	
	public static Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public static void closeSession()
	{
		getSession().close();
	}
}
