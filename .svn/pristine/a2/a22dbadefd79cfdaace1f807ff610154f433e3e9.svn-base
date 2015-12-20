<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/easyui-lang-zh_CN.js"></script>
  	<script type="text/javascript">
		$(function(){
			/* 加入工具栏 */
			var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
			pager.pagination({
				buttons:[{
					iconCls:'icon-search',
					text:'查询',
					handler:function(){
					}
				},{
					iconCls:'icon-add',
					text:'添加',
					handler:function(){
					/* 添加 */
						//addDept();
						location.href="${pageContext.request.contextPath}/dept!addPage.action";
					}
				},{
					iconCls:'icon-edit',
					text:'修改',
					handler:function(){
						var row=$('#dg').datagrid("getSelected");
						editDept(row.ID);
					}
				},{
					iconCls:'icon-remove',
					text:'批量删除',
					handler:function(){
						delAllDept();
					}
				}]
			});			
		});
		
		
		function getButtons(value,row,index){
			return '<input type="button" onclick="editDept(\''+row.ID+'\')" value="修改"><input type="button" onclick="delDept(\''+row.ID+'\')" value="删除">';
		}
		
		function editDept(id){
			openWindow("修改页面","dept!updatePage.action?id="+id);
		}
		
		function openWindow(title,url){
			$('#upwin').window({
				title:title,
			    width:600,    
			    height:400,
			    collapsible:false,
			    minimizable:false,
			    maximizable:false,
			    modal:true   
			});
			$('#upwin').html('<iframe id="child" name="child" src="${pageContext.request.contextPath}/'+url+'" frameborder="0" style="width: 100%;height: 100%"></iframe>');
		}
		
		function closeWindow(){
			$('#upwin').window("close");
		}
		
		/* function addDept(){
		 	//打开对话框，引入form表单 
			$('#addDept_dialog').dialog({    
			    title: '添加部门',    
			    width: 300,    
			    height: 200,    
			    closed: false,    
			    cache: false,    
			    href: '${pageContext.request.contextPath}/dept!addPage.action',    
			    modal: true,
			    buttons:[{
					text:'保存',
					handler:function(){}
				},{
					text:'关闭',
					handler:function(){}
				}]   
			});
		}; */
		
		
		function delDept(id){
			if(confirm("确定要删除吗")){
				$.post("${pageContext.request.contextPath}/dept!deleteDept.action?ids="+id,function(){
					$('#dg').datagrid('reload');    
				});						
			}
		}
		
		function delAllDept(){
			var rows=$('#dg').datagrid('getSelections');
			var ids='';
			for(var i in rows){
				ids+=rows[i].ID+",";
			}
			ids=ids.substring(0, ids.length-1);
			alert(ids);
			if(confirm("确定要批量"+ids+"删除吗")){
				$.post("${pageContext.request.contextPath}/dept!deleteDept.action?ids="+ids,function(){
					$('#dg').datagrid('reload');    
				});						
			}
		}
		
		function flushDate(){
			$('#dg').datagrid('reload');
		}
		
	</script>
  		

  </head>
  
  <body>
  <!-- <div id="addDept_dialog"></div> -->
  <div id="upwin"></div>  
  
	<table id="dg" title="部门数据展示列表"
			data-options="fit:true,rownumbers:true,pageSize:20,singleSelect:false,pagination:true,url:'${pageContext.request.contextPath}/dept!listData.action',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'a',width:80,checkbox:true">复选框</th>
				<th data-options="field:'ID',width:80">编号</th>
				<th data-options="field:'NAME',width:100">部门名称</th>
				<th data-options="field:'PNAME',width:80">上级部门</th>
				<th data-options="field:'FIRSTUSER',width:80">部门正职</th>
				<th data-options="field:'SECONDUSER',width:240">部门副职</th>
				<th data-options="field:'SECRETARY',width:240">部门秘书</th>
				<th data-options="field:'操作',width:100,align:'center',formatter:getButtons">操作</th>
			</tr>
		</thead>
	</table>
	
  </body>
</html>
