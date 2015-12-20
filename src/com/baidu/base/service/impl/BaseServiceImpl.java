package com.baidu.base.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baidu.base.dao.BaseDao;
import com.baidu.base.service.BaseService;
import com.baidu.util.Page;
import com.baidu.util.PropertiesUtil;

public class BaseServiceImpl implements BaseService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List findObjectByLikeParsOrder(Class clazz, String[] propNames, Object[] propValues, String[] orderNames, String[] ascoOrdescs) {
		// TODO Auto-generated method stub
		return baseDao.findObjectByLikeParsOrder(clazz, propNames, propValues, orderNames, ascoOrdescs);
	}

	public List findObjectByPar(Class clazz, String propName, Object propValue) {
		// TODO Auto-generated method stub
		return baseDao.findObjectByPar(clazz, propName, propValue);
	}

	public List findObjectByParOrder(Class clazz, String propName, Object propValue, String orderName, String ascoOrdesc) {
		// TODO Auto-generated method stub
		return baseDao.findObjectByParOrder(clazz, propName, propValue, orderName, ascoOrdesc);
	}

	public List findObjectByPars(Class clazz, String[] propNames, Object[] propValues) {
		// TODO Auto-generated method stub
		return baseDao.findObjectByPars(clazz, propNames, propValues);
	}

	public List findObjectByParsOrder(Class clazz, String[] propNames, Object[] propValues, String orderName, String ascoOrdesc) {
		// TODO Auto-generated method stub
		return baseDao.findObjectByParsOrder(clazz, propNames, propValues, orderName, ascoOrdesc);
	}

	public List findObjectListByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap) {
		// TODO Auto-generated method stub
		return baseDao.findObjectListByParamAndOrder(clazz, proMap, orderMap);
	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		baseDao.delete(obj);
	}

	public Object save(Object obj, Class clazz, String key) {
		// TODO Auto-generated method stub
		return baseDao.save(obj, clazz, key);
	}


	public Long getUniqueId() {
		return baseDao.getUniqueId();
	}


	public Page findObjectPageByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int pageSize = Integer.parseInt(PropertiesUtil.getProperties("system.pagesize", "10"));
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} else if (request.getParameter("rows") != null) {
			pageSize = Integer.parseInt(request.getParameter("rows"));
		}
		if (request.getParameter("order") != null && request.getParameter("sort") != null) {
			orderMap.put(request.getParameter("sort"), request.getParameter("order"));
		}
		return baseDao.findObjectPageByParamAndOrder(clazz, proMap, orderMap, page, pageSize);
	}

}
