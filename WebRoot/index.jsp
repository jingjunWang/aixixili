<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>开发票</title>
		<!-- <style>
		 input::-webkit-input-placeholder{
		    color: red;opacity:0.5;
		}
		</style> -->
		<!-- 引入 WeUI -->
		<link rel="stylesheet" href="${ctx}/dist/style/weui.min.css" />
		<link rel="stylesheet" href="${ctx}/dist/example/example.css" />
	</head>

	<body>
		<div class="container" id="container" >
			 <div class="weui-form-preview" id="head" >
	            <div class="weui-flex">
		            <div class="weui-flex__item"><div class="placeholder" style="text-align:center; "><img src="${ctx}/img/logo2.png" style="vertical-align:middle;"/></div></div>
		        </div>
		        <div class="weui-flex">
		            <div class="weui-flex__item"><div class="placeholder" style="text-align: center; ">公司说明</div></div>
		        </div>
	            <div class="weui-form-preview__bd">
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">发票金额</label>
	                    <span class="weui-form-preview__value">${amount}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">订单号</label>
	                    <span class="weui-form-preview__value">${orderid}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">订单日期</label>
	                    <span class="weui-form-preview__value">${date}</span>
	                </div>
	            </div>
	            <!--<div class="weui-form-preview__ft">
	                <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">操作</a>
	            </div>-->
	        </div>
			<div class="weui-tab" id="end" style="height: 55%;">
				<div class="weui-navbar">
					<div class="weui-navbar__item weui-bar__item_on">
						个人及政府事业单位
					</div>
					<div class="weui-navbar__item">
						企业
					</div>
				</div>
				<div class="weui-tab__panel">
					<div id="navbar_1">
						<div class="weui-cells__title">个人</div>
						<div class="weui-cells weui-cells_form">
							<form id="personal" name=personal method="post" action="">
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">发票抬头</label>
							        </div>
							       <input id="pname" name="p_name" class="weui-input weui-cell_warn" type="text" placeholder="个人名称（必填） "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select"  id="p_name">
							            <option selected style="display:none" ></option>
							            <c:forEach items="${name }" var="p">
							            	<option value="${p }" selected> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">手机</label>
							        </div>
							        <input id="pmphone" name="p_mphone"  class="weui-input"   placeholder="请输入您的手机（必填）" />
							        <div class="weui-cell__bd">
							            <select class="weui-select" id="p_mphone">
							             <option style="display:none" selected></option>
							            <c:forEach items="${phone }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							
							    <div class="weui-cell weui-cell_select weui-cell_select-after ">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">邮箱</label>
							        </div>
							       <input id="pemail" name="p_email" class="weui-input" type="text" placeholder="请输入您的邮箱 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select"  id="p_email">
							             <option style="display:none" selected></option>
							            <c:forEach items="${mail }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							
							<input  name="openid" value="${openid}"  type="hidden" />
							<input  name="amount" value="${amount}"  type="hidden" />
							<input name="orderid" value="${orderid}"  type="hidden" />
							<input name="date" value="${date}"  type="hidden" />
							<input name="payway" value="${payway}"  type="hidden" />
							</form>
						<!-- 使用 -->
						<a id="footer"  href="javascript:void(0)" onclick="personalfun()" class="weui-btn weui-btn_primary">开发票</a>
					</div>
				</div>
					
					<div id="navbar_2"  style="display: none;">
						<div class="weui-cells__title">企业</div>
						<div class="weui-cells weui-cells_form">
						<form id="enterprise" name=enterprise method="post" action="">
							<div class="weui-cells">
								    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">发票抬头</label>
								        </div>
								       <input id="ename" name="e_name" class="weui-input" type="text" placeholder="企业名称（必填） "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_name">
								            <option style="display:none" selected></option>
								            <c:forEach items="${ename }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">手机</label>
								        </div>
								       <input id="emphone"  name="e_mphone" class="weui-input" type="text"  placeholder="请输入您的手机(必填) "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_mphone">
								            <option style="display:none" selected></option>
								            <c:forEach items="${ephone }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after ">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">邮箱</label>
								        </div>
								       <input id="email" name="e_email" class="weui-input" type="text" placeholder="请输入您的邮箱 "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_email">
								            <option style="display:none" selected></option>
									            <c:forEach items="${email }" var="p">
									            	<option value="${p }"> ${p}</option>
									            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">识别号</label>
								        </div>
								       <input id="sbh" name="e_number" class="weui-input weui-cell_warn" type="text" placeholder="情输入您的18位的纳税人识别号(必填) "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_number">
								            <option style="display:none" selected></option>
								            <c:forEach items="${nsr }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after ">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">开户行</label>
								        </div>
								       <input id="e_bank" name="e_bank" class="weui-input " type="text" placeholder="请输入您的开户行 "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select" name="select2" id="nameSelect">
								            <c:forEach items="${bank }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after ">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">银行账号</label>
								        </div>
								       <input  name="e_account" class="weui-input " type="text" onKeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))" placeholder="请输入您的银行账号 "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_account">
								            <option style="display:none" selected></option>
								            <c:forEach items="${account }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after ">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">地址</label>
								        </div>
								       <input  name="e_address" class="weui-input" type="text" placeholder="请输入您的地址 "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_address">
								            <option style="display:none" selected></option>
								            <c:forEach items="${address }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
								    <div class="weui-cell weui-cell_select weui-cell_select-after ">
								        <div class="weui-cell__hd">
								            <label for="" class="weui-label">电话</label>
								        </div>
								       	<input  name="e_phone" class="weui-input" type="text" placeholder="请输入您的电话 "  />
								        <div class="weui-cell__bd">
								            <select class="weui-select"  id="e_phone">
								            <option style="display:none" selected></option>
								            <c:forEach items="${dh }" var="p">
								            	<option value="${p }"> ${p}</option>
								            </c:forEach>
								            </select>
								        </div>
								    </div>
							</div>
							<input  name="openid" value="${openid}"  type="hidden" />
							<input  name="amount" value="${amount}"  type="hidden" />
							<input  name="orderid" value="${orderid}"  type="hidden" />
							<input  name="date" value="${date}"  type="hidden" />
							<input  name="payway" value="${payway}"  type="hidden" />
							
							</form>
							<!-- 使用 -->
							<a id="footer"  href="javascript:void(0)" onclick="enterprisefun()" class="weui-btn weui-btn_primary">开发票</a>
						</div>
						
					</div>
				</div>
			</div>
			    <!--BEGIN toast-->
			     <!-- loading toast style="display:none;"-->
		    <div id="loadingToast"  style="display:none;">
		        <div class="weui-mask_transparent"></div>
		        <div class="weui-toast">
		            <i class="weui-loading weui-icon_toast"></i>
		            <p class="weui-toast__content">数据加载中</p>
		        </div>
		    </div>
		    
		    <div id="toast" style="display: none;">
		        <div class="weui-mask_transparent"></div>
		        <div class="weui-toast">
		            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
		            <p class="weui-toast__content">已完成</p>
		        </div>
		    </div>
		    <!--end toast-->
			<div id="dialog" style="display: none;">
			    <div class="weui-mask"></div>
			    <div class="weui-dialog">
			        <div class="weui-dialog__hd"><strong class="weui-dialog__title">警告</strong></div>
			        <div class="weui-dialog__bd" id="message"></div>
			        <div class="weui-dialog__ft">
			            <a href="javascript:void(0);" onclick="back()" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
			        </div>
			    </div>
			</div>
	
		
		</div>
		<script src="${ctx}/dist/example/zepto.min.js"></script> 
		<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
		<script src="${ctx}/dist/example/example.js"></script>
		<script type="text/javascript">
			$(function() {
				$('select').val('');
				$(document).on("change",'select',function(){
				    var $this = $(this);
				    var val =  $this.val();
				 	$this.parent().siblings('input').val(val);
				 	$this.val("");
				 });
				
			
				$("#end").height(($("#container").height()-$("#head").height())-2);
				$('.weui-navbar__item').on('click', function() {
					$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
					var html = $(this).html();
					if(html.indexOf('企业') >0){
						//alert(1);
						$('#navbar_1').css('display', 'none');
						$('#navbar_2').css('display', 'block');
					}else{
						//alert(2);
						$('#navbar_1').css('display', 'block');
						$('#navbar_2').css('display', 'none');
					}
					
				});
			});
			var $loadingToast = $('#loadingToast');
			var $dialog = $('#dialog');
			//个人
			function personalfun(){
				//alert("nihao");
				
				//alert("get in personal");
				var personal = $("#personal").serialize();
				var pname = document.getElementById("pname").value;
				var pphone = document.getElementById("pmphone").value;
				var pemail = document.getElementById("pemail").value;
				//alert(pphone)
				//alert(getBytesLength(pname));
				if(pname == null || pname==""){
					document.getElementById("message").innerHTML="纳税人抬头名称不可以为空";
					$dialog.fadeIn(100);
				}else if(getBytesLength(pname)>100){
					document.getElementById("message").innerHTML="纳税人抬头名称过长,请重新输入";
					$dialog.fadeIn(100);
				}else
				if(pphone == null || pphone==""){
					document.getElementById("message").innerHTML="手机号码不能为空";
					$dialog.fadeIn(100);
				}else
				if(!(/^[1][3,4,5,6,7,8][0-9]{9}$/.test(pphone))){
					document.getElementById("message").innerHTML=pphone+"不是有效的手机号码";
					$dialog.fadeIn(100);
				}else if(pemail == null || pemail==""){
					document.getElementById("message").innerHTML="邮箱不可为空！";
					$dialog.fadeIn(100);
				}else
				if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ .test(pemail))){
					document.getElementById("message").innerHTML=pemail+"不是有效的邮箱";
					$dialog.fadeIn(100);
				}else{
				$loadingToast.fadeIn(100);
	
			 $.ajax({
						url:"${ctx}/weixin/personal",
						type:"POST",
						//async: false,//取消异步
						dataType:"json",
						data:personal,
						success: function(data){
							if(data != null && data != ""){
								if(data.success){
									$loadingToast.fadeOut(100);
									alert(data.message);
									window.self.location = data.message;
								}else{
									alert(data.message);
									$loadingToast.fadeOut(100);
								}
							}else{
								window.self.location = "weixin/error";
							}
						
						},
						error: function(err){
							console.log("error");
							if(err != null && err != ""){
								window.self.location = "weixin/error";
							}
							$loadingToast.fadeOut(100);
						}
				}); 
				
				}
				
			}
			//企业
			function enterprisefun(){
				
				var enterprise = $("#enterprise").serialize();
				var sbh = document.getElementById("sbh").value;
				var ename = document.getElementById("ename").value;
				var emphone = document.getElementById("emphone").value;
				var email = document.getElementById("email").value;
				getBytesLength(ename);
				if(ename == null || ename==""){
					document.getElementById("message").innerHTML="纳税人抬头名称不可以为空";
					$dialog.fadeIn(100);
				}else if(getBytesLength(ename)>100){
					document.getElementById("message").innerHTML="纳税人抬头名称过长,请重新输入";
					$dialog.fadeIn(100);
				}else
				if(emphone == null || emphone==""){
					document.getElementById("message").innerHTML="手机号码不能为空";
					$dialog.fadeIn(100);
				}else
				if(!(/^[1][3,4,5,6,7,8][0-9]{9}$/.test(emphone))){
					document.getElementById("message").innerHTML=emphone+"不是有效的手机号码";
					$dialog.fadeIn(100);
				}else
				if(email == null || email==""){
					document.getElementById("message").innerHTML="邮箱不能为空";
					$dialog.fadeIn(100);
				}else
				if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ .test(email))){
					document.getElementById("message").innerHTML=email+"不是有效的邮箱";
					$dialog.fadeIn(100); 
				}else
				if(sbh.length<15 || sbh.length>20 ){
					document.getElementById("message").innerHTML="纳税人识别号长度只能是15至20位,当前长度"+sbh.length;
					$dialog.fadeIn(100);
				}else if(/^0+$/.test(sbh)){
					document.getElementById("message").innerHTML="纳税人识别号不能全为0";
					$dialog.fadeIn(100);
				}else if(!(/^[0-9a-zA-Z]+$/.test(sbh))){
					document.getElementById("message").innerHTML="纳税人识别号只能是数字和字母组成！";
					$dialog.fadeIn(100);
				}else{
				$loadingToast.fadeIn(100);
					$.ajax({
						url:"${ctx}/weixin/enterprise",
						type:"POST",
						//async: false,//取消异步
						dataType:"json",
						data:enterprise,
						success: function(data){
						
							if(data != null && data != ""){
								if(data.success){
									//alert(data);
									$loadingToast.fadeOut(100);
									window.self.location = data.message;
								}else{
									alert(data.message);
								}
							}else{
								window.self.location = "weixin/error";
							}
							$loadingToast.fadeOut(100);
						},
						error: function(err){
							if(err != null && err != ""){
								window.self.location = "weixin/error";
							}
							$loadingToast.fadeOut(100);
						}
				});
				
				}
				
				
			}
			
			function getBytesLength(s){
				 	var totalLength = 0;     
				    var charCode;  
				    for (var i = 0; i < s.length; i++) {  
				        charCode = s.charCodeAt(i);  
				        if (charCode < 0x007f)  {     
				            totalLength++;     
				        } else if ((0x0080 <= charCode) && (charCode <= 0x07ff))  {     
				            totalLength += 2;     
				        } else if ((0x0800 <= charCode) && (charCode <= 0xffff))  {     
				            totalLength += 3;   
				        } else{  
				            totalLength += 4;   
				        }          
				    }  
				    return totalLength;
			}
			function back(){
				$dialog.fadeOut(100);
				}
			//成功调用的方法
			function toast(){
				var $toast = $('#toast');
				$toast.fadeIn(100);
				setTimeout(function () {
					$toast.fadeOut(100);
				}, 2000);
			}
			function load(){
			console.log("i am load")
				var $load = $('#loadingToast');
				//$load.show();
				$load.fadeIn(100);
				
			}
			function loadsuccess(){
				var $load = $('#loadingToast');
				$load.fadeOut(100);
			}
		 /*  function ct(){
		    return document.compatMode == "BackCompat"? document.body.clientHeight:document.documentElement.clientHeight;
		  }
		  var f=document.getElementById("footer");
		  (window.onresize=function(){
		    f.style.position=document.body.scrollHeight>ct()?'':'absolute';
		  })(); */
		</script>
		
	</body>

</html>