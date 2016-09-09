package com.hibernate.dao;

import org.hibernate.Query;

import com.hibernate.domain.SensorStatus;
import com.hibernate.util.HibernateUtil;

public class SensorStatusImpl extends GenericDaoImpl<SensorStatus, Integer> implements SensorStatusDao {

	@Override
	public SensorStatus getNearestData(int farmId) {
		String sql="select s from com.hibernate.domain.SensorStatus s"
				+" where id=(select max(id) from s where farmId.id=:farmId )";

		Query querySensor = HibernateUtil.getSession().createQuery(sql)
					  .setParameter("farmId", farmId);
		return findOne(querySensor);
	}

}
