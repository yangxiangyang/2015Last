<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var idkey='';
	var namekey='';
	
	function getDeptTree(){
		idkey =$('#'+event.target.id).prev().attr('id');
  		namekey =event.target.id;
		openWindow("选择部门",'dept!deptTreePage.action');
	}	
	function openWindow(title,url){
			$('#deptwin').window({
				title:title,
			    width:200,    
			    height:250,
			    collapsible:false,
			    minimizable:false,
			    maximizable:false,
			    modal:true   
			});
			$('#deptwin').html('<iframe id="child" name="child" src="${pageContext.request.contextPath}/'+url+'" frameborder="0" style="width: 100%;height: 100%"></iframe>');
	}
	
	function closeTree(){
		$("#deptwin").window("close");
	}
		
</script>

</head>
<body>
	<div id="deptwin"></div> 

  <form id='userForm' >
   <!--/select id, loginname, password, realname, age, sex, phone, mail, birthday, deptid from t_user;  -->
 <table>
 	<tr>
 		<td>登陆名:</td>
 		<td>
 			<input type="hidden" id="id" class='validate[required]' name="user.id" >
 			<input type="text" id="loginname" class='validate[required]' name="user.loginname" >
 		</td>
 	</tr>
 	<tr>
 		<td>密码:</td>
 		<td><input type="text" id="password" class='validate[required]' name="user.password"></td>
 	</tr>
 	<tr>
 		<td>真实姓名：</td>
 		<td><input type="text" id="realname" class='validate[required]' name="user.realname" ></td>
 	</tr>
 	<tr>
 		<td>年龄:</td>
 		<td><input type="text" id="age" name="user.age"></td>
 	</tr>
 	<tr>
 		<td>性别:</td>
 		<td>
 			 <select id="sex" name="user.sex">
	          <option value="男">男</option>
	          <option value="女">女</option>
	       </select>
 		</td>
 	</tr>
 	<tr>
 		<td>手机号:</td>
 		<td><input type="text" id="phone" class='validate[required]' name="user.phone"></td>
 	</tr>
 	<tr>
 		<td>邮箱:</td>
 		<td><input type="text" id="mail" class='validate[required]' name="user.mail"></td>
 	</tr>
 	<tr>
 		<td>生日:</td>
 		<td><input type="text" id="birthday" class='validate[required]' name="user.birthday" onclick="WdatePicker()"></td>
 	</tr>
 	<tr>
 		<td>所属部门:</td>
 		<td>
 			<input type="text" id="deptid" name="user.deptid"/>
 			<input type="text" id="deptname"   onclick="getDeptTree()"/>
 		</td>
 	</tr>
 	<tr>
 		<td colspan="2"><input type="button" value="注册" onclick="checkAdd()" id="sub"></td>
 		<td></td>
 	</tr>
 </table>  
     
   </form>
</body>
  <script type="text/javascript">
  
  
  
   function checkAdd(){
   		//alert("测试表单数据---"+$("#userForm").serialize());
   		$.post("${pageContext.request.contextPath}/user!addUser.action",$("#userForm").serialize(),function(data){
   			if(data=="ok"){
   				//alert("ok");
   				window.parent.closeWindow();
   				window.parent.flushDate();
   			}else{
   				alert("用户注册失败");
   			}
   		});
		 
  }
  
 /*  $(function(){
	  //初始化小组数据
	  getGroups();
  }); */
  
 /*  function getGroups(){
	  $.get('group!listAll.action',
		{},
		function(data){
			var groups =eval(data);
			for(var i=0;i<groups.length;i++){
				$('#group').append('<option value="'+groups[i].id+'">'+groups[i].groupName+'</option>');
			}
		}
	  
	  );
  } */
  
 /*  function checkAdd(){
	  //校验输入是否合法
	  var flag =validation();
	  
	  if(flag){
		     //<!--/select id, loginname, password, realname, age, sex, phone, mail, birthday, deptid from t_user;  -->
		  
		  $.post(
			'user!addUser',
			{"user.loginname":$('#loginname').val(),"user.password":$('#password').val(),"user.realname":$('#realname').val(),
			"user.age":$('#age').val(),"user.sex":$('#sex').val(),"user.phone":$('#phone').val(),"user.mail":$('#mail').val(),
			"user.birthday":$('#birthday').val(),"user.dept.id":$('#dept').val()},
			function(data){
				if(data=='ok'){
					$.messager.alert('提示','新增成功','info',function(){
						//parent.location.reload();
					});
				}
			}
		  
		  );
	  }
  } */
  	
  
  
  </script>
</html>