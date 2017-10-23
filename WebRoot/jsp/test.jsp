<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"></c:set>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>开发票</title>
		<style>
		 input::-webkit-input-placeholder{
		    color: red;opacity:0.5;
		}
		</style>
		<!-- 引入 WeUI -->
		<link rel="stylesheet" href="${ctx}/dist/style/weui.min.css" />
		<link rel="stylesheet" href="${ctx}/dist/example/example.css" />
	</head>

	<body>
		<div class="container" id="container" >
			 <div class="weui-form-preview" id="head" >
	            <div class="weui-flex">
		            <div class="weui-flex__item"><div class="placeholder" style="text-align:center; "><img src="${ctx}/img/logo.png" style="vertical-align:middle;"/></div></div>
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
			<div class="weui-tab" id="end" style="height: 60%;">
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
							
							<div class="weui-cells">
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">发票抬头</label>
							        </div>
							       <input id="p_name" name="p_name" class="weui-input" type="text" placeholder="个人名称（必填） "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${name }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">手机</label>
							        </div>
							        <input id="p_mphone" name="p_mphone"  class="weui-input" type="number" onKeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))" placeholder="请输入您的手机（必填）" />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="mSelect">
							            <c:forEach items="${phone }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">邮箱</label>
							        </div>
							       <input id="group" name="group" class="weui-input" type="text" placeholder="请输入您的邮箱 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="select">
							            <c:forEach items="${mail }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							</div>
							<input  name="openid" value="${openid}"  type="hidden" />
							<input  name="amount" value="${amount}"  type="hidden" />
							<input name="orderid" value="${orderid}"  type="hidden" />
							<input name="date" value="${date}"  type="hidden" />
							<input name="payway" value="${payway}"  type="hidden" />
							</form>
						</div>
						<!-- 使用 -->
						<a href="javascript:void(0)" onclick="personalfun()" class="weui-btn weui-btn_primary">开发票</a>
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
							       <input id="p_name" name="p_name" class="weui-input" type="text" placeholder="企业名称（必填） "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
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
							       <input id="e_mphone" name="e_mphone" class="weui-input" type="text" placeholder="请输入您的手机(必填) "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${ephone }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">邮箱</label>
							        </div>
							       <input id="e_email" name="e_email" class="weui-input" type="text" placeholder="请输入您的邮箱 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
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
							       <input id="e_number" name="e_number" class="weui-input" type="text" placeholder="情输入您的18位的纳税人识别号(必填) "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${nsr }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">开户行</label>
							        </div>
							       <input id="e_bank" name="e_bank" class="weui-input" type="text" placeholder="请输入您的开户行 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${bank }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">银行账号</label>
							        </div>
							       <input id="e_account" name="e_account" class="weui-input" type="text" placeholder="请输入您的银行账号 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${account }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">地址</label>
							        </div>
							       <input id="e_address" name="e_address" class="weui-input" type="text" placeholder="请输入您的地址 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${address }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
							    <div class="weui-cell weui-cell_select weui-cell_select-after weui-cell_warn">
							        <div class="weui-cell__hd">
							            <label for="" class="weui-label">电话</label>
							        </div>
							       	<input id="e_phone" name="e_phone" class="weui-input" type="text" placeholder="请输入您的电话 "  />
							        <div class="weui-cell__bd">
							            <select class="weui-select" name="select2" id="nameSelect">
							            <c:forEach items="${dh }" var="p">
							            	<option value="${p }"> ${p}</option>
							            </c:forEach>
							            </select>
							        </div>
							    </div>
						</div>
						<!-- <div class="weui-cell weui-cell_warn">
							<div class="weui-cell__hd"><label for="" class="weui-label">发票抬头</label></div>
							<div class="weui-cell__bd">
								<input id="e_name" name="p_name" class="weui-input" type="text"  placeholder="企业名称（必填）" />
							</div>
							<div class="weui-cell__ft">
								<i class="weui-icon-warn"></i>
							</div>
						</div>
						<div class="weui-cell weui-cell_warn">
							<div class="weui-cell__hd"><label for="" class="weui-label">手机</label></div>
							<div class="weui-cell__bd">
								<input id="e_mphone" name="e_mphone"  class="weui-input" type="number" pattern="[0-9]*"  placeholder="请输入您的手机（必填）" />
							</div>
							<div class="weui-cell__ft">
								<i class="weui-icon-warn"></i>
							</div>
						</div>
							<div class="weui-cell">
								<div class="weui-cell__hd"><label class="weui-label">邮箱</label></div>
								<div class="weui-cell__bd">
									<input id="e_email" name="p_email"  class="weui-input" type="text" placeholder="请输入您的邮箱" />
								</div>
							</div>
						<div class="weui-cell weui-cell_warn">
							<div class="weui-cell__hd"><label for="" class="weui-label">识别号</label></div>
							<div class="weui-cell__bd">
								<input id="e_number" name="e_number"  class="weui-input" type="text" placeholder="请输入您的纳税人识别号（必填）" />
							</div>
							<div class="weui-cell__ft">
								<i class="weui-icon-warn"></i>
							</div>
						</div>
							<div class="weui-cell">
								<div class="weui-cell__hd"><label class="weui-label">开户行</label></div>
								<div class="weui-cell__bd">
									<input id="e_account" name="e_account"  class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入您的开户行" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd"><label class="weui-label">银行账号</label></div>
								<div class="weui-cell__bd">
									<input id="e_bank" name="e_bank"  class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入您的银行账号" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd"><label class="weui-label">地址</label></div>
								<div class="weui-cell__bd">
									<input id="e_address" name="e_address"  class="weui-input" type="text" placeholder="请输入您的地址" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd"><label class="weui-label">电话</label></div>
								<div class="weui-cell__bd">
									<input id="e_phone" name="e_phone"  class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入您的电话" />
								</div>
							</div> -->
							
							<input  name="openid" value="${openid}"  type="hidden" />
							<input  name="amount" value="${amount}"  type="hidden" />
							<input  name="orderid" value="${orderid}"  type="hidden" />
							<input  name="date" value="${date}"  type="hidden" />
							<input  name="payway" value="${payway}"  type="hidden" />
							
							</form>
						</div>
						<!-- 使用 -->
						<a href="javascript:void(0)" onclick="enterprisefun()" class="weui-btn weui-btn_primary">开发票</a>
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
					
	
		
		</div>
		<script src="${ctx}/dist/example/zepto.min.js"></script> 
		<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
		<script src="${ctx}/dist/example/example.js"></script>
		<script type="text/javascript">
			$(function() {
			
				$(document).on("change",'select',function(){
				    var $this = $(this);
				    var val =  $this.val();
				    console.log("yes");
				 	$this.parent().siblings('input').val(val);
				 	$this.val("");
				 });
				/* $("#select").change(function(){
					console.log($(this).val());
					$("#group").val($(this).val());
				})
				$("#nameSelect").change(function(){
					console.log($(this).val());
					$("#p_name").val($(this).val());
				})
				$("#mSelect").change(function(){
					console.log($(this).val());
					$("#p_mphone").val($(this).val());
				}) */
			
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
			
			//个人
			function personalfun(){
				$loadingToast.fadeIn(100);
				console.log("get in personal")
				var personal = $("#personal").serialize();
				var p_name = document.getElementById("p_name").value;  
				var p_mphone = document.getElementById("p_mphone").value;
				var p_email = document.getElementById("p_email").value;
				 $.ajax({
						url:"${ctx}/weixin/personal",
						type:"POST",
						//async: false,//取消异步
						dataType:"json",
						data:personal,
						success: function(data){
							if(data != null && data != ""){
								
							}
							console.log("success");
							$loadingToast.fadeOut(100);
							//toast();
						},
						error: function(err){
							console.log("error");
							if(err != null && err != ""){
								
							}
							$loadingToast.fadeOut(100);
						}
				}); 
			}
			//企业
			function enterprisefun(){
				$loadingToast.fadeIn(100);
				var enterprise = $("#enterprise").serialize()
				var e_name = document.getElementById("e_name").value;  
				var e_mphone = document.getElementById("e_mphone").value;
				var e_email = document.getElementById("e_email").value;
				var e_number = document.getElementById("e_number").value;  
				var e_account = document.getElementById("e_account").value;
				var e_bank = document.getElementById("e_bank").value;
				var e_address = document.getElementById("e_address").value;  
				var e_phone = document.getElementById("e_phone").value;
				$.ajax({
						url:"${ctx}/weixin/enterprise",
						type:"POST",
						async: false,//取消异步
						dataType:"json",
						data:enterprise,
						success: function(data){
						
							if(data != null && data != ""){
								
							}
							$loadingToast.fadeOut(100);
						},
						error: function(err){
							if(err != null && err != ""){
								
							}
							$loadingToast.fadeOut(100);
						}
				});
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
		</script>
		
	</body>

</html>