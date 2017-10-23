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

/*发票配置*/
public class BillingInfo {
	private String requestUrl = "https://dev.fapiao.com:18943/fpt-dsqz/";//初始化地址
	private String appid = "6d29f136114544bcc73edcce960c430231183cc192c433e2b9ebcad56e8ceb08";//appid
	private String contentPassword = "5EE6C2C11DD421F2";//AES加密密钥
	private String fpqqlsh = "TEST2017022415272501";// 需要查询发票的流水号
	private String nsrsbh = "110109500321655"; //纳税人识别号
	private String fpdm = "050003521333"; 	//发票代码
	private String fphm = "85004524";	//发票号码
	
	private static BillingInfo billingInfo=null;
	
	public static BillingInfo getBillingInfo(){
		if(billingInfo==null){
			synchronized(BillingInfo.class){
				if(billingInfo==null){
					billingInfo = new BillingInfo();
				}
			}
		}
		return billingInfo;
	}
	public BillingInfo(){
		getBillingInfoXml();
	}
	public void getBillingInfoXml(){
		String serverConfigFile = ApplicationListener.rootPath + ConstantStaticVariablesSet.ConfigFilePathAndBillingInfoConfig;
		File fText = new File(serverConfigFile);
		Document billingInfoXml = null;
		if (fText.canRead()) {
			SAXReader saxReader = new SAXReader();
			try {
				billingInfoXml = saxReader.read(fText);
			} catch (DocumentException e) {
				DebugLog.logger.error("DocumentException in loadConfigurationFiles when read billingInfo.config - ", e);
			}
		}
		if(billingInfoXml!=null){
//			String url_ = getValueByXpath(serverXml, "/CanDao/URL");
//			if (url_ != null && url_.trim().length() > 0) {
//				this.url = url_;
//			}
			String requestUrl_ = getValueByXpath(billingInfoXml, "/BillingInfo/REQUESTURL");
			if(requestUrl_ !=null && requestUrl_.trim().length() > 0){
				this.requestUrl = requestUrl_;
			}
			String appid_ = getValueByXpath(billingInfoXml, "/BillingInfo/APPID");
			if(appid_ !=null && appid_.trim().length() > 0){
				this.appid = appid_;
			}
			
			String contentPassword_ = getValueByXpath(billingInfoXml, "/BillingInfo/CONTENTPASSWORD");
			if(contentPassword_ !=null && contentPassword_.trim().length() > 0){
				this.contentPassword = contentPassword_;
			}
			String fpqqlsh_ = getValueByXpath(billingInfoXml, "/BillingInfo/FPQQLSH");
			if(fpqqlsh_ !=null && fpqqlsh_.trim().length() > 0){
				this.fpqqlsh = fpqqlsh_;
			}
			String nsrsbh_ = getValueByXpath(billingInfoXml, "/BillingInfo/NSRSBH");
			if(nsrsbh_ !=null && nsrsbh_.trim().length() > 0){
				this.nsrsbh = nsrsbh_;
			}
			String fphm_ = getValueByXpath(billingInfoXml, "/BillingInfo/FPHM");
			if(fphm_ !=null && fphm_.trim().length() > 0){
				this.fphm = fphm_;
			}
			String fpdm_ = getValueByXpath(billingInfoXml, "/BillingInfo/FPDM");
			if(fpdm_ !=null && fpdm_.trim().length() > 0){
				this.fpdm = fpdm_;
			}
		}
	}
	
	/**
	 * 根据XPath获取结点的String值. 如果节点不存在，返回空字符 -- 应该修改为返回 null
	 * 
	 * @param xml
	 * @param xpathstring
	 * @return
	 */
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
	public String getRequestUrl() {
		return requestUrl;
	}
	
	public String getAppid() {
		return appid;
	}
	
	public String getContentPassword() {
		return contentPassword;
	}
	
	public String getFpqqlsh() {
		return fpqqlsh;
	}
	
	public String getNsrsbh() {
		return nsrsbh;
	}
	
	public String getFpdm() {
		return fpdm;
	}
	
	public String getFphm() {
		return fphm;
	}
	

}
