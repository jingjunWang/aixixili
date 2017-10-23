<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html>
	<head>
		<title></title>
		<script src='${ctx}/js/jquery/jquery-1.7.2.min.js'></script>
		<script src='http://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
		
		<script type="text/javascript">
		$(function(){
			wx.config({
			    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: '${weChatPayData.appid}', // 必填，公众号的唯一标识
			    timestamp: '${weChatPayData.timestamp}', // 必填，生成签名的时间戳
			    nonceStr: '${weChatPayData.nonceStr}', // 必填，生成签名的随机串
			    signature: '${weChatPayData.signature}',// 必填，签名，见附录1
			    jsApiList: ['openLocation',//这里不知道你用的地理接口是哪个就两个都写上了
      				'getLocation'
      				] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			
			wx.ready(function(){
				alert(1);
			    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			});
			wx.error(function(res){
				alert(2);
			    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			});
		});
		</script>
	</head>
	<body>
	</body>
</html>
