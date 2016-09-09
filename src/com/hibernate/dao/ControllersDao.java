package com.hibernate.dao;

import com.hibernate.domain.Controllers;

public interface ControllersDao extends GenericDao<Controllers, Integer> {
	public Controllers getNearestData(int farmId);

}
