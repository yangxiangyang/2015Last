package com.baidu.img.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.baidu.img.bean.Img;
import com.baidu.img.service.ImgService;
import com.baidu.util.PageView;
import com.baidu.util.Request;
import com.baidu.util.Response;
import com.opensymphony.xwork2.ActionSupport;

public class ImgAction extends ActionSupport {
	private Img img;
	
	private ImgService imgService;
	
	public Img getImg() {
		return img;
	}

	public void setImg(Img img) {
		this.img = img;
	}

	public ImgService getImgService() {
		return imgService;
	}

	public void setImgService(ImgService imgService) {
		this.imgService = imgService;
	}

	
	public void addImg() throws Exception{
		String imgs = Request.getParameter("imgs");
//		198795.jpg,8087d9e9-533f-4957-bb97-5243ddf23bc8.jpg;199705.jpg,382d20f0-b52d-4e51-ae30-82dc7ce2452e.jpg;202120.jpg,ea718953-00e0-4b8c-939c-26ed99f18a4f.jpg
		String[] imgNamePath = imgs.split(";");
		Img img2=null;
		for (String name : imgNamePath) {
			img2=new Img();
			String[] namePath = name.split(",");
			img2.setName(namePath[0]);
			img2.setPath(namePath[1]);
			imgService.save(img2);
		}
		Response.write("imgok");
	}
	
	
	public void listAll(){
		List<Img> imgs =	imgService.findAll();
		Response.write(JSONArray.fromObject(imgs).toString());
	}
	
	
	public String list() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		PageView<Img> pageView = new PageView<Img>(10, currentPage);
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
		pageView.setQueryResult(imgService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),hql.toString(), params.toArray(), orderby));
		request.setAttribute("pageView", pageView);
		
		return "list";
	}
	
	

}
