package com.baidu.base.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baidu.util.Page;

public interface BaseService {
	/**
	 * 保存实体
	 * 
	 * @param obj
	 * @param clazz
	 * @param key
	 * @return
	 */
	public Object save(Object obj, Class clazz, String key);

	/**
	 * 删除实体
	 * 
	 * @param obj
	 */
	public void delete(Object obj);

	/**
	 * 通过参数查找实体
	 * 
	 * @param clazz
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public List findObjectByPar(Class clazz, String propName, Object propValue);

	/**
	 * 出入参数,和排序的名称
	 * 
	 * @param clazz
	 * @param propName
	 * @param propValue
	 * @param orderName
	 * @param ascoOrdesc
	 * @return
	 */
	public List findObjectByParOrder(Class clazz, String propName, Object propValue, String orderName, String ascoOrdesc);

	/**
	 * 通过传多个参数来查询相应数据
	 * 
	 * @param clazz
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List findObjectByPars(Class clazz, String[] propNames, Object[] propValues);

	/**
	 * 通过传多个参数来模糊查询相应数据
	 * 
	 * @param clazz
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List findObjectByLikeParsOrder(Class clazz, String[] propNames, Object[] propValues, String[] orderNames, String[] ascoOrdescs);

	public List findObjectByParsOrder(Class clazz, String[] propNames, Object[] propValues, String orderName, String ascoOrdesc);

	/**
	 * 查询
	 * 
	 * @param clazz
	 * @param proMap
	 * @param orderMap
	 * @return
	 */
	public List findObjectListByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap);

//	public void saveSysTemUserLog(HttpServletRequest request, String operateDetail);

	public Long getUniqueId();


	/**
	 * 分页查询,通过传多个参数来查询相应数据
	 * 
	 * @param clazz
	 * @param proMap
	 *            (属性名,值)
	 * @param orderMap
	 *            (排序属性,desc or asc)
	 * @param request
	 * @author tongjp
	 * @return
	 */
	public Page findObjectPageByParamAndOrder(Class clazz, LinkedHashMap proMap, LinkedHashMap orderMap, HttpServletRequest request);
}
