package com.hibernate.manager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.hibernate.dao.FarmsDao;
import com.hibernate.dao.FarmsDaoImpl;
import com.hibernate.domain.Farms;
import com.hibernate.domain.Users;
import com.hibernate.util.HibernateUtil;

import server.util.UserInfo;

public class HousesManagerImpl implements HousesManager {
	private FarmsDao farmsDao = new FarmsDaoImpl();
	private UserInfo userInfo;
	public HousesManagerImpl(UserInfo xUserInfo)
	{
		userInfo = xUserInfo;
	}
	public Map<String,Integer> getFarms()
	{
		Map<String,Integer> farms = new LinkedHashMap<String,Integer>();
		//用Map的哪个具体实现比较好
		try
		{
			HibernateUtil.beginTransaction();
//			String sql= "select id, farmName from com.hibernate.domain.Houses h ";
			//先弄个简单版的  先不考虑效率的问题
			String sql= "from com.hibernate.domain.Farms f where f.account.account=:account";
			Query query = HibernateUtil.getSession().createQuery(sql)
						  .setParameter("account", userInfo.account);
			List<Farms> houses=farmsDao.findMany(query);
System.out.println("GetFarmsInfo:");
			for(Farms h:houses)
			{	
System.out.println("FarmName： "+h.getFarmName()+" Id: "+h.getId());
//				if(h.getAccount().getAccount()==userInfo.account)
//				{
					farms.put(h.getFarmName(), h.getId());
//				}
			}	
			HibernateUtil.commitTransaction();
		}
		catch(Exception e)
		{
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
		}
		return farms;
	}
}
