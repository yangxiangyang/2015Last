package com.baidu.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baidu.base.dao.SelectDataDao;
import com.baidu.base.service.SelectDataService;
import com.baidu.util.DataTranslateUtil;
import com.baidu.util.Page;
import com.baidu.util.PropertiesUtil;

public class SelectDataServiceImpl implements SelectDataService{
	private SelectDataDao selectDataDao;
	public void setSelectDataDao(SelectDataDao selectDataDao) {
		this.selectDataDao = selectDataDao;
	}
	public SelectDataDao getSelectDataDao() {
		return selectDataDao;
	}
	public List queryForList(String sql) {
		// TODO Auto-generated method stub
		List list=selectDataDao.queryForList(sql);
		return list;
	}
	
	public int queryForInt(String sql) {
		// TODO Auto-generated method stub
		return selectDataDao.queryForInt(sql);
	}
	
	public List getDataObjs(String sql,Class clazz){
		try {
			List list=selectDataDao.queryForList(sql);
			List<Object> objs =DataTranslateUtil.getObjects(list, clazz);
			return objs;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean remove(String sql) {
		// TODO Auto-generated method stub
		return selectDataDao.deletebyid(sql);
	}
	public String[] getColumnName(String sql) {
		// TODO Auto-generated method stub
		
		return selectDataDao.getColumnName(sql);
	}
	
	public int update(String sql) {
		return selectDataDao.update(sql);
	}
	
	public Map getListForPage(HttpServletRequest request, String sql) {
		// TODO Auto-generated method stub
		int total=this.queryForInt(sql);
		int page=1;
		if(request.getParameter("page")!=null&&request.getParameter("page").trim().length()>0){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int pageSize=Integer.parseInt(PropertiesUtil.getProperties("system.pagesize", "12"));
		if(request.getParameter("pageSize")!=null&&request.getParameter("pageSize").trim().length()>0){
			pageSize=Integer.parseInt(request.getParameter("pageSize"));
		}
		if(request.getParameter("rows")!=null){
			pageSize=Integer.parseInt(request.getParameter("rows"));
		}
		if(request.getParameter("order")!=null&&request.getParameter("sort")!=null){
			sql=sql+" order by "+request.getParameter("sort")+" "+request.getParameter("order");
		}
		Page p=new Page();
		p.setPage(page);
		p.setPageSize(pageSize);
		p.setTotal(total);
		sql="select rownum as oid, pagetable.* from ("+sql+") pagetable ";
		String sqlpage="select * from ("+sql+") tables1 where tables1.oid>="+((page-1)*pageSize+1)+" and tables1.oid<="+page*pageSize;
		List resoultList=this.queryForList(sqlpage);
		Map resultMap=new HashMap();
		resultMap.put("resoultList", resoultList);
		resultMap.put("p", p);
		return resultMap;
	}
	
	public Page getForPage(HttpServletRequest request, String sql) {
		// TODO Auto-generated method stub
		int total=this.queryForInt(sql);
		int page=1;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int pageSize=Integer.parseInt(PropertiesUtil.getProperties("system.pagesize", "12"));
		if(request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize"));
		}
		if(request.getParameter("rows")!=null){
			pageSize=Integer.parseInt(request.getParameter("rows"));
		}
		if(request.getParameter("order")!=null&&request.getParameter("sort")!=null){
			sql=sql+" order by "+request.getParameter("sort")+" "+request.getParameter("order");
		}
		Page p=new Page();
		p.setPage(page);
		p.setPageSize(pageSize);
		p.setTotal(total);
		sql="select rownum as oid, pagetable.* from ("+sql+") pagetable ";
		String sqlpage="select * from ("+sql+") tables1 where tables1.oid>="+((page-1)*pageSize+1)+" and tables1.oid<="+page*pageSize;
		List resoultList=this.queryForList(sqlpage);
//		Map resultMap=new HashMap();
//		resultMap.put("resoultList", resoultList);
//		resultMap.put("page", p);
		p.setData(resoultList);
		return p;
	}

	public String getString(String sql) {
		List list=selectDataDao.queryForList(sql);
		String re = "";
		if(list.size()>0){
			Map map = (Map) list.get(0);
			String[] cols = this.getColumnName(sql);
			Object obj = map.get(cols[0]);
			if(obj!=null){
				re = obj.toString();
			}		
		}
		return re;
	}

	public void updateClob(String clobColumnName,final String data,String idName,String idValue,String tableName)
	{
		selectDataDao.updateClob(clobColumnName, data, idName, idValue, tableName);
	}
	public String getClob(String clobColumnName,String idName,String idValue,String tableName){
		return selectDataDao.getClob(clobColumnName, idName, idValue, tableName);
	}
	public boolean delete(String sql) {
		// TODO Auto-generated method stub
		return selectDataDao.deletebyid(sql);
	}
	
	/**调用没有返回值的存储过程
	 * @author wgzx_106    @param callName 存储过程名称
	 * @author wgzx_106    @param args List集合，集合里面存放的字符串
	 *@version 5:16:39 PM
	 */
	public void callProceDureNoResult(String callName,List<String> args){
		selectDataDao.callProceDureNoResult(callName, args);
	}
	
	/** 调用返回输出参数数据的存储过程
	 * @author wgzx_106    @param callName 存储过程名称
	 * @author wgzx_106    @param args List集合，集合里面存放表明参数类型的字符串 参数类型_参数值，如input_1 是输入参数，out_2  输出参数  
	 * @author wgzx_106    @return 返回输出参数返回值的集合
	 *@version 5:16:26 PM
	 */
	public  List<String> callProceDureByOut(String callName,List<String> args){
		return selectDataDao.callProceDureByOut(callName, args);
	}
}
