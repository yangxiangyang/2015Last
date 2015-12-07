package com.baidu.user.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.Region;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.baidu.base.ContextHolder;
import com.baidu.base.service.SelectDataService;
import com.baidu.group.bean.Group;
import com.baidu.group.service.GroupService;
import com.baidu.user.bean.User;
import com.baidu.user.service.UserService;
import com.baidu.util.DateUtil;
import com.baidu.util.FileUtil;
import com.baidu.util.PageView;
import com.baidu.util.QueryResult;
import com.baidu.util.Request;
import com.baidu.util.Response;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user;
	private UserService userService;
	private GroupService groupService;
	private SelectDataService  selectDataService;
	private List<Group> groups ;
	
	
	public SelectDataService getSelectDataService() {
		return selectDataService;
	}

	public void setSelectDataService(SelectDataService selectDataService) {
		this.selectDataService = selectDataService;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void test(){
//		System.out.println(Request.getParameter("aaa"));
		System.out.println(ContextHolder.getApplicationContext().containsBean("aaa"));
		System.out.println(ContextHolder.getApplicationContext().getClassLoader().getResource(""));
		System.out.println(ContextHolder.getApplicationContext().CLASSPATH_ALL_URL_PREFIX);
	}
	
	public List getNameByCode(String code,String dicCode) {
		try {
			return selectDataService.queryForList("select * from user_col_comments where table_name ='T_USER'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void export(){
		FileOutputStream stream =null;
		String fileName ="用户管理";
		try {
			    
			//查询表字段信息
			List<Map> cols =	selectDataService.queryForList("select * from user_col_comments where table_name ='T_USER'");
			
			//查询用户数据
//			List<User> users =	userService.findAll();
			List<Map> users = selectDataService.queryForList("select a.id,a.loginname,a.password,a.realname,a.age,decode(a.sex,'1','男','2','女','男') sex,a.phone,a.mail,to_char(a.adddate,'yyyy-mm-dd hh24:mi:ss') adddate,b.groupname classid,a.role from t_user a,t_group b where a.classid =b.id order by a.id");
			
			//调用poi写入数据
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet =	workbook.createSheet(fileName);
			
			
			HSSFCellStyle cellStyle = workbook.createCellStyle();  
//			 一、设置背景色:


			cellStyle.setFillForegroundColor((short) 13);// 设置背景色  
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//			二、设置边框:


			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
//			三、设置居中:


			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
//			四、设置字体:


			HSSFFont font = workbook.createFont();  
			font.setFontName("黑体");  
			font.setFontHeightInPoints((short) 16);//设置字体大小  
			  
			cellStyle.setFont(font);//选择需要用到的字体格式  
//			五、设置列宽:

			sheet.setColumnWidth(0, 3766); 
			//第一个参数代表列id(从0开始),第2个参数代表宽度值  参考 ："2012-08-10"的宽度为2500  
//			六、设置自动换行:

			cellStyle.setWrapText(true);//设置自动换行  
			
			
			//创建合并单元格   表示excel名称
			HSSFRow row =	sheet.createRow(0);
			
			sheet.addMergedRegion(new Region(0, (short)0, 1, (short)(cols.size()-1)));
			
			HSSFCell cell =	row.createCell(0);
			cell.setCellValue(fileName);
			cell.setCellStyle(cellStyle);
			
			//创建数据表头行
			row =	sheet.createRow(2);
			
			for (int i = 0; i < cols.size(); i++) {
				
				cell =row.createCell(i);
				cell.setCellValue(cols.get(i).get("COMMENTS").toString());
			}
			
			//创建数据行
			for (int i = 0; i < users.size(); i++) {
				row =	sheet.createRow(3+i);
				Map userMap =users.get(i);
				for (int j = 0; j < cols.size(); j++) {
					cell =	row.createCell(j);
					String colName =cols.get(j).get("COLUMN_NAME").toString();
					cell.setCellValue((userMap.get(colName)== null?"":userMap.get(colName).toString()));
				}
				
				
				
			}
			
			
			stream = new FileOutputStream("C:\\a.xls");
			workbook.write(stream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
		
		
		
		
		
		//生成excel文件
		
		FileUtil.downloadFile(ServletActionContext.getRequest(), ServletActionContext.getResponse(), "C:\\a.xls", fileName+".xls");
		
		
		
	}
	
	
	
	public void exportPdf(){
		
		Document document =null;
		FileOutputStream outputStream =null;
		
		try {
			
			//查询表字段信息
			List<Map> cols =	selectDataService.queryForList("select * from user_col_comments where table_name ='T_USER'");
			
			//查询用户数据
//			List<User> users =	userService.findAll();
			List<Map> users = selectDataService.queryForList("select a.id,a.loginname,a.password,a.realname,a.age,decode(a.sex,'1','男','2','女','男') sex,a.phone,a.mail,to_char(a.adddate,'yyyy-mm-dd hh24:mi:ss') adddate,b.groupname classid,a.role from t_user a,t_group b where a.classid =b.id order by a.id");
			
			String exportPah =ServletActionContext.getRequest().getRealPath("\\")+"export\\";
//			outputStream =new FileOutputStream(exportPah+System.currentTimeMillis()+".pdf");
			outputStream =new FileOutputStream(exportPah+System.currentTimeMillis()+".doc");
			
			document = new Document(PageSize.A4);
			
			//pdf
//			PdfWriter.getInstance(document, outputStream);
			//word
			RtfWriter2.getInstance(document, outputStream);
			
			document.open();
			
			//定义字体
			BaseFont bfChinese =BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			Font fontCN = new Font(bfChinese,10,Font.BOLD);
			
			Paragraph title =new Paragraph("用户管理",fontCN);
			title.setAlignment("center");
			document.add(title);
			
			Table table = new Table(cols.size());
			
			for (int i = 0; i <cols.size(); i++) {
				Cell cell =new Cell(new Paragraph(cols.get(i).get("COMMENTS").toString(), fontCN));
				table.addCell(cell);
			}
			
			for (int i = 0; i < users.size(); i++) {
				//获取每一行数据
				Map usermap = users.get(i);
				//循环添加每一个字段
				for (int j = 0; j <cols.size(); j++) {
					//获取每一个字段名
					String colName =cols.get(j).get("COLUMN_NAME").toString();
					Cell cell =new Cell(new Paragraph((usermap.get(colName)==null?"":usermap.get(colName).toString()), fontCN));
					table.addCell(cell);
				}
				
			}
			
			
			document.add(table);
			
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			document.close();
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//生成excel文件
		
		
	}
	
	public void impExcel() {
		FileInputStream inputStream =null;
		try {
			//文件上传
			
			
			//解析数据
			
			//查询表字段信息
			List<Map> cols =	selectDataService.queryForList("select a.COLUMN_NAME,a.DATA_TYPE,b.comments from cols a,user_col_comments b where a.table_name ='T_USER' and b.table_name ='T_USER' and a.COLUMN_NAME =b.column_name");
			
			
			//字段注释与字段英文名关联关系map
			Map colmap = new HashMap<String, String>();
			//字段注释与字段类型关联关系map
			Map typemap = new HashMap<String, String>();
			List colList = new ArrayList<String>();
			for(Map map:cols){
				//获取键
				String key = map.get("COMMENTS").toString();
				//获取值
				String value = map.get("COLUMN_NAME").toString();
				
				String type = map.get("DATA_TYPE").toString();
				colmap.put(key, value);
				typemap.put(key, type);
			}
			
			inputStream  =new FileInputStream("c:\\a.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			
			int sheetNum =workbook.getNumberOfSheets();
			
			
			String firstsql ="insert into T_USER (";
			
			for (int i = 0; i < sheetNum; i++) {
				//解析每一个sheet
				HSSFSheet sheet =workbook.getSheetAt(i);
				//获取有效行数
				int rownum =sheet.getLastRowNum()+1;
				for (int j = 0; j < rownum; j++) {
					//解析每一行数据
					HSSFRow row =sheet.getRow(j);
					if(row!=null){
						
						int cellnum =row.getLastCellNum();
						
						//解析字段表头
						String valuesql ="";	
						if(j==2){
							for (int k = 0; k < cellnum; k++) {
								HSSFCell cell =row.getCell(k);
								colList.add(cell.getStringCellValue());
								firstsql+=colmap.get(cell.getStringCellValue())+",";
								}
							firstsql=firstsql.substring(0, firstsql.length()-1);
							firstsql+=") values (";
						}
						if(j>2){
							//解析数据行
							for (int k = 0; k < cellnum; k++) {
								HSSFCell cell =row.getCell(k);
								
								//根据字段类型动态拼凑sql
								if(typemap.get(colList.get(k)).equals("NUMBER")){
									valuesql+=(cell.getStringCellValue().equals("")?"null":cell.getStringCellValue())+",";
								}else if(typemap.get(colList.get(k)).equals("DATE")){
									valuesql+=(cell.getStringCellValue().equals("")?"null":"to_date('"+cell.getStringCellValue()+"','yyyy-mm-dd hh24:mi:ss')")+",";;
								}else{
									valuesql+="'"+cell.getStringCellValue()+"',";
								}
								}
							valuesql=valuesql.substring(0, valuesql.length()-1);
							valuesql+=")";
						}
						if(valuesql!=null&&valuesql.length()>0){
							
						System.out.println(firstsql+valuesql);
						//数据入库
						selectDataService.update(firstsql+valuesql);
						}
						
					}
				}
			}
			
			
			
			
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 分页展示
	 * @return
	 */
	public String getList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取参数
		String cuurentPage = request.getParameter("currentPage")==null?"1":request.getParameter("currentPage");
		
		//初始化分页对象
		//每页显示条数
		int pageSize = 3;
		PageView<User> pageView = new PageView<User>(pageSize, Integer.parseInt(cuurentPage));
		
		//获取分页数据
		
		QueryResult<User> queryResult =	userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), null, null, null);
		pageView.setQueryResult(queryResult);
		
		//数据传递到前台
		request.setAttribute("pageView", pageView);
		//跳转页面
		return "list";
	}
	
	
	public String addPage(){
		return "addPage";
	}
	
	public void add(){
		userService.addUser(user);
		
		Response.write("ok");
		
	}
	
	
	public String editPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		user =userService.getUserById(user.getId());
		
		//查询所有小组
		groups =groupService.findAll();
		request.setAttribute("groups", groups);
		return "editPage";
	}
	
	public void edit(){
		userService.updateUser(user);
		Response.write("ok");
	}
	
	
	

	
	public String pagelist() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		PageView<User> pageView = new PageView<User>(10, currentPage);
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
		pageView.setQueryResult(userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),hql.toString(), params.toArray(), orderby));
		request.setAttribute("pageView", pageView);
		return "list";
	}
	

}
