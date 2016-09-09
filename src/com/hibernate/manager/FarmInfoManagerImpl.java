package com.hibernate.manager;

import com.hibernate.dao.ControllersDao;
import com.hibernate.dao.ControllersImpl;
import com.hibernate.dao.SensorStatusDao;
import com.hibernate.dao.SensorStatusImpl;
import com.hibernate.domain.Controllers;
import com.hibernate.domain.SensorStatus;
import com.hibernate.util.HibernateUtil;

import server.util.FarmInfo;

public class FarmInfoManagerImpl implements FarmInfoManager {

	int farmId;
	ControllersDao controllersDao = new ControllersImpl();
	SensorStatusDao sensorStatusDao = new SensorStatusImpl();
	public FarmInfoManagerImpl(int xFarmId)
	{
		farmId = xFarmId;
	}
	@Override
	public FarmInfo getCurFarmStatus() {
		FarmInfo farmInfo = null;
		try
		{
			HibernateUtil.beginTransaction();
			SensorStatus sensors    = sensorStatusDao.getNearestData(farmId);
System.out.println("SensorStatus:"+sensors.toString());
			Controllers controllers = controllersDao.getNearestData(farmId);
System.out.println("Controllers:"+controllers.toString());

			farmInfo = new FarmInfo(sensors, controllers);

			HibernateUtil.commitTransaction();
		}
		catch(Exception e)
		{
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return farmInfo;
	}

}
