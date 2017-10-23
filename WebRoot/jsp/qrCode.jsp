<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<script src='${ctx}/js/jquery/jquery-1.7.2.min.js'></script>
	<script type="text/javascript" src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript">
		  wx.config({
	        	debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	         	appId: 'wx99a066ed3be3d52e', // 必填，公众号的唯一标识
			    timestamp: 1462521975, // 必填，生成签名的时间戳
			    nonceStr: '9f2c415b-0b8a-48be-ae2c-b4b92a76f494', // 必填，生成签名的随机串
			    signature: '0f978b1b7884092cfb825fed7ea18bc9b9fd3b27',// 必填，签名，见附录1
			    jsApiList: ['onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	
	    });
		
		$(function(){
		
			//var orderid = "${orderid}";
			alert("hello");
		});
		wx.ready(function () {var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
                if (ua.match(/MicroMessenger/i) == "micromessenger") {//在微信中打开
                    wx.scanQRCode({
                        needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                        scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                        success: function (res) {
                            var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                            if(result){
                                window.location.href = result;
                            }
                            //alert(result);
                        }
                    });
                }else{
                    alert('请在微信中使用扫描功能');
                } })
		
		
		</script>

  </head>
  
  <body>
    This is my JSP page. <br>
    
    
  </body>
</html>
