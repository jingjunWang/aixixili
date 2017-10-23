package invoice.iceason.weixin.weixin;
import invoice.iceason.debug.DebugLog;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class WeiUserInfoService {
	
	private static Logger log = LoggerFactory.getLogger(WeiUserInfoService.class);
	private JSONArray jsonArray;
	/**
	 * 
	 * 微信公共账号发送给账号
	 * 
	 * @param content
	 *            文本内容
	 * 
	 * @param toUser
	 *            微信用户
	 * 
	 * @return
	 */

	public void sendTextMessageToUser(String content, String toUser) {

		String json = "{\"touser\": \"" + toUser
				+ "\",\"msgtype\": \"text\", \"text\": {\"content\": \""
				+ content + "\"}}";

		// 获取access_token

		AccessToken at=WeixinUtil.getAccessToken();
		String accessToken = at.getToken();

		// 获取请求路径

		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ accessToken;

		DebugLog.logger.info("json:" + json);

		try {

			JSONObject	jsonObject=WeixinUtil.httpRequest(action,"POST", json);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					DebugLog.logger.info("菜单创建成功！");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 微信公共账号发送给账号(本方法限制使用的消息类型是语音或者图片)
	 * 
	 * @param mediaId
	 *            图片或者语音内容
	 * 
	 * @param toUser
	 *            微信用户
	 * 
	 * @param messageType
	 *            消息类型
	 * 
	 * @return
	 */

	public void sendPicOrVoiceMessageToUser(String mediaId, String toUser,
			String msgType) {

		String json = null;

		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {

			json = "{\"touser\": \"" + toUser
					+ "\",\"msgtype\": \"image\", \"image\": {\"media_id\": \""
					+ mediaId + "\"}}";

		} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {

			json = "{\"touser\": \"" + toUser
					+ "\",\"msgtype\": \"voice\", \"voice\": {\"media_id\": \""
					+ mediaId + "\"}}";

		}

		// 获取access_token



		AccessToken at=WeixinUtil.getAccessToken();
		String accessToken = at.getToken();

		// 获取请求路径

		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ accessToken;

		try {
			
			JSONObject	jsonObject=WeixinUtil.httpRequest(action,"POST", json);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					log.info("菜单创建成功！");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * 发送图文给所有的用户
	 * 
	 * @param openId
	 *            用户的id
	 */

	public String sendNewsToUser(String openId) {


		ArrayList<Object> articles = new ArrayList<Object>();

		Article a = new Article();
		articles.add(a);
		String str = JSONutilss.toJSONArray(articles).toString();

		String json = "{\"touser\":\"" + openId
				+ "\",\"msgtype\":\"news\",\"news\":" +

				"{\"articles\":" + str + "}" + "}";

		json = json.replace("picUrl", "picurl");
		System.out.println(json);

		// 获取access_token

		
		AccessToken at=WeixinUtil.getAccessToken();
		String accessToken = at.getToken();

		// 获取请求路径

		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ accessToken;

		try {
			JSONObject	jsonObject=WeixinUtil.httpRequest(action,"POST", json);
			return jsonObject.toString();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "";
	}

	/**
	 * 
	 * 连接请求微信后台接口
	 * 
	 * @param action
	 *            接口url
	 * 
	 * @param json
	 *            请求接口传送的json字符串
	 */

	/*public void connectWeiXinInterface(String action, String json) {

		URL url;

		try {

			url = new URL(action);

			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");

			http.setRequestProperty("Content-Type",

			"application/x-www-form-urlencoded");

			http.setDoOutput(true);

			http.setDoInput(true);

			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			OutputStream os = http.getOutputStream();

			os.write(json.getBytes("UTF-8"));// 传入参数

			InputStream is = http.getInputStream();

			int size = is.available();

			byte[] jsonBytes = new byte[size];

			is.read(jsonBytes);

			String result = new String(jsonBytes, "UTF-8");

			System.out.println("请求返回结果:" + result);

			os.flush();

			os.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}*/
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
				+ "\"url\":\"http://www.baidu.com\",\"topcolor\":\"#FF69B4\",\"data\":"
				+ "{\"first\":{\"value\":\"测试first\",\"color\":\"#FF69B4\"},"
				+ "\"storeName\":{\"value\":\"测试storeName\",\"color\":\"#173177\"},"
				+ "\"orderId\":{\"value\":\"测试orderId\",\"color\":\"#173177\"},"
				+ "\"orderType\":{\"value\":\"测试orderType\",\"color\":\"#173177\"},"
				+ "\"remark\":{\"value\":\"测试remark\",\"color\":\"#173177\"}}}";
		// 获取access_token "first": {"value":"测试","color":"#173177"},
		// 获取请求路径
		String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
				+ accessToken;

		DebugLog.logger.info("json:" + json);

		try {

			JSONObject	jsonObject=WeixinUtil.httpRequest(action,"POST", json);
			DebugLog.logger.info("推送==="+jsonObject);
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					DebugLog.logger.info("推送成功！");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
