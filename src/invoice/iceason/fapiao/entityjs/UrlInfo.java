package invoice.iceason.fapiao.entityjs;

import invoice.iceason.debug.ApplicationListener;
import invoice.iceason.debug.DebugLog;
import invoice.iceason.utility.ConstantStaticVariablesSet;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.python.modules.synchronize;

public class UrlInfo {

	private String redirectedUrl="";
	private String appId="";
	
	public String getRedirectedUrl() {
		return redirectedUrl;
	}
	public void setRedirectedUrl(String redirectedUrl) {
		this.redirectedUrl = redirectedUrl;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	private static UrlInfo urlInfo = null;
	public static UrlInfo getUrlInfo(){
		if(urlInfo==null){
			synchronized(UrlInfo.class){
				if(urlInfo==null){
					urlInfo = new UrlInfo();
				}
			}
		}
		return urlInfo;
	}
	public UrlInfo(){
		getUrlInfoFromXml();
	}
	public void getUrlInfoFromXml(){

		String urlConfigFile = ApplicationListener.rootPath + ConstantStaticVariablesSet.UrlPath;
		File file = new File(urlConfigFile);
		Document billingParamsInfoXml = null;
		if (file.canRead()) {
			SAXReader saxReader = new SAXReader();
			try {
				billingParamsInfoXml = saxReader.read(file);
			} catch (DocumentException e) {
				DebugLog.logger.error("DocumentException in loadConfigurationFiles when read billingInfo.config - ", e);
			}
		}
		if(billingParamsInfoXml!=null){

			String fhr_ = getValueByXpath(billingParamsInfoXml, "/Url/APPID");
			if(fhr_ !=null && fhr_.trim().length() > 0){
				this.appId = fhr_;
			}
			String xsfmc = getValueByXpath(billingParamsInfoXml, "/Url/REDIRECTEDURL");
			if(xsfmc !=null && xsfmc.trim().length() > 0){
				this.redirectedUrl = xsfmc;
			}
		}
	
	}
	private String getValueByXpath(Document xml, String xpathstring) {
		if (xml == null) {
			return "";
		}
		List<Element> list = (List<Element>) xml.selectNodes(xpathstring);
		if (list.size() > 0) {
			return list.get(0).getText();
		}
		return "";
	}
}
