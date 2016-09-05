package com.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.hibernate.util.HibernateUtil;

public class GenericDaoImpl <T, PK extends Serializable> implements GenericDao<T, PK> {
	
		
	@Override
	public PK create(T obj) {
		return (PK)HibernateUtil.getSession().save(obj);
	}

	@Override
	public T read(Class<T> type, PK id) {
		return (T) HibernateUtil.getSession().get(type, id);	
	}

	@Override
	public void update(T obj) {
		HibernateUtil.getSession().update(obj);
	}

	@Override
	public void delete(T obj) {
		HibernateUtil.getSession().delete(obj);
	}

	@Override
	public T findOne(Query query) {
		return (T) query.uniqueResult();
	}

	@Override
	public List<T> findMany(Query query) {
		return (List<T>) query.list();
	}

}
