package com.hibernate.dao;

import org.hibernate.Query;

import com.hibernate.domain.Controllers;
import com.hibernate.domain.SensorStatus;
import com.hibernate.util.HibernateUtil;

public class ControllersImpl extends GenericDaoImpl<Controllers, Integer> implements ControllersDao {

	@Override
	public Controllers getNearestData(int farmId) {
		String sql= "select c from com.hibernate.domain.Controllers c "
					+" where id = (select max(id) from c where farmId.id=:farmId)";
		Query query = HibernateUtil.getSession().createQuery(sql)
					  .setParameter("farmId", farmId);
System.out.println("farmId:"+farmId);
System.out.println("Controller:getNearestData:"+findOne(query).getId());
		return findOne(query);
	}
	
}
