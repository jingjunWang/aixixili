<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>查看发票</title>
		
		<!-- 引入 WeUI -->
		<link rel="stylesheet" href="${ctx}/dist/style/weui.min.css" />
		<link rel="stylesheet" href="${ctx}/dist/example/example.css" />
	</head>

	<body>
	
	
	<div class="weui-cells__title"></div>
		
		<div class="weui-cells__title">我开过的发票</div>
		
		
		<div class="weui-cells__title">个人发票</div>
		<div class="weui-cells">
			<c:forEach items="${inpersonal }" var= "p">
				 <a class="weui-cell weui-cell_access" href="${p.sp_url }">
			        <div class="weui-cell__bd">
			            <p>${p.pName }     ${p.createDate }</p>
			        </div>
			        <div class="weui-cell__ft">
			        </div>
			    </a>
			</c:forEach>
		    <a class="weui-cell weui-cell_access" href="javascript:;">
		        <div class="weui-cell__bd">
		            <p>cell standard</p>
		        </div>
		        <div class="weui-cell__ft">
		        </div>
		    </a>
		    <a class="weui-cell weui-cell_access" href="javascript:;">
		        <div class="weui-cell__bd">
		            <p>cell standard</p>
		        </div>
		        <div class="weui-cell__ft">
		        </div>
		    </a>
		</div>
		<div class="weui-cells__title">企业发票</div>
		<div class="weui-cells">
			<c:forEach items="${inEnterprise }" var= "p">
				 <a class="weui-cell weui-cell_access" href="${p.sp_url }">
			        <div class="weui-cell__bd">
			            <p>${p.pName }     ${p.createDate }</p>
			        </div>
			        <div class="weui-cell__ft">
			        </div>
			    </a>
			</c:forEach>
		    <a class="weui-cell weui-cell_access" href="javascript:;">
		        <div class="weui-cell__bd">
		            <p>cell standard</p>
		        </div>
		        <div class="weui-cell__ft">
		        </div>
		    </a>
		    <a class="weui-cell weui-cell_access" href="javascript:;">
		        <div class="weui-cell__bd">
		            <p>cell standard</p>
		        </div>
		        <div class="weui-cell__ft">
		        </div>
		    </a>
		</div>
	</body>

</html>
