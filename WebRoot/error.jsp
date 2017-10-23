<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>提示</title>
		<!-- 引入 WeUI -->
		<link rel="stylesheet" href="${ctx}/dist/style/weui.min.css" />
		<link rel="stylesheet" href="${ctx}/dist/example/example.css" />
	</head>

	<body>
		<div class="container" id="container">
			<div class="weui-flex" style="height: 100%;margin:0 auto;text-align:center;">
				<div class="weui-flex__item">
					<div class="placeholder" style="text-align:center; "> <i class="weui-icon-info weui-icon_msg"></i></div>
				</div>
				<div class="weui-flex__item">
					<div class="placeholder" style="text-align:center; ">
						<div class="icon-box__ctn">
							<h2 class="icon-box__title">提示</h2>
							<p class="icon-box__desc">用于表示信息提示；也常用于缺乏条件的操作拦截，提示用户所需信息</p>
						</div>
					</div>
				</div>
			</div>
			</div>
			<script src="${ctx}/dist/example/zepto.min.js"></script>
			<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
			<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
			<script src="${ctx}/dist/example/example.js"></script>
	</body>

</html>