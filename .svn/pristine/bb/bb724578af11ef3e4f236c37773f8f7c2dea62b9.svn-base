<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dept_tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css"></link>
	<script src="${pageContext.request.contextPath}/js/jqueryui/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
	<SCRIPT type="text/javascript" >
		var zTreeObj,
		setting = {
				view: {
					selectedMulti: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: zTreeOnClick
				}
		};
		
		/* 树形部门 */
	
		$(document).ready(function(){
			var zTreeNodes;
			$.ajax({
				url:"${pageContext.request.contextPath}/dept!deptTree.action",
				type:"post",
				async:false,
				dataType:"json",//测试
				success:function(data){
					zTreeNodes=eval(data);
				}
			});
			zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
			
			//回显节点
			var treeObj = $.fn.zTree.getZTreeObj("tree");//获取当前树，获取节点，选中节点
			var node = treeObj.getNodeByParam("id", $('#'+window.parent.idkey,window.parent.document).val(), null);
				treeObj.selectNode(node);
	
		});
		
		
		function zTreeOnClick(event, treeId, treeNode) {
		   $('#'+window.parent.idkey,window.parent.document).val(treeNode.id);
			$('#'+window.parent.namekey,window.parent.document).val(treeNode.name);
			window.parent.closeTree();
		};
  </SCRIPT>
	

  </head>
  
  <body>
  
  <ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
  </body>
</html>
