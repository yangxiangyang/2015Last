<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="/struts-tags" prefix="s" %>
    
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
</head>
<body>
   <form action="<%=request.getContextPath() %>/user!edit.action" method="post" >
     用户名 ：<input type="text" id="userName" name="user.userName" value="${user.userName}" class='validate[required]'><br>
     密码：<input type="text"  id="userPassword" name="user.userPassword" value="${user.userPassword}"><br>
     年龄：<input type="text" value="${user.userAge}" id="userAge" name="user.userAge"><br>
     性别：<select id="userSex" name="user.userSex">
          <option <c:if test="${user.userSex == '男'}">selected="selected"</c:if>	 value="男">男</option>
          <option <c:if test="${user.userSex == '女'}">selected="selected"</c:if> value="女">女</option>
       </select><br>
    所属小组：	<select id="group" name="user.group.id">
    			<c:forEach items="${groups}" var="group">
    				<option	<c:if test="${group.id ==user.group.id}">selected="selected"</c:if> value="${group.id}">${group.groupName}</option>
    			</c:forEach>
       </select><br>
       <input type="hidden" id="id" value="${user.id}" name="user.userAge">
     <input type="button" value="修改" onclick="checkEdit()" id="sub">
   </form>
</body>
  <script type="text/javascript">
     function userName(){
    	 var searchName=$("#userName").val();
    	 if(userName==null){
    		 alert("用户名不能为空");
    		 document.getElementById("sub").disabled=true;
    	 }else{
    		 document.getElementById("sub").disabled=false;
    		 
    	 }
     }
     
      function checkEdit(){
	  //校验输入是否合法
	  var flag =validation();
	  
	  if(flag){
		  
		  $.post(
			'user!edit.action',
			{"user.id":$('#id').val(),"user.userName":$('#userName').val(),"user.userPassword":$('#userPassword').val(),"user.userAge":$('#userAge').val(),"user.userSex":$('#userSex').val(),"user.group.id":$('#group').val()},
			function(data){
				if(data=='ok'){
					$.messager.alert('提示','修改成功','info',function(){
						parent.location.reload();
					});
				}
			}
		  
		  );
	  }
  }
  </script>
</html>