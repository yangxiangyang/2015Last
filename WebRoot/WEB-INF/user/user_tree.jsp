<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css"></link>
	<script src="${pageContext.request.contextPath}/js/jqueryui/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
	
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jqueryui/themes/base/jquery.ui.all.css">
	<script src="${pageContext.request.contextPath}/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="${pageContext.request.contextPath}/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="${pageContext.request.contextPath}/js/jqueryui/ui/jquery.ui.position.js"></script>
	<script src="${pageContext.request.contextPath}/js/jqueryui/ui/jquery.ui.autocomplete.js"></script>
	
	<SCRIPT type="text/javascript" >
		var availableTags = new Array();
		var zTreeObj,
		setting = {
				data: {
						simpleData: {
								enable: true
						}
				},	
				callback: {
					onClick: zTreeOnClick
				}
		};
		
		
	
		
		$(document).ready(function(){
			/* jquryui */
			
			var zTreeNodes;
			$.ajax({
				url:"${pageContext.request.contextPath}/user!getUsers.action",
				type:"post",
				async:false,
				dataType:"json",//测试
				success:function(data){
					zTreeNodes=eval(data);
					for(var i in zTreeNodes){//遍历配置资源，数组
						availableTags[i]=zTreeNodes[i].name;
					}
				}
			});
			zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
			
			
			$( "#tags" ).autocomplete({
				source: availableTags,//资源数组
				select: function( event, ui ) {
							//根据name获取节点
							var treeNode= treeObj.getNodeByParam("name", ui.item.value, null);
							zTreeOnClick(event,null,treeNode);
						}
			});
			
			//获取父页面节点值
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var node = treeObj.getNodeByParam("id", $('#'+window.parent.idkey,window.parent.document).val(), null);
				treeObj.selectNode(node);
	
		});
		
		function zTreeOnClick(event, treeId, treeNode) {
			if(treeNode.ISPARENT=='0'){
				alert('请选择正确的用户！');    
				//return ;//直接返回
			}else{
			   $('#'+window.parent.idkey,window.parent.document).val(treeNode.id);
				$('#'+window.parent.namekey,window.parent.document).val(treeNode.name);
				window.parent.closeTree();
			}
		};
		
		
		
		
  </SCRIPT>
	 </head>
  
  <body>
	<div class="ui-widget">
		<input id="tags" />
	</div>
  <div>
  	<ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
  </div>
  </body>
</html>
