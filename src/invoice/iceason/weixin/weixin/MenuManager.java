package invoice.iceason.weixin.weixin;

import invoice.iceason.debug.DebugLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	private static AccessToken at =null;
	
	public static void main(String[] args) {
	//static{ ~֧ضʧќ֧ضAPIϵͳ׵ܘʧќìȫݬӢPostٸAPIք˽ߝˇرڦ׶ۏרìխϳхϢΪúǩĻխϳ
		// 第三方用户唯一凭证
		String appId = WeixinUtil.appId;
		// 第三方用户唯一凭证密钥\
		
	/*	String appSecret = "";
		String token = "";*/
		// 调用接口获取access_token
		 at = WeixinUtil.getAccessToken();

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(appId),at.getToken());

			// 判断菜单创建结果
			if (0 == result){
				DebugLog.logger.info("菜单创建成功！");
			}else{
				DebugLog.logger.info("菜单创建失败，错误码：" + result);
			}
		}
		System.exit(0);
	}
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu(String appid) {
		CommonButton btn11 = new CommonButton();
		btn11.setName("扫码带提示");
		btn11.setType("scancode_waitmsg");
		btn11.setKey("11");
		
		
		
		CommonButton btn12 = new CommonButton();
		btn12.setName("公交查询");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("周边搜索");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("精品推荐");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("经典游戏");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("美女电台");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("人脸识别");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("聊天唠嗑");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q友圈");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("电影排行榜");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("33");

		CommonButtonUrl btn34 = new CommonButtonUrl();
		btn34.setName("开发票");
		btn34.setType("scancode_push");
		btn34.setKey("rselfmenu_0_1");
		String url34 = "http://wechat.talentsolution.net/Iceason_Invoice/weixin/qrCode";
		//String url34 = WeixinUtil.createMenu(appid, "http://www.baidu.com");
		btn34.setUrl(url34);
		
		
		CommonButtonUrl btn35 = new CommonButtonUrl();
		btn35.setName("我的发票");
		btn35.setType("view");
		String url35 = "http://wechat.talentsolution.net/Iceason_Invoice/weixin/goMyBilling";
		//String url35 = WeixinUtil.createMenu(appid, "http://www.baidu.com");
		btn35.setUrl(url35);
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[] { btn31, btn12, btn13, btn14 });
		
		ComplexButton mainBtn4 = new ComplexButton();
		mainBtn4.setName("发票");
		mainBtn4.setSub_button(new CommonButtonUrl[]{ btn34, btn35 });
		

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲驿站");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn13, btn14 });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是"更多体验"，而直接是"幽默笑话"，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		
		//点击进入本店  wechat.intellistreet.net
				CommonButtonUrl url = new CommonButtonUrl();
				url.setName("测试");
				url.setType("view");
				String urls=WeixinUtil.createMenu(appid,"http://wechat.51aoyi.com/weixin/index.do","pos",WeixinUtil.scope_1,"1001");
				 //String  urls="http://www.baidu.com";
				 
				url.setUrl(urls);

				
				
				CommonButtonUrl url2 = new CommonButtonUrl();
				url2.setName("点餐系统");
				url2.setType("view");
				//String  urls1=WeixinUtil.createMenu(token,"http://wechat.intellistreet.net/weixin/index.do","pos",WeixinUtil.scope_2,"1001");
				String  urls1=WeixinUtil.createMenu(appid,"http://wechat.talentsolution.net/CustomerOrder/weixin/index.do","pos",WeixinUtil.scope_2,"1001");
				//String urls1="http://wechat.intellistreet.net/weixin/index.do";
				url2.setUrl(urls1);
				
				
				//点击进入本店  wechat.intellistreet.net
				CommonButtonUrl url3= new CommonButtonUrl();
				url3.setName("LBS定位");
				url3.setType("view");
				 String  urls3="http://wechat.talentsolution.net/BSVIP/wx/order";
				url3.setUrl(urls3);
		Menu menu = new Menu();
		//menu.setButton(new Button[] { mainBtn1, mainBtn2, url });
		menu.setButton(new Button[] {  mainBtn4,url2,url3});
		//menu.setButton(new Button[] { btn11});
		//menu.setButton(new Button[] {mainBtn2});
		
		return menu;
	}
}