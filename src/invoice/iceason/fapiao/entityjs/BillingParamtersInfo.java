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

public class BillingParamtersInfo {
	/*开票人*/
	private String KPR="new";
	/*收款人*/
	private String SKR="new";
	/*复核人*/
	private String FHR="new";
	
	/*销售方纳税人识别号*/
	private String XSF_NSRSBH="110109500321655";
	
	/*销售方名称*/
	private String XSF_MC="百旺电子测试2";
	
	private String XSF_DHDZ="new";
	
	private String XSF_YHZH="";
	
	public String getXSF_YHZH() {
		return XSF_YHZH;
	}

	public String getXSF_DHDZ() {
		return XSF_DHDZ;
	}

	private static BillingParamtersInfo billingParamtersInfo = null;
	
	public static BillingParamtersInfo getbillBillingParamtersInfo(){
		if(billingParamtersInfo==null){
			synchronized (BillingParamtersInfo.class) {
				if(billingParamtersInfo ==null){
					billingParamtersInfo = new BillingParamtersInfo();
				}
			}
		}
		return billingParamtersInfo;
	}
	
	public BillingParamtersInfo(){
		getXmlInfo();
	}
	public void getXmlInfo(){
		String parametersConfigFile = ApplicationListener.rootPath + ConstantStaticVariablesSet.BillingParametersPath;
		File file = new File(parametersConfigFile);
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
//			String url_ = getValueByXpath(serverXml, "/CanDao/URL");
//			if (url_ != null && url_.trim().length() > 0) {
//				this.url = url_;
//			}
			String fhr_ = getValueByXpath(billingParamsInfoXml, "/BillingParameters/FHR");
			if(fhr_ !=null && fhr_.trim().length() > 0){
				this.FHR = fhr_;
			}
			String xsfmc = getValueByXpath(billingParamsInfoXml, "/BillingParameters/XSFMC");
			if(xsfmc !=null && xsfmc.trim().length() > 0){
				this.XSF_MC = xsfmc;
			}
			
			String kpr = getValueByXpath(billingParamsInfoXml, "/BillingParameters/KPR");
			if(kpr !=null && kpr.trim().length() > 0){
				this.KPR = kpr;
			}
			String skr = getValueByXpath(billingParamsInfoXml, "/BillingParameters/SKR");
			if(skr !=null && skr.trim().length() > 0){
				this.SKR = skr;
			}
			String nsrsbh_ = getValueByXpath(billingParamsInfoXml, "/BillingParameters/NSRSBH");
			if(nsrsbh_ !=null && nsrsbh_.trim().length() > 0){
				this.XSF_NSRSBH = nsrsbh_;
			}
			String xsfdhdz = getValueByXpath(billingParamsInfoXml, "/BillingParameters/XSFDHDZ");
			if(xsfdhdz !=null && xsfdhdz.trim().length() > 0){
				this.XSF_DHDZ = xsfdhdz;
			}
			String xsfyhzh = getValueByXpath(billingParamsInfoXml, "/BillingParameters/XSFYHZH");
			if(xsfyhzh !=null && xsfyhzh.trim().length() > 0){
				this.XSF_YHZH = xsfyhzh;
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

	public String getKPR() {
		return KPR;
	}

	public String getSKR() {
		return SKR;
	}

	public String getFHR() {
		return FHR;
	}

	public String getXSF_NSRSBH() {
		return XSF_NSRSBH;
	}

	public String getXSF_MC() {
		return XSF_MC;
	}
}
