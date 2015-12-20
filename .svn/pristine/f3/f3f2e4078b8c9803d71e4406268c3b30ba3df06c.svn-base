<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css"></link>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
		<SCRIPT type="text/javascript">
		<!--
		var setting = {
				callback: {
					onClick: zTreeOnClick
				},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		function zTreeOnClick(event, treeId, treeNode){
			$('#pid',window.parent.document).val(treeNode.id);
			$('#pname',window.parent.document).val(treeNode.name);
			window.parent.closeTree();
		}
		


		$(document).ready(function(){
			
			var zNodes =null;
			$.ajax({
	  			url:'${pageContext.request.contextPath}/user!getUsers.action',
	  			type:'post',
	  			async:false,
	  			data:$('#loginform').serialize(),
	  			success:function(data){
	  				zNodes =eval(data);
	  			}
	  		});
			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			//获取父页面节点值
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = treeObj.getNodeByParam("id", $('#pid',window.parent.document).val(), null);
				treeObj.selectNode(node);
		});
		//-->
	</SCRIPT>
  </head>
  
  <body>
    <div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
  </body>
</html>
