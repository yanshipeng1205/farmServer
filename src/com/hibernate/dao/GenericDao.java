package com.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface GenericDao <T, PK extends Serializable>{
	public PK create(T newInstance);
	
	public void update(T transientObject);
	
	public void delete(T persistentObject);
	
	public T findOne(Query query);
	
	public List<T> findMany(Query query);

	public T read(Class<T> type, PK id);
}
