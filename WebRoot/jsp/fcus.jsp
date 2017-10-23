<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection"content="telephone=no, email=no" />
<script src='${ctx}/js/jquery/jquery-1.7.2.min.js'></script>
<script type="text/javascript">
	$(function() {
		//var orderid = "${orderid}";
		//window.self.location = "${url}";
		//window.location.href = "${url}";
	});
</script>
</head>
<body>
<br/>
<br/>
<br/>
<br/><br/>
<div ><img style="width: 250px;height: 250px" src="${ctx}/images/login.jpg" alt="二维码图片" /></div>
</body>
</html>
