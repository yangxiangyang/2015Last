package com.baidu.college.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.baidu.college.bean.College;
import com.baidu.college.service.CollegeService;
import com.baidu.util.PageView;
import com.baidu.util.QueryResult;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionSupport;

public class CollegeAction extends ActionSupport {
	private College college;
	
	private CollegeService collegeService;
	
	
	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	
	public String listPage() {
		return "list";
	}
	
	public String add() throws Exception{
		collegeService.save(college);
		return SUCCESS;
	}
	
	public void edit() {
		collegeService.update(college);
		Response.write("true");
	}
	
	
	public void listAll(){
		List<College> colleges =	collegeService.findAll();
		Response.write(JSONArray.fromObject(colleges).toString());
	}
	
	
	public void list() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//当前页
		int page =Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		//每页显示条数
		  int rows = Integer.parseInt(request.getParameter("rows")==null?"10":request.getParameter("rows"));//接受参数page和rows
		
		QueryResult<College> result =collegeService.getScrollData((page-1)*rows,rows);
		
		Response.write(  JSONArray.fromObject(result.getResultlist()).toString());
		
	}
	
	

}
