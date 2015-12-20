package com.baidu.dept.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.baidu.base.service.SelectDataService;
import com.baidu.dept.bean.Dept;
import com.baidu.dept.service.DeptService;
import com.baidu.util.Page;
import com.baidu.util.Request;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DeptAction extends ActionSupport implements ModelDriven<Dept>{
	private Dept dept=new Dept();
	private String ids;
	
	
	private DeptService deptService;
	private SelectDataService selectDataService;
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	

	
	public SelectDataService getSelectDataService() {
		return selectDataService;
	}

	public void setSelectDataService(SelectDataService selectDataService) {
		this.selectDataService = selectDataService;
	}

	public String add() throws Exception{
		deptService.save(dept);
		return SUCCESS;
	}
	
	public String listPage(){
		return "list";
	}
	
	/** 
	 * 分页查询返回部门数据
	* @Title: listData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* //总条数
//		int totalCount = (int) deptService.getCount();
//		QueryResult result =	deptService.getScrollData((page-1)*rows, rows);
//		
//		JSONObject json = new JSONObject();
//		json.put("total", result.getTotalrecord());
//		json.put("rows", result.getResultlist());
	*/ 
	
	public void listData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//当前页
		int page= Integer.parseInt(Request.getParameter("page")==null?"1":Request.getParameter("page"));
		//每页显示条数
		int rows= Integer.parseInt(Request.getParameter("rows")==null?"10":Request.getParameter("rows"));
		
		Map map =selectDataService.getListForPage(request, "select a.id,a.name,b.name pname,c.realname firstuser,d.realname seconduser,e.realname secretary from t_dept a,t_dept b,t_user c,t_user d,t_user e where a.pid =b.id and a.firstuser =c.id and a.seconduser =d.id and a.secretary =e.id order by to_number(a.id)  desc");
		
		JSONObject json = new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));
		
		Response.write(json.toString());
	}
	
	
	public void listAll(){
		List<Dept> depts =	deptService.findAll();
		Response.write(JSONArray.fromObject(depts).toString());
	}
	
	public String addPage(){
		return "addPage";
	}

	public void ptree() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		List tree = selectDataService.queryForList(" select id ,name  ,pid  from   t_dept");
		response.getWriter().write(JSONArray.fromObject(tree).toString());
	}
	public void deptTree() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		List tree = selectDataService.queryForList(" select id \"id\" ,name \"name\" ,pid \"pId\" ,'true'  \"open\" from   t_dept");
		response.getWriter().write(JSONArray.fromObject(tree).toString());
	}
	public String deptTreePage(){
		return "deptTreePage";
	}
	
	public void addDept(){
		deptService.save(dept);
		Response.write("yes");
	}

	@Override
	public Dept getModel() {
		return this.dept;
	}
	
	/**
	 * 删除+批量删除
	 */
	public void deleteDept(){
		String[] deptIds = ids.split(",");
		for (String iid : deptIds) {
			deptService.delete(deptService.getEntityById(Integer.parseInt(iid)));
		}
	}
	
	public String updatePage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer id=Integer.parseInt(request.getParameter("id"));
		
		String sql="select a.id,a.name, b.pid,b.name pname, a.firstuser,c.realname firstuserName," +
				"a.seconduser,d.realname seconduserName,a.secretary,e.realname secretaryName  " +
				"from t_dept a,t_dept b,t_user c,t_user d,t_user e  " +
				"where a.pid =b.id and a.firstuser =c.id and a.seconduser =d.id and a.secretary =e.id and a.id="+dept.getId();
		List<Dept> list = selectDataService.queryForList(sql);
		
		request.setAttribute("depts", list.get(0));
		return "updatePage";
	}
	
	public void updateDept(){
		deptService.update(dept);
		Response.write("yes");
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	

}
