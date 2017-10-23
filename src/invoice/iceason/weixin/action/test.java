package invoice.iceason.weixin.action;

import java.io.File;
import java.math.BigDecimal;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import invoice.iceason.debug.DebugLog;
import invoice.iceason.fapiao.entityjs.Interface;
import invoice.iceason.utility.GsonJson;
import invoice.iceason.weixin.utility.AccessToken;
import invoice.iceason.weixin.utility.Base_info;
import invoice.iceason.weixin.utility.Invoice_info;
import invoice.iceason.weixin.utility.WeixinUtil;

public class test {
	
	

	public static void main(String[] args) {
		 boolean flag=true;
		 String str = "e";
		 
         if(str.hashCode()>0&&str.hashCode()<9){
        	 
        	 System.out.println(str.hashCode());
        	 System.out.println(flag); 
         } 
        System.out.println(101>0&&101<9);
		BigDecimal a = new BigDecimal(38.99);
		BigDecimal b = new BigDecimal(3.24);
		BigDecimal c =a.divide(b,2,BigDecimal.ROUND_HALF_UP);
		//String da=String.valueOf(new BigDecimal(50.00).multiply(new BigDecimal(0.06)).setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println(c.toString());
		/*String path = "D:/tomcat7/webapps/Iceason_Invoice/Config/BillingParameters.config";
		File file = new File(path);
		Document billingParamsInfoXml = null;
		System.out.println(file.canRead());
		if (file.canRead()) {
			SAXReader saxReader = new SAXReader();
			try {
				billingParamsInfoXml = saxReader.read(file);
			} catch (DocumentException e) {
				DebugLog.logger.error("DocumentException in loadConfigurationFiles when read billingInfo.config - ", e);
			}
		}*/
		
//		String str =  "{\"globalInfo\": {\"appId\": \"6d29f136114544bcc73edcce960c430231183cc192c433e2b9ebcad56e8ceb08\",\"interfaceId\": \"\",\"interfaceCode\": \"DFXJ1001\",\"requestCode\": \"DZFPQZ\",\"requestTime\": \"Mon Sep 04 12:06:56 CST 2017\",\"responseCode\": \"Ds\",\"dataExchangeId\": \"DZFPQZDFXJ10012017-09-04247785502\"},\"returnStateInfo\": {\"returnCode\": \"\",\"returnMessage\": \"\"},\"Data\": {\"dataDescription\": {\"zipCode\": \"0\"},\"content\": \"eyJSRVFVRVNUX0NPTU1PTl9GUEtKIjogeyJGUFFRTFNIIjogIlRFU1QyMDE3MDkwNDEyMDY1NjAxIiwiWlNGUyI6ICIwIiwiS1BMWCI6ICIwIiwiWFNGX05TUlNCSCI6ICIxMTAxMDk1MDAzMjE2NTUiLCJYU0ZfTUMiOiAieFNGX0RaREgiLCJYU0ZfRFpESCI6ICJ4U0ZfRFpESCIsIlhTRl9ZSFpIIjogInhTRl9EWkRIIiwiR01GX05TUlNCSCI6ICIiLCJHTUZfTUMiOiAiZ21mX21jIiwiR01GX0RaREgiOiAi6LSt5Lmw5pa55Zyw5Z2A55S16K+dIiwiR01GX1lIWkgiOiAiZ21mX3loemgiLCJHTUZfU0pIIjogIiIsIkdNRl9EWllYIjogIiIsIkZQVF9aSCI6ICIiLCJXWF9PUEVOSUQiOiAiIiwiS1BSIjogImtwciIsIlNLUiI6ICJza3IiLCJGSFIiOiAi5aSN5qC45Lq6IiwiWUZQX0RNIjogIiIsIllGUF9ITSI6ICIiLCJKU0hKIjogImpzaGoiLCJISkpFIjogImhqamUiLCJISlNFIjogImhqc2UiLCJLQ0UiOiAiIiwiQloiOiAi5rWL6K+V5LiA5LiLIiwiSFlMWCI6ICIiLCJCWTEiOiAiIiwiQlkyIjogIiIsIkJZMyI6ICIiLCJCWTQiOiAiIiwiQlk1IjogIiIsIkJZNiI6ICIiLCJCWTciOiAiIiwiQlk4IjogIiIsIkJZOSI6ICIiLCJCWTEwIjogIiIsIldYX09SREVSX0lEIjogIiIsIldYX0FQUF9JRCI6ICIiLCJaRkJfVUlEIjogIiIsIkNPTU1PTl9GUEtKX1hNWFhTIjogeyJDT01NT05fRlBLSl9YTVhYIjogW119fX19\",\"contentKey\":\"UMRpBsJtI0eLhlVo0G0Hck1DRqBoIykG8CIAY9rC57NU4pYCt+SUydvtom101qqc\"}}";
//		String sb = "{\"globalInfo\":{\"interfaceCode\":\"DFXJ1001\",\"responseCode\":\"1\",\"appId\":\"0\",\"requestTime\":\"2017-09-04 13:16:24:204\",\"requestCode\":\"DZFPQZ\",\"interfaceId\":\"\",\"dataExchangeId\":\"DZFPQZDFXJ10012017-09-04126998628\"},\"returnStateInfo\":{\"returnCode\":\"9999\",\"returnMessage\":\"10002:销方信息不合法\"},\"Data\":{\"dataDescription\":{\"zipCode\":\"0\"},\"content\":\"\"}}";
//		Interface pojo = GsonJson.getGson().fromJson(sb, Interface.class);
//		System.out.println(pojo);
		// TODO Auto-generated method stub
//		AccessToken accessToken = WeixinUtil.getAccessToken();
//		Invoice_info invoice_info = new Invoice_info();
//		invoice_info.setPayee(\"测试-收款方\");
//		invoice_info.setType(\"广东省增值税普通发票\");
//		invoice_info.setDetail(\"测试-detail\");
//		Base_info base_info = new Base_info();
//		base_info.setLogo_url(\"\");
//		base_info.setTitle(\"收款方\");
//		invoice_info.setBase_info(base_info);
//		String createcard = WeixinUtil.createcard(accessToken.getToken(), invoice_info);
//		System.out.println(createcard);
	}

}
