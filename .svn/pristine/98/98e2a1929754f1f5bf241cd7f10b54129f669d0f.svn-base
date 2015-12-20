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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css"></link>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript">
		function openTree(){
			openWindow('部门',300,500,'user!tree.action');
		}
		function closeTree(){
			$('#win').window('close');
		}
		
		function openWindow(title,width,height,url){
			$('#win').window({
				title:title,
			    width:width,    
			    height:height,
			    //相对居中
			    left:($(window).width()-width)*0.5,
				top:($(window).height()-height)*0.5,
			    collapsible:false,
			    minimizable:false,
			    maximizable:false,
			    modal:true   
			});
			$('#win').html('<iframe id="child" name="child" src="${pageContext.request.contextPath}/'+url+'" frameborder="0" style="width: 100%;height: 100%"></iframe>');
		}
		</script>
	</head>
	<body >
			
			<div id="win" >
			</div>  
			<input type="text" name="pid" id="pid" >
			上级部门<input type="text" name="pname" id="pname" onclick="openTree()">
			
	</body>
	
	
</html>
