package com.baidu.asset.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.baidu.asset.bean.Asset;
import com.baidu.asset.service.AssetService;
import com.baidu.assetdetail.bean.Assetdetail;
import com.baidu.assetdetail.service.AssetdetailService;
import com.baidu.base.service.SelectDataService;
import com.baidu.img.bean.Img;
import com.baidu.img.service.ImgService;
import com.baidu.util.FileUtil;
import com.baidu.util.Page;
import com.baidu.util.QueryResult;
import com.baidu.util.Request;
import com.baidu.util.Response;
import com.baidu.util.StringUtil;
import com.baidu.util.WebUtils;
import com.opensymphony.xwork2.ActionSupport;
public class AssetAction extends ActionSupport {
	private Asset asset;
	private Assetdetail assetdetail;
	private Img img;
	
	
	private AssetService assetService;
	private SelectDataService selectDataService;
	private AssetdetailService assetdetailService;
	private ImgService imgService;
	
	//第一种方法：资产明细，前台拼接字符串，后台截取，再添加
	//第二种方法：资产明细用数组接收
	private String[] name;
	private String[] model;
	private Integer[] num;
	private String[] content;
	
	
	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getModel() {
		return model;
	}

	public void setModel(String[] model) {
		this.model = model;
	}

	public Integer[] getNum() {
		return num;
	}

	public void setNum(Integer[] num) {
		this.num = num;
	}
	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}
	
	
	public Assetdetail getAssetdetail() {
		return assetdetail;
	}

	public void setAssetdetail(Assetdetail assetdetail) {
		this.assetdetail = assetdetail;
	}

	public Img getImg() {
		return img;
	}

	public void setImg(Img img) {
		this.img = img;
	}

	public AssetdetailService getAssetdetailService() {
		return assetdetailService;
	}

	public void setAssetdetailService(AssetdetailService assetdetailService) {
		this.assetdetailService = assetdetailService;
	}

	public ImgService getImgService() {
		return imgService;
	}

	public void setImgService(ImgService imgService) {
		this.imgService = imgService;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public AssetService getAssetService() {
		return assetService;
	}

	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	
	public String listPage() {
		return "list";
	}
	public String addPage(){
		
		return "addPage";
	}
	
	public void addAsset() throws Exception{
		assetService.save(asset);
		Response.write("ok");
		
		String id = StringUtil.randomInt();
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
	
	public void addDetail() throws Exception{
		String string = Request.getParameter("details");
		String[] trs = string.split(";");
		for (String tr : trs) {
			String[] td = tr.split(",");
			assetdetail=new Assetdetail();
			assetdetail.setName(td[0]);
			assetdetail.setModel(td[1]);
			assetdetail.setNum(td[2]);
			assetdetail.setContent(td[3]);
			assetdetailService.save(assetdetail);
		}
		Response.write("detailok");
	}
	
	/**
	 * 获取资产表主键的方法
	 * 1.资产表保存之后，对象中直接可以获取主键
	 * 2.使用工具类直接生成随机id，三个表可以直接同时使用主键和外键
	 */
	public void addForm(){
		//添加资产
		//手动设置编号
		String randomId = WebUtils.getRandomId();
		asset.setNum("京"+randomId);//自动生成时间
		assetService.save(asset);
		
		//添加图片
		String imgs = Request.getParameter("imgs");
		if(imgs!=null){
			String[] imgNamePath = imgs.split(";");
			Img img2=null;
			for (String name : imgNamePath) {
				img2=new Img();
				String[] namePath = name.split(",");
				img2.setAid(asset.getId());
				img2.setName(namePath[0]);
				img2.setPath(namePath[1]);
				imgService.save(img2);
			}
		}
		//添加资产明细
		String string = Request.getParameter("details");
		if(string!=null){
			String[] trs = string.split(";");
			for (String tr : trs) {
				String[] td = tr.split(",");
				assetdetail=new Assetdetail();
				assetdetail.setAssetid(asset.getId());
				assetdetail.setName(td[0]);
				assetdetail.setModel(td[1]);
				assetdetail.setNum(td[2]);
				assetdetail.setContent(td[3]);
				assetdetailService.save(assetdetail);
			}
		}
		
		Response.write("ok");
	}
	
	public void edit() {
		assetService.update(asset);
		Response.write("true");
	}
	
	
	public void listAll(){
		List<Asset> assets =assetService.findAll();
		Response.write(JSONArray.fromObject(assets).toString());
	}
	
	public void assetDate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sql="select a.id,a.num,a.name,b.type_name typename , a.buydate,c.realname username, a.price, d.name factory,a.status, a.content from T_ASSET a,t_assettype b,t_user c,t_factory d where a.typeid=b.id and a.userid=c.id and a.factory=d.id order by to_number(a.id) desc";
		Map map = selectDataService.getListForPage(request, sql);
		JSONObject json=new JSONObject();
		json.put("total", ((Page)map.get("p")).getTotal());
		json.put("rows", map.get("resoultList"));
		Response.write(json.toString());
		
	/*	
		//当前页
		int page =Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		//每页显示条数
		  int rows = Integer.parseInt(request.getParameter("rows")==null?"10":request.getParameter("rows"));//接受参数page和rows
		
		QueryResult<Asset> result =assetService.getScrollData((page-1)*rows,rows);
		
		Response.write(JSONArray.fromObject(result.getResultlist()).toString());*/
		
	}
	
	public String upload(){
		return "upload";
		
	}

	public SelectDataService getSelectDataService() {
		return selectDataService;
	}

	public void setSelectDataService(SelectDataService selectDataService) {
		this.selectDataService = selectDataService;
	}
	
	public void getAssetType(){
		List typeList = selectDataService.queryForList("select t.id, t.type_name typename from T_ASSETTYPE t ");
		Response.write(JSONArray.fromObject(typeList).toString());
	}
	public void getFactory(){
		Integer id = Integer.parseInt(Request.getParameter("id"));
		List facoryList = selectDataService.queryForList("select t.id , t.name from t_factory t where t.tid= "+id);
		Response.write(JSONArray.fromObject(facoryList).toString());
	}
	public String updatePage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer assetid = Integer.parseInt(request.getParameter("assetid"));
		asset = assetService.getEntityById(assetid);
		request.setAttribute("asset", asset);
		request.setAttribute("assetid", assetid);
		
		return "updatePage";
		
		
	}
	public void getDetailList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer assetid = Integer.parseInt(request.getParameter("assetid"));
		List<Assetdetail> detailList = selectDataService.queryForList("select id, name, model, num, content, assetid from t_assetdetail where assetid="+assetid);
		Response.write(JSONArray.fromObject(detailList).toString());
	}
	public void getImgList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer assetid = Integer.parseInt(request.getParameter("assetid"));
		List<Img> imgList = selectDataService.queryForList("select id, name, path, aid from t_img where aid="+assetid);
		Response.write(JSONArray.fromObject(imgList).toString());
	}
	
	public void updateForm(){
		//修改资产
		assetService.update(asset);
		//不需要删除图片,如果有,添加新图片
		//selectDataService.delete("delete from t_img where aid="+asset.getId());
		String imgs = Request.getParameter("imgs");
		if(imgs!=null && imgs.length()>0){
			String[] imgNamePath = imgs.split(";");
			Img img2=null;
			for (String name : imgNamePath) {
				img2=new Img();
				String[] namePath = name.split(",");
				img2.setAid(asset.getId());
				img2.setName(namePath[0]);
				img2.setPath(namePath[1]);
				imgService.save(img2);
			}
		}
		//删除资产配置+添加新配置
		selectDataService.delete("delete from t_assetdetail where assetid="+asset.getId());
		String string = Request.getParameter("details");
		if(string!=null && string.length()>0){
			String[] trs = string.split(";");
			for (String tr : trs) {
				String[] td = tr.split(",");
				assetdetail=new Assetdetail();
				assetdetail.setAssetid(asset.getId());
				assetdetail.setName(td[0]);
				assetdetail.setModel(td[1]);
				assetdetail.setNum(td[2]);
				assetdetail.setContent(td[3]);
				assetdetailService.save(assetdetail);
			}
		}
		
		Response.write("ok");
	}
	
	
	/**
	 * 导出excel
	 */
	public void exportAsset(){
		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();
		String fileName ="资产管理";
		
		//获取表头信息TABLE_NAME, TABLE_TYPE, COMMENTS
		List<Map> list =selectDataService.queryForList("select * from user_col_comments where table_name ='T_ASSET'");
		//中文表头集合
		List<String> cnColList = new ArrayList();
		//中英文表头集合
		Map cn_enmap =new HashMap();
		
		for(Map map:list){
			cnColList.add(map.get("COMMENTS").toString());
			cn_enmap.put(map.get("COMMENTS").toString(), map.get("COLUMN_NAME").toString());
		}
		
		HSSFWorkbook workbook =new HSSFWorkbook();
		HSSFSheet sheet =workbook.createSheet("fileName");
		
		//添加表头
		HSSFRow row =sheet.createRow(0);
		HSSFCell cell =row.createCell(0);
		cell.setCellValue(fileName);
		//添加列头
		row =sheet.createRow(1);
		for (int i = 0; i < cnColList.size(); i++) {
			cell =row.createCell(i);
			cell.setCellValue(cnColList.get(i));
		}
		
		//添加数据
		
		List<Map> dataList =selectDataService.queryForList("select a.id,a.num,a.name,b.type_name typeid,to_char(a.buydate,'yyyy-MM-dd') buydate ,d.realname userid,a.price,c.name factory,decode(a.status,'1','在库','2','出库','禁用') status,a.content  from t_asset a,t_assettype b,t_factory c,t_user d where a.typeid =b.id(+) and a.userid =d.id(+) and a.factory =c.id(+)");
		
		for (int i = 0; i < dataList.size(); i++) {
			//每一条数据集合
			Map dataMap =dataList.get(i);
			row =sheet.createRow(i+2);
			for (int j = 0; j < cn_enmap.size(); j++) {
				cell =row.createCell(j);
				//获取中文字段名
				String cnColName =cnColList.get(j);
				//根据中文字段获取英文字段名
				String enColName =cn_enmap.get(cnColName).toString();
				cell.setCellValue(dataMap.get(enColName)==null?"":dataMap.get(enColName).toString());
			}
			
			
		}
		
		
		try {
			String filePath =request.getSession().getServletContext().getRealPath("/")+"download\\a.xls";
			FileOutputStream outputStream =new FileOutputStream(filePath);
			
			workbook.write(outputStream);
			
			outputStream.close();
			
			FileUtil.downloadFile(response, filePath, fileName+".xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 
	 */
	public void deleteForm(){
		//删除资产，图片，资产配置
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer assetid = Integer.parseInt(request.getParameter("assetid"));
		//查询图片服务器地址
		List<Img> imgList = imgService.findAll();
		for (Img im : imgList) {
			File file=new File(request.getSession().getServletContext().getRealPath("/")+"upload/"+im.getPath());
			if(file.exists()){
				System.out.println("要删除的file.path="+file);
				boolean b = file.delete();
				if(b){
					System.out.println("服务器图片删除成功");
				}else{
					System.out.println("服务器图片删除失败");
				}
			}
			
		}
		
		selectDataService.delete("delete from t_img where aid="+assetid);
		selectDataService.delete("delete from t_assetdetail where assetid="+assetid);
		assetService.delete(assetService.getEntityById(assetid));
		Response.write("ok");
	}
	
	public void deleteImg(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = Integer.parseInt(request.getParameter("id"));
		imgService.delete(imgService.getEntityById(id));
		Response.write("ok");
		
	}
	
	public void deleteDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = Integer.parseInt(request.getParameter("id"));
		assetdetailService.delete(assetdetailService.getEntityById(id));
		Response.write("ok");
	}


}
