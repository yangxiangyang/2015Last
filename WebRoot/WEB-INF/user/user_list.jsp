<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_list.jsp' starting page</title>
    
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
		function addUser(){
			openWindow("新增用户页面","user!addPage.action");
		}
		
		function getButtons(value,row,index){
			return '<input type="button" onclick="editUser(\''+row.ID+'\')" value="修改"><input type="button" onclick="delUser(\''+row.ID+'\')" value="删除">';
		}
		function delUser(id){
			if(confirm("确定要删除吗")){
				$.post("${pageContext.request.contextPath}/user!deleteUser.action?ids="+id,function(){
					$('#user_dg').datagrid('reload');    
				});						
			}
		}
		function editUser(id){
			var rows = $('#user_dg').datagrid('getSelected');
			alert(rows);
			$('#user_dg').form('load',rows);
			
			openWindow("用户修改页面","user!addPage.action");
		
		}
		function editUser1(id){
			openWindow("用户修改页面","user!updatePage.action?id="+id);
		}
		
		function delAllUser(){
			var rows=$('#user_dg').datagrid('getSelections');
			var ids='';
			for(var i in rows){
				ids+=rows[i].ID+",";
			}
			ids=ids.substring(0, ids.length-1);
			alert(ids);
			if(confirm("确定要批量"+ids+"删除吗")){
				$.post("${pageContext.request.contextPath}/user!deleteUser.action?ids="+ids,function(){
					$('#user_dg').datagrid('reload');    
				});						
			}
		}
		
		function flushDate(){
			$('#user_dg').datagrid('reload');
		}
				
		function openWindow(title,url){
			$('#userwin').window({
				title:title,
			    width:600,    
			    height:400,
			    top:100,
			    collapsible:false,
			    minimizable:false,
			    maximizable:false,
			    modal:true   
			});
			$('#userwin').html('<iframe id="child" name="child" src="${pageContext.request.contextPath}/'+url+'" frameborder="0" style="width: 100%;height: 100%"></iframe>');
		}
		
		function closeWindow(){
			$('#userwin').window("close");
		}
		
		
		function getDate(value,row,index){
			var curDate =new Date(value.time);
			return curDate.getFullYear()+"-"+(curDate.getMonth()+1)+"-"+curDate.getDate();
		}
		
		</script>
  </head>
  
  <body>
  
  	<div id="userwin"></div>  
    <table id="user_dg" class="easyui-datagrid" title="用户数据展示列表"
			data-options="fit:true,rownumbers:true,pageSize:10,singleSelect:false,pagination:true,
			toolbar: [{
					iconCls: 'icon-add',
					text:'添加用户',
					handler: function(){
						addUser();
					}
				},'-',{
					iconCls: 'icon-help',
					text:'寻求帮助',
					handler: function(){alert('帮助按钮')}
				}],
			url:'${pageContext.request.contextPath}/user!userData.action',method:'get'">
		<thead>
			<tr>
			<!-- /select id, loginname, password, realname, age, sex, phone, mail, birthday, deptid from t_user; -->
				<th data-options="field:'a',width:80,checkbox:true">复选框</th>
				<th data-options="field:'ID',width:80">编号</th>
				<th data-options="field:'LOGINNAME',width:100">登录名</th>
				<th data-options="field:'PASSWORD',width:80">密码</th>
				<th data-options="field:'REALNAME',width:80">真实名字</th>
				<th data-options="field:'AGE',width:100">年龄</th>
				<th data-options="field:'PHONE',width:100">手机号</th>
				<th data-options="field:'MAIL',width:100">邮箱</th>
				<th data-options="field:'SEX',width:100">性别</th>
				<th data-options="field:'BIRTHDAY',width:100,formatter:getDate">入职日期</th>
				<th data-options="field:'DNAME',width:100">所属部门</th>
				<th data-options="field:'操作',width:100,align:'center',formatter:getButtons">操作</th>
			</tr>
		</thead>
	</table>
	
  </body>
</html>
