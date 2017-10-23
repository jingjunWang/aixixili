package invoice.iceason.utility;

public class ConstantStaticVariablesSet {
	/** log4j在项目中的所在目录 ，即在目录下的哪个目录 */
	public static final String Log4jPathAndName = "/WEB-INF/classes/log4j.properties";
	/** 日志保存在项目中的哪个目录 ，即在Baidu_POSServer目录下的哪个目录 */
	public static final String Log4jFilePathAndName = "/logs/";
	
	public static final String testIssue ="/Config/shqgqyglyxgs.pfx";
	public static final String clientTrustStore ="/Config/fapiao2017client.truststore";
	/** 配置文件地址*/
	public static final String ConfigFilePathAndBillingInfoConfig = "/Config/BillingInfo.config";
	
	/** pdf 发票保存地址*/
	public static final String BillingPdfPath = "/Pdf/";
	
	/** 开票信息配置地址 */
	public static final String BillingParametersPath = "/Config/BillingParameters.config";
	
	public static final String UrlPath = "/Config/Url.config";
	
	public static final String payWay1 = "100";
	public static final String payWay2 = "112";
	
	public static final String taxRate = "0";
	public static final String taxRate2 = "0.06";
	
	//BillingParameters.config
}
