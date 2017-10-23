package invoice.iceason.weixin.utility;

import invoice.iceason.debug.DebugLog;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 发送https请求
 * @author iSPOS-WHY
 */
public class WeChatHttpsRequestUtil {
    //连接超时时间，默认10秒
    private int socketTimeout = 10000;
    //传输超时时间，默认30秒
    private int connectTimeout = 30000;
    //请求器的配置
    private RequestConfig requestConfig;
    
	/**
	 * 初始化HttpClient对象   传入参数控制是否需要证书
	 * @param needCert true表示需要证书，false表示不需要证书
	 * @return 返回HttpClient对象
	 */
	protected HttpClient getHttpClient(boolean needCert) throws Exception {
		HttpClient httpClient = null;    	
		if (needCert) {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(WeChatConfig.certLocalPath));// 加载本地的证书进行https加密传输
			try {
				keyStore.load(instream, WeChatConfig.certPassword.toCharArray());// 设置证书密码
			} catch (CertificateException e) {
				DebugLog.logger.error("initHttpClient:", e);
			} catch (NoSuchAlgorithmException e) {
				DebugLog.logger.error("initHttpClient:", e);
			} finally {
				instream.close();
			}

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WeChatConfig.certPassword.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} else {
			httpClient = HttpClients.createDefault();
		}
		//以下是安卓中创建httpClient的方法
//		if (needCert) {
//			KeyStore keyStore = KeyStore.getInstance("PKCS12");
//			FileInputStream instream = new FileInputStream(new File(WeChatConfig.certLocalPath));// 加载本地的证书进行https加密传输
//			try {
//				keyStore.load(instream, WeChatConfig.certPassword.toCharArray());// 设置证书密码
//			} catch (CertificateException e) {
//				DebugLog.logger.error("initHttpClient:", e);
//			} catch (NoSuchAlgorithmException e) {
//				DebugLog.logger.error("initHttpClient:", e);
//			} finally {
//				instream.close();
//			}
//	
//			// Trust own CA and all self-signed certs
//			SSLContext sslcontext = SSLContexts.custom()
//					.loadKeyMaterial(keyStore, WeChatConfig.certPassword.toCharArray()).build();
//			// Allow TLSv1 protocol only
//			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
//					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//	
//			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//		} else {
//			httpClient = new DefaultHttpClient(); 
//		}
        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
		return httpClient;
	}
	/**
	 * 发送请求
	 * @param httpPost
	 * @param httpClient
	 * @param postDataXML
	 * @return
	 */
	protected String sendPost(HttpPost httpPost, HttpClient httpClient, String postDataXML) throws Exception{
		 //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        
        String requestResult = "";
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			requestResult = EntityUtils.toString(entity, "UTF-8");
		} catch (ConnectionPoolTimeoutException e) {
			DebugLog.logger.error("http get throw ConnectionPoolTimeoutException(wait time out):" , e);
        } catch (ConnectTimeoutException e) {
			DebugLog.logger.error("http get throw ConnectTimeoutException:" , e);
        } catch (SocketTimeoutException e) {
            DebugLog.logger.error("http get throw SocketTimeoutException:" , e);
        } finally {
			httpPost.abort();
		}
		return requestResult;
	}
	/**
	 * 根据list生成sign
	 * @param list
	 * @return
	 */
	public String generateSignByList(ArrayList<String> list){
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String sign = sb.toString();
		sign += "key=" + WeChatConfig.key;
		sign = MD5_2(sign, 32).toUpperCase();
		
		return sign;
	}
	public String generateSignByListjs(ArrayList<String> list){
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String sign = sb.toString();
		sign = MD5_2(sign, 32).toUpperCase();
		
		return sign;
	}
	/**
	 * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
	 */
	protected boolean checkIsSignValidFromResponseString(String responseString) throws Exception{
		Element rootElement = null;
		SAXReader saxReader = new SAXReader();
		ByteArrayInputStream byteArray = new ByteArrayInputStream(responseString.getBytes("UTF-8"));
		Document document = saxReader.read(byteArray);
		if(document != null){
			rootElement = document.getRootElement();
		}
		String responseSign = "";
		ArrayList<String> list = new ArrayList<String>();
		if(rootElement != null){
			List<Element> elements = rootElement.elements();
			for (Element element : elements) {
				if (element.getText() != null && !element.getText().equals("")) {
					if (element.getName().equals("sign")) {
						responseSign = element.getText();
					} else {
						list.add(element.getName() + "=" + element.getText() + "&");
					}
				}
			}
		}
		if (responseSign == null || responseSign.equals("")) {
			DebugLog.logger.error("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
			return false;
		}
		DebugLog.logger.info("服务器回包里面的签名是:" + responseSign);
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		String sign = generateSignByList(list);
		
		if (responseSign.equals(sign)) {
			DebugLog.logger.info("恭喜，API返回的数据签名验证通过!!!");
			return true;
		}
		// 签名验不过，表示这个API返回的数据有可能已经被篡改了
		DebugLog.logger.error("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
		return false;
	}
	
	/**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
	public String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    /**
     * MD5加密
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
	protected static String MD5_2(String value, int len) {
		String res = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = value.getBytes("UTF-8");
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			res = new String(str);
			res = res.substring(res.length() - len, res.length());
			return res;
		} catch (Exception e) {
			DebugLog.logger.error("Exception!!", e);
			return null;
		}
	}

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
	protected void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
	protected void setConnectTimeout(int connectTimeout) {
    	this.connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig(){
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig 设置HttpsRequest的请求器配置
     */
    protected void setRequestConfig(RequestConfig requestConfig) {
    	this.requestConfig = requestConfig;
    }
}
