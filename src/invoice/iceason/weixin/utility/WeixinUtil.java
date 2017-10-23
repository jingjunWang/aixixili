package invoice.iceason.weixin.utility;

import invoice.iceason.utility.GsonJson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	private static AccessToken accessToken = null;
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	
	// 第三方用户唯一凭证
	public final static String appId = "wxa2ec4046c12f1c84";
	// 第三方用户唯一凭证密钥
	public final static String appSecret = "83f42ffcb62315820265a343c6344d4a";
	
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken() {
		
		if(accessToken == null){
			String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				} catch (JSONException e) {
					accessToken = null;
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}
		}
		
		return accessToken;
	}
	public final static String access_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	public static Ticket getTicket(String access_token) {
		Ticket ticket = null;
			String requestUrl = access_ticket_url.replace("ACCESS_TOKEN", access_token);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					ticket = new Ticket();
					/*"errcode":0,
					"errmsg":"ok",
					"ticket":"",
					"expires_in":7200*/
					ticket.setErrcode(Integer.valueOf(jsonObject.getString("errcode")));
					ticket.setErrmsg(jsonObject.getString("errmsg"));
					ticket.setTicket(jsonObject.getString("ticket"));
					ticket.setExpires_in(Integer.valueOf(jsonObject.getString("expires_in")));
				} catch (JSONException e) {
					ticket = null;
					// 获取token失败
					log.error("获取ticket失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
				}
			}
		
		return ticket;
	}
	// 菜单创建（POST） 限100（次/天）

	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//4. 创建发票卡券模板
	public static String menu_createcard_url = "https://api.weixin.qq.com/card/invoice/platform/createcard?access_token=ACCESS_TOKEN";
	
	public static String createcard(String access_token,Invoice_info invoice_info) {
		String str = "";
			String requestUrl = menu_createcard_url.replace("ACCESS_TOKEN", access_token);
			String json = GsonJson.getGson().toJson(invoice_info);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", json);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					 str = jsonObject.toString();
					
//					 errcode
//					 string
//					 是
//					 错误码
//					 errmsg
//					 string
//					 是
//					 错误信息
//					 card_id
//					 string
					/*ticket.setErrcode(Integer.valueOf(jsonObject.getString("errcode")));
					ticket.setErrmsg(jsonObject.getString("errmsg"));
					ticket.setTicket(jsonObject.getString("ticket"));
					ticket.setExpires_in(Integer.valueOf(jsonObject.getString("expires_in")));*/
				} catch (JSONException e) {
					// 获取token失败
					log.error("获取ticket失败 errcode:{} errmsg:{}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
				}
			}
		
		return str;
	}
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	public static String batchget(Batchget batchget1, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = batchget.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(batchget1).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			
			if (jsonObject.toString().contains("user_info_list")) {
				
			}else if (jsonObject.toString().contains("errcode")){
				result = jsonObject.getInt("errcode");
				log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return jsonObject.toString();
	}
	public static String qrcode_qrcode_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	public static String qrcode_qrcode(Batchget batchget1, String accessToken) {

		// 拼装创建菜单的url
		String url = qrcode_qrcode_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(batchget1).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}");

		if (null != jsonObject) {
			
			if (jsonObject.toString().contains("ticket")) {
				
			}else if (jsonObject.toString().contains("errcode")){
				log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return jsonObject.toString();
	}
	public static String batchget = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	//获取用户的信息URI
	public static String menu_create_o = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	/**
	 * 获取用户信息
	 * 
	 * @param openid 用户的OPENID
	 * @param accessToken 有效的access_token
	 * @return null 表示失败 其他表示成功
	 */
	public static String createMenu(String openid,String accessToken) {
		String result = "";

		// 拼装创建菜单的url
		String url = menu_create_o.replace("ACCESS_TOKEN", accessToken);
		if (openid!=null) {
			 url=url.replace("OPENID", openid);
		}
		

		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", null);

		if (null != jsonObject) {
			//{"errcode":40013,"errmsg":"invalid appid"}
			if (0 < jsonObject.getInt("errcode")) {
				log.error("获取失败用户 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				return null;
				
			}
		}

		return jsonObject.toString();
	}
	//	 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid
	public static String scope_1="snsapi_base";
	//用户授权snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
	public static String scope_2="snsapi_userinfo";
	
	public static String menu_create_authorization ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=CODE&scope=SCOPE&state=STATE#wechat_redirect";
	
	/**
	 * 获取用户信息
	 * 
	 * @param openid 用户的OPENID
	 * @param redirect_uri 返回地址
	 * 	@param	response_type 返回类型 用户授权 成功就会返回定义的类型  失败就不会返回 (CODE) 
	 *  @param scope ,scope_1,scope_1
	 *  @param state 都会返回的参数
	 * @return null 表示失败 其他表示成功
	 */
	public static String createMenu(String appid,String redirect_uri,String code,String scope,String state) {

		// 拼装创建菜单的url
		String url = menu_create_authorization.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("CODE", code).replace("SCOPE", scope).replace("STATE", state);
		



		return url;
	}
	/**
	 * 获取用户信息
	 * 
	 * @param openid 用户的OPENID
	 * @param redirect_uri 返回地址
	 * 	@param	response_type 返回类型 用户授权 成功就会返回定义的类型  失败就不会返回 (CODE) 
	 *  @param scope ,scope_1,scope_1
	 *  @param state 都会返回的参数
	 * @return null 表示失败 其他表示成功
	 */
	public static String createMenuUrl(String appid,String redirect_uri,String code,String scope,String state) {

		// 拼装创建菜单的url
		String url = menu_create_authorization.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("CODE", code).replace("SCOPE", scope).replace("STATE", state);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);

		if (null != jsonObject) {
			//{"errcode":40013,"errmsg":"invalid appid"}
			if (0 < jsonObject.getInt("errcode")) {
				log.error("获取用户信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				return null;
				
			}
		}



		return jsonObject.toString();
	}
	
	//拉取用户信息(需scope为 snsapi_userinfo)
	public static String menu_create_WebAuthorization="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 *   获取用户信息
	 * @author TSPOS_005	 2015-6-18  lrf
	 * @param access_token
	 * @param openid
	 * @param lang
	 * @return
	 */
	public static String createMenu_Web(String access_token,String openid,String lang) {

		// 拼装创建菜单的url
		String url = menu_create_WebAuthorization.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid).replace("zh_CN", lang);
		

		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);

		if (null != jsonObject) {
			//{"errcode":40013,"errmsg":"invalid appid"}
			
		}

		return jsonObject.toString();

	}
	//获取用户 通过code换取网页授权access_token
	public static String menu_create_WebAccessTokenURL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/**
	 *   获取用户信息
	 * @author TSPOS_005	 2015-6-18  lrf
	 * @param access_token
	 * @param openid
	 * @param lang
	 * @return
	 */
	public static String createMenu_WebAccessTokenURL(String code) {

		// 拼装创建菜单的url
		String url = menu_create_WebAccessTokenURL.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		
	
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);

		if (null != jsonObject) {
			return jsonObject.toString();
		}

		return jsonObject.toString();

	}
	
	/**
	 * 
	* @Title: pushUserTemplate
	* @Description: TODO(推送用户模板)
	* @param @param content
	* @param @param toUser    openId   accessToken 有效的 accessToken
	* @return void    返回类型
	* @throws
	 */
	public static void pushUserTemplate(String toUser,String accessToken) {
		String json = "{\"touser\":\"" + toUser
				+ "\",\"template_id\":\"xtJmOvzFUs4RT_Dx0wT6PivxI1Mfnvg-YEp8DQntQR0\","
				+ "\"url\":\"http://www.baidu.com\",\"topcolor\":\"#FF0000\",\"data\":"
				+ "{\"first\":{\"value\":\"测试first\",\"color\":\"#173177\"},"
				+ "\"storeName\":{\"value\":\"测试storeName\",\"color\":\"#173177\"},"
				+ "\"orderId\":{\"value\":\"测试orderId\",\"color\":\"#173177\"},"
				+ "\"orderType\":{\"value\":\"测试orderType\",\"color\":\"#173177\"},"
				+ "\"remark\":{\"value\":\"测试remark\",\"color\":\"#173177\"}}}";
		

		// 获取access_token "first": {"value":"测试","color":"#173177"},


		// 获取请求路径

		String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
				+ accessToken;

		System.out.println("json:" + json);

		try {

			JSONObject	jsonObject=WeixinUtil.httpRequest(action,"POST", json);
			System.out.println("推送==="+jsonObject);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					log.info("推送成功！");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}