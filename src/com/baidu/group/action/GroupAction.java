package com.baidu.group.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.baidu.group.bean.Group;
import com.baidu.group.service.GroupService;
import com.baidu.util.PageView;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAction extends ActionSupport {
	private Group group;
	
	private GroupService groupService;
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	
	public String add() throws Exception{
		groupService.save(group);
		return SUCCESS;
	}
	
	
	public void listAll(){
		List<Group> groups =	groupService.findAll();
		Response.write(JSONArray.fromObject(groups).toString());
	}
	
	
	public String list() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		PageView<Group> pageView = new PageView<Group>(10, currentPage);
		StringBuffer hql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		hql.append(" 1=1 ");
		String startDate = request.getParameter("startDate");
		try {
			if (startDate != null && !startDate.equals("")) {
				hql.append(" and o.applyTime >=?");
				params.add(startDate);
				request.setAttribute("startDate", startDate);
			}
			String endDate = request.getParameter("endDate");
			if (endDate != null && !endDate.equals("")) {
				hql.append(" and o.applyTime <=?");
				params.add(endDate);
				request.setAttribute("endDate", endDate);
			}
			String applyNode = request.getParameter("applynode");
			if (applyNode != null && !applyNode.equals("")) {
				hql.append(" and o.applyNode = " + applyNode);
				request.setAttribute("applyNode", applyNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("applyTime", "desc");
		pageView.setQueryResult(groupService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),hql.toString(), params.toArray(), orderby));
		request.setAttribute("pageView", pageView);
		
		return "list";
	}
	
	

}
