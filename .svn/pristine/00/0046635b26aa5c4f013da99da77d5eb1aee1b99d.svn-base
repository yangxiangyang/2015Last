<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/easyui-lang-zh_CN.js"></script>
  	
  	<script type="text/javascript">
  	
  	function login(){
  		$.ajax({
  			url:'${pageContext.request.contextPath}/user!login.action',
  			type:'post',
  			data:$('#loginform').serialize(),
  			success:function(data){
  				if(data=='true'){
  					
  				}
  			}
  		});
  	}
  	</script>
  </head>
  
  <body>
    	<form id="loginform" action="">
    		<input class="easyui-textbox" name="loginName" data-options="iconCls:'icon-man',prompt:'登录名'" > <br>
    		<input class="easyui-textbox" name="password" data-options="iconCls:'icon-lock'" >  <br>
    		<a id="btn" href="#" onclick="login()" class="easyui-linkbutton">登陆</a>
    	</form>
  </body>
</html>
