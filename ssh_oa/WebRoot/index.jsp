<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<title>展示页面</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			function quit(){
				$.messager.confirm('确认框', '您想要注销吗？', function(r){
					if (r){
					    location.href="login.jsp";
					}
				});
			}
		</script>
	</head>
	<body class="easyui-layout" ondblclick="aaa()">
		<div data-options="region:'north',border:false" style="height:88px;background:#B3DFDA;padding:10px">
			<div style="font-size: 30px;color: red;" align="center">欢迎进入班级勤务管理系统</div>
			<div align="right">
				<label style="color:green;font-size: 20px">当前登录用户为:<%=session.getAttribute("user")%></label>
				<a href="javascript:quit();" class="easyui-linkbutton">注销</a>
			</div>
		</div>
		<div data-options="region:'west',split:true,title:'功能管理'" style="width:200px;padding:10px;" >
           		 <div class="easyui-accordion" data-options="fit:true,border:false">
						<ul class="easyui-tree" lines="true">   
						    <li state="closed">   
						        <span>班级勤务管理系统</span>   
						        <ul>   
						            <li>   
						                <span><a href="college!listPage.action" target="jf">学院管理</a></span>   
						            </li>   
						            <li>   
						                <span><a href="getListGroup.action" target="jf">小组管理</a></span>   
						            </li>   
						            <li>   
						                <span>违纪管理</span>   
						            </li>  
						            <li>   
						                <span>流程管理</span>   
						            </li>  
						            <li>   
						                <span>报表模块</span>   
						            </li>  
						            <li>   
						                <span>打卡管理</span>   
						            </li>  
						            <li>   
						                <span>班级财务管理</span>   
						            </li>  
						            <li>   
						                <span>信息交流</span>   
						            </li>  
						            <li state="closed">   
						                <span>通信管理</span>
						                <ul>
						                	<li>
						                		<span>邮箱</span>   
						                	</li>
						                	<li>
						                		<span>收件箱</span>   
						                	</li>
						                	<li>
						                		<span>发件箱</span>   
						                	</li>
						                	<li>
						                		<span>草稿箱</span>   
						                	</li>
						                </ul>   
						            </li>   
						        </ul>   
						    </li>   
						</ul> 
        	</div>
		</div>
		<div align="center" data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">@大家购系统</div>
		<div id="center" data-options="region:'center',title:'欢迎！'">
			<iframe name="jf" frameborder="0"  style="width: 100%;height: 100%;"></iframe>
		</div>
	</body>
	<script type="text/javascript"></script>
</html>
