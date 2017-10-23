<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html>
	<head>
		<title></title>
		<script src='${ctx}/js/jquery/jquery-1.7.2.min.js'></script>
		<script type="text/javascript">
		$(function(){
			//var orderid = "${orderid}";
			alert('${url}');
			window.self.location = "${url}";
			//window.location.href = "${url}";
		});
		</script>
	</head>
	<body>
	</body>
</html>
