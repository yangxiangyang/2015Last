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
	<body class="easyui-layout" >
		<div data-options="region:'north',border:false" style="height:60px;background:#9EFF16;padding:10px">
			<div  style="border:1px; font-size: 20px;color:blue;float: left;" align="left">
				<img src="${pageContext.request.contextPath}/img/mainlogo.jpg"/>
			</div>
			<div align="right">
				<!--当前登录用户为:<%=session.getAttribute("user")%>  -->
				<label style="color:green;font-size: 14px">当前登录用户:杨向阳</label>
				<a href="javascript:quit();" class="easyui-linkbutton">注销</a>
			</div>
		</div>
		<div data-options="region:'west',split:true,title:'功能管理'" style="width:200px;padding:10px;" >
           		 <div class="easyui-accordion" data-options="fit:true,border:false">
						<ul class="easyui-tree" lines="true">   
						    <li state="open">   
						        <span>管理系统</span>   
						        <ul>   
						            <li>   
						                <span><a href="dept!listPage.action" target="jf">部门管理</a></span>   
						            </li>   
						            <li>   
						                <span><a href="user!listPage.action" target="jf">用户管理</a></span>   
						            </li>   
						            <li>   
						                <span><a href="asset!listPage.action" target="jf">资产管理</a></span>   
						            </li>  
						            <li>   
						                <span>报表模块</span>   
						            </li>  
						            <li>   
						                <span>财务管理</span>   
						            </li>  
						            <li>   
						                <span>信息交流</span>   
						            </li>  
						            <li state="open">   
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
		<div id="center" data-options="region:'center',title:'欢迎！'">
			<iframe name="jf" frameborder="0"  style="width: 100%;height: 100%;"></iframe>
		</div>
	</body>
	<script type="text/javascript"></script>
</html>
