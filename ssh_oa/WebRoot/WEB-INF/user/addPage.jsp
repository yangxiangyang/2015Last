<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
</head>
<body>
   <form action="<%=request.getContextPath() %>/user!add.action" method="post" >
   
   
   
     用户名 ：<input type="text" id="userName" class='validate[required]' name="user.userName" ><br>
     密码：<input type="text" id="userPassword" class='validate[required]' name="user.userPassword"><br>
     年龄：<input type="text" id="userAge" name="user.userAge"><br>
     性别：<select id="userSex" name="user.userSex">
          <option value="男">男</option>
          <option value="女">女</option>
       </select><br>
       所属小组：<select id="group" name="user.group.id">
       </select><br>
     
     <input type="button" value="注册" onclick="checkAdd()" id="sub">
   </form>
</body>
  <script type="text/javascript">
  
  $(function(){
	  //初始化小组数据
	  getGroups();
  });
  
  function getGroups(){
	  $.get(
		'group!listAll.action',
		{},
		function(data){
			var groups =eval(data);
			for(var i=0;i<groups.length;i++){
				$('#group').append('<option value="'+groups[i].id+'">'+groups[i].groupName+'</option>');
			}
		}
	  
	  );
  }
  
  function checkAdd(){
	  //校验输入是否合法
	  var flag =validation();
	  
	  if(flag){
		  
		  $.post(
			'user!add.action',
			{"user.userName":$('#userName').val(),"user.userPassword":$('#userPassword').val(),"user.userAge":$('#userAge').val(),"user.userSex":$('#userSex').val(),"user.group.id":$('#group').val()},
			function(data){
				if(data=='ok'){
					$.messager.alert('提示','新增成功','info',function(){
						parent.location.reload();
					});
				}
			}
		  
		  );
	  }
  }
  	
  
  
  </script>
</html>