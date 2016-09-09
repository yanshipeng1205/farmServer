package com.hibernate.dao;

import com.hibernate.domain.SensorStatus;

public interface SensorStatusDao extends GenericDao<SensorStatus, Integer> {
	public SensorStatus getNearestData(int farmId);
}
