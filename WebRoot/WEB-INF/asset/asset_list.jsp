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
		<style type="text/css">
			a{ text-decoration:none; }
		</style>	
  	<script type="text/javascript">
  		$(function(){
  			$('#asset_dg').datagrid({    
			    url:'${pageContext.request.contextPath}/asset!assetDate.action',
			    title:"部门数据展示列表",   
			    pagination:true,
			    pagePosition:'bottom',
			    rownumbers:true,
			    pageNumber:1,
			    pageSize:10,
			    singleSelect:true,
			    toolbar: '#tool',
			    
			    columns:[[    
			        {field:'ID',title:'ID',width:100},    
			        {field:'NUM',title:'资产编号',width:100,formatter:function(value,row,index){
			        	return "<a href='javascript:void(0)'>"+row.NUM+"</a>";
			        	//return '<a href="javascript:void(0)" onclick="showAsset(\''+row+'\'>"+row.NUM+"</a>';
			        }},    
			        {field:'NAME',title:'资产名称',width:100,formatter:function(value,row,index){
			        	return "<a href='javascript:void(0)'>"+row.NAME+"</a>";
			        }},    
			        {field:'TYPENAME',title:'资产类型',width:100},    
			        {field:'BUYDATE',title:'购买日期',width:100,formatter: function(value,row,index){
			     		var curDate=new Date(value.time);
						return curDate.getFullYear()+"/"+(curDate.getMonth()+1)+"/"+curDate.getDate();
						}
			        },    
			        {field:'USERNAME',title:'责任人',width:100},    
			        {field:'PRICE',title:'价格',width:100},    
			        {field:'FACTORY',title:'厂家',width:100,align:'left'} ,   
			        {field:'STATUS',title:'状态',width:100,align:'left',formatter:function(value,row,index){
			        	if(row.STATUS==1){
			        		return "在库";
			        	}else if(row.STATUS==2){
			        		return "出库";
			        	}
			        	return "禁用";
			        }} ,   
			        {field:'CONTENT',title:'备注',width:100,align:'left'} ,   
			        {field:'操作',title:'操作',width:100,formatter: function(value,row,index){
			        	return '<input type="button" value="修改" onclick="editAsset(\''+row.ID+'\')"/><input type="button" value="删除" onclick="delAsset(\''+row.ID+'\')"/>';
			        }}  
			    ]]    
			}); 
  		});
		
		function editAsset(id){
			openWindow("资产修改页面","asset!updatePage.action?assetid="+id);
		}
		function delAsset(id){
			if(confirm("确定要删除id="+id+"的信息吗？")){
				$.post("${pageContext.request.contextPath}/asset!deleteForm.action?assetid="+id,function(data){
					if(data=="ok"){
						$("#asset_dg").datagrid("reload");
						console.log("删除成功!");
					}
				});
			}
		}
		function addAsset(){
			openWindow("资产添加页面","asset!addPage.action");
		}
		
		function openWindow(title,url){
			$('#asset_win').window({    
			    title:title,
			    width:1000,    
			    height:500,
			    shadow:true,
			    modal:true
			}); 
			$('#asset_win').html('<iframe id="child" name="child" src="${pageContext.request.contextPath}/'+url+'" frameborder="0" style="width: 100%;height: 100%"></iframe>');
		}
		function closeWindow(){
			$("#asset_win").window("close");
			$("#asset_dg").datagrid("reload");
		}
		
		/* ------------查看资产---------------------- */
		function showAsset(id){
			console.log("测试查看功能");
			//openWindow("资产查看页面","asset!addPage.action");
		}
		
		function viewAsset(){
			var row=$('#asset_dg').datagrid('getSelected');
			if(row){
				$('#assetForm').form('load',row);
				$("#num").val(row.NUM);
				openWindow("资产查看页面","asset!addPage.action");
			}else{
				showMessage("请选择要查看的数据！ ");
			}
		}
		
		function showMessage(message){
			//$.messager.progress();
			$.messager.alert('提示信息',message,'info');
		}
		
	</script>

  </head>
  
  <body>
	  <div id="asset_win"></div> 	
	  <table id="asset_dg"></table>  
	  <div id="tool">
		<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="addAsset()" data-options="iconCls:'icon-save',plain:true">添加资产</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"   data-options="iconCls:'icon-tip',plain:true">查看</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">帮助</a>
		<a href="${pageContext.request.contextPath}/asset!exportAsset.action"  class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">导出</a>
	</div>
  
  </body>
</html>
