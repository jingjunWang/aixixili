package invoice.iceason.weixin.utility;

public class WeChatConfig {
	/** WeChatPay config */
	// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
	public static String key = "TW41BKHsXPdR9EUQW1owzMT281qTsOxR";// 623701318f09cbcaeb490ec7919a8b19
	public static String appID = "wx5d187db4e888e77f";// wx5d187db4e888e77f"wx5d187db4e888e77f";//
														// 微信分配的公众号ID（开通公众号之后可以获取到）
	public static String mchID = "1399779402";// 1399779402
												// 1399779402微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static String subMchID = "";// 受理模式下给子商户分配的子商户号
	public static String certLocalPath = "apiclient_cert.p12";// HTTPS证书的本地路径
	public static String certPassword = "1399779402";// 1399779402HTTPS
														// 证书密码，默认密码等于商户号MCHID
	public static boolean useThreadToDoReport = true;// 是否使用异步线程的方式来上报API测速，默认为异步模式
	// 以下是几个API的路径：
	// 1）被扫支付API 统一下单
	public static String WECHAT_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 2）被扫支付查询API
	public static String WECHAT_PAY_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 3）退款API
	public static String WECHAT_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 4）退款查询API
	public static String WECHAT_REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 5）撤销API
	public static String WECHAT_REVERSE_URL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
	// 6）下载对账单API
	public static String WECHAT_DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	// 7) 统计上报API
	public static String WECHAT_REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";

	// #全部门店的 人气产品
	public static String weixin_crm_postPopularItemHttp = "http://localhost/BSVIP/wx/postPopularItem";
	// #发送用户已经支付好的订单
	public static String weixin_crm_getOrderData = "http://localhost/BSVIP/wx/getOrderData";
	// #根据门店号获取 人品产品
	public static String weixin_crm_postPopularItemByStore = "http://localhost/BSVIP/wx/postPopularItemByStore";
	// #常点产品
	public static String weixin_crm_postFavoriteItem = "http://localhost/BSVIP/wx/postFavoriteItem";
	// #验证订单
	public static String weixin_web_verifyOrderHttp = "http://localhost:8020/Waimai_POSServer/h5webAction/verifyOrder";
	// #提交订单
	public static String weixin_web_submitOrdersHttp = "http://localhost:8020/Waimai_POSServer/h5webAction/submitOrders";

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		WeChatConfig.key = key;
	}

	public static String getAppID() {
		return appID;
	}

	public static void setAppID(String appID) {
		WeChatConfig.appID = appID;
	}

	public static String getMchID() {
		return mchID;
	}

	public static void setMchID(String mchID) {
		WeChatConfig.mchID = mchID;
	}

	public static String getSubMchID() {
		return subMchID;
	}

	public static void setSubMchID(String subMchID) {
		WeChatConfig.subMchID = subMchID;
	}

	public static String getCertLocalPath() {
		return certLocalPath;
	}

	public static void setCertLocalPath(String certLocalPath) {
		WeChatConfig.certLocalPath = certLocalPath;
	}

	public static String getCertPassword() {
		return certPassword;
	}

	public static void setCertPassword(String certPassword) {
		WeChatConfig.certPassword = certPassword;
	}

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		WeChatConfig.useThreadToDoReport = useThreadToDoReport;
	}

	public static String getWeixin_crm_postPopularItemHttp() {
		return weixin_crm_postPopularItemHttp;
	}

	public static void setWeixin_crm_postPopularItemHttp(
			String weixin_crm_postPopularItemHttp) {
		WeChatConfig.weixin_crm_postPopularItemHttp = weixin_crm_postPopularItemHttp;
	}

	public static String getWeixin_crm_getOrderData() {
		return weixin_crm_getOrderData;
	}

	public static void setWeixin_crm_getOrderData(String weixin_crm_getOrderData) {
		WeChatConfig.weixin_crm_getOrderData = weixin_crm_getOrderData;
	}

	public static String getWeixin_crm_postPopularItemByStore() {
		return weixin_crm_postPopularItemByStore;
	}

	public static void setWeixin_crm_postPopularItemByStore(
			String weixin_crm_postPopularItemByStore) {
		WeChatConfig.weixin_crm_postPopularItemByStore = weixin_crm_postPopularItemByStore;
	}

	public static String getWeixin_crm_postFavoriteItem() {
		return weixin_crm_postFavoriteItem;
	}

	public static void setWeixin_crm_postFavoriteItem(
			String weixin_crm_postFavoriteItem) {
		WeChatConfig.weixin_crm_postFavoriteItem = weixin_crm_postFavoriteItem;
	}

	public static String getWeixin_web_verifyOrderHttp() {
		return weixin_web_verifyOrderHttp;
	}

	public static void setWeixin_web_verifyOrderHttp(
			String weixin_web_verifyOrderHttp) {
		WeChatConfig.weixin_web_verifyOrderHttp = weixin_web_verifyOrderHttp;
	}

	public static String getWeixin_web_submitOrdersHttp() {
		return weixin_web_submitOrdersHttp;
	}

	public static void setWeixin_web_submitOrdersHttp(
			String weixin_web_submitOrdersHttp) {
		WeChatConfig.weixin_web_submitOrdersHttp = weixin_web_submitOrdersHttp;
	}

}
