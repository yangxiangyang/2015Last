<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'dept_add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<%@include file="/src/easyui.jsp" %>
	<script type="text/javascript">
	
		var idkey='';
		var namekey='';
		
		/*树形人员选择  */
		function getUsers(){
			//获取前一个同辈元素的id//
			
			$('#users_dialog').dialog({    
			    title: '人员选择',    
			    width: 400,    
			    height: 200,    
			    closed: false,    
			    cache: false,
			    href: '${pageContext.request.contextPath}/user!userTree.action',    
			    modal: true   
			}); 
			
		}
		
		function setUser(id,value){
			$("#'+id+'").val(value);
		}	
		
		function closeDialog(){
			$('#users_dialog').window('close');
		}
		function closeTree(){
			console.log('close');
			$('#users_dialog').window('close');
		}
		function submitForm(){
			$("#dept_add").submit(function(e){
			alert($(this).serialize());
				e.preventDefault();
				$.post("${pageContext.request.contextPath}/dept!addDept.action",$(this).serialize(),function(data){
					if(data=='yes'){
						alert("部门添加");
					}
						alert("部门添加失败");
				},"text");
			});
		}
	</script>
  </head>
  
  <body>
  	<!--正职对话框 -->
  	<div id="users_dialog"></div>  
  	
  	<form id="dept_add" method="post" >  
  		<table>
  			<tr>
	  			<td><label for="name">部门编号:</label>   </td>
	  			<td><input class="easyui-validatebox" type="text" name="id" data-options="required:true" />   </td>
  			</tr>
  			<tr>
	  			<td><label for="name">部门名称:</label>   </td>
	  			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   </td>
  			</tr>
  			<tr>
	  			<td><label for="name">职能类型:</label>   </td>
	  			<td><input class="easyui-validatebox" type="text" name="typeid" data-options="required:true" />   </td>
  			</tr>
  			<tr>
	  			<td><label for="name">上级部门:</label>   </td>
	  			<td>
	  				<!--下拉菜单  -->
	  				<input id="cc" class="easyui-combobox" name="pid"   
    				data-options="valueField:'ID',textField:'NAME',url:'${pageContext.request.contextPath}/dept!ptree.action'" />  
	  			</td>
  			</tr>
  			<tr>
  				<td><label for="name">部门正职:</label>   </td>
	  			<td>
	  				<input type="text" id="firstuser" name="firstuser">
	  				<input class="easyui-validatebox" onclick="getUsers()" type="text" id="firstuserName"  data-options="required:true" />   
	  			</td>
  			</tr>
  			<tr>
  				<td><label for="name">部门副职:</label>   </td>
	  			<td>
	  			<input type="text" id="seconduser" name="seconduser">
	  			<input class="easyui-validatebox" onclick="getUsers()" type="text" id="seconduserName"  data-options="required:true" />   </td>
  			</tr>
  			<tr>
  				<td><label for="name">部门秘书:</label>   </td>
	  			<td>
	  			<input type="text" id="secretary" name="secretary">
	  			<input class="easyui-validatebox" onclick="getUsers()" type="text" id="secretaryName"  data-options="required:true" />   </td>
  			</tr>
  			
  			<tr>
  				<td colspan="2">
  					<input type="submit" value="提交" onclick="submitForm()"/>
  					<input type="reset" value="重置"/>
  				</td>
  			</tr>
  		</table>
</form> 
  
  
  </body>
</html>
  