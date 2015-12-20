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
		<script type="text/javascript">
		</script>
	</head>
	<body >
		<h2 align="center">学院管理</h2>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" class="easyui-datagrid" title="学院管理" style="width:90%;height:auto"
			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${pageContext.request.contextPath}/college!list.action',method:'get',
			onClickCell: onClickCell,onAfterEdit:onAfterEdit">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'collegeName',editor:'text'">学院名称</th>
				<th data-options="field:'aaa',editor:'text'">操作</th>
			</tr>
		</thead>
	</table>

	<script type="text/javascript">
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			}
		});
		
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('editCell', {index:index,field:field});
				editIndex = index;
			}
		}
		function onAfterEdit(index, rowData){
			$.messager.confirm('确认对话框', '您确定要保存吗？', function(r){
				if (r){
				    $.ajax({
				    	url:'${pageContext.request.contextPath}/college!edit.action',
				    	type:'post',
				    	data:{"college.id":rowData.id,"college.collegeName":rowData.collegeName},
				    	success:function(data){
				    		if(data=='true'){
				    			$.messager.alert('我的消息','修改成功！','info',function(){
				    				$('#dg').datagrid('reload');
				    			});
				    		}
				    	}
				    });
				}
			});


			console.log(rowData);
		}
		
	</script>
</body>
</html>