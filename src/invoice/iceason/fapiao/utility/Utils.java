package invoice.iceason.fapiao.utility;

import invoice.iceason.fapiao.entityjs.BillingInfo;
import invoice.iceason.fapiao.entityjs.BillingParamters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static final String dfxj1001 = "DFXJ1001"; // 订单上传接口
	public static final String dfxj1003 = "DFXJ1003"; // 发票库存信息
	public static final String dfxj1004 = "DFXJ1004"; // 发票查询
	public static final String dfxj1005 = "DFXJ1005"; // 发票下载地址查询
	
	public static final String interfaceLau_json = "json";//语言-json
	public static final String interfaceLau_xml = "xml";//语言-xml
	
	public static final String webservice_axis = "webservice_axis";//请求方式使用axis的webservice
	public static final String webservice_xfire = "webservice_xfire";//请求方式使用xfire的webservice		
	public static final String post_https = "post_https";//使用post请求方式
	
	public static final String requestMethod_xml = "doService";//xml的webservice请求方法名
	public static final String requestMethod_json = "doJsonService";//json的webservice请求方法名
	
	public static final String requestUrlMethod_xml = "/services/DZFPService";//xml的webservice请求方法名
	public static final String requestUrlMethod_json = "/services/DZFPJsonService";//json的webservice请求方法名
	
	/**
	 * 获取指定格式时间(yyyy-MM-dd)
	 * @return
	 */
	public static String formatToDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format((new Date()));
	}
	
	/**
	 * 获取指定格式时间(yyyyMMddHHmmss)
	 * @return
	 */
	public static String formatToTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format((new Date()));
	}
	
	/************************************************************************
	 * 获取9位随机数
	 */
	public static String randNineData(){
		return randData()+randFiveData();
	}
	
	/************************************************************************
	 * 获取5位随机数
	 */
	public static String randFiveData(){
		return String.valueOf((int)(Math.random()*90000+10000));
	}
	
	/************************************************************************
	 * 获取4位随机数
	 */
	public static String randData(){
		return String.valueOf((int)(Math.random()*9000+1000));
	}
	
	public static String getFaPiao(BillingParamters billingParamters)  throws Exception{
		Date startDate = new Date();//初始化开始时间
		// 初始化参数
		String requestData = null;//初始化请求报文
		String rsData = null;//初始化结果报文
		String requestMethod = null;//初始化请求方法
		String requestUrlMethod = null;//初始化连接方法
		
		BillingInfo billingInfo=BillingInfo.getBillingInfo();
		String requestUrl = billingInfo.getRequestUrl();//"https://dev.fapiao.com:18943/fpt-dsqz/";//初始化地址
		String appid = billingInfo.getAppid();//"6d29f136114544bcc73edcce960c430231183cc192c433e2b9ebcad56e8ceb08";//appid
		String contentPassword = billingInfo.getContentPassword();//"5EE6C2C11DD421F2";//AES加密密钥
		String fpqqlsh = billingInfo.getFpqqlsh();//"TEST2017022415272501";// 需要查询发票的流水号
		String nsrsbh = billingInfo.getNsrsbh();//"110109500321655";
		String fpdm = billingInfo.getFpdm();//"050003521333";
		String fphm = billingInfo.getFphm();//"85004524";

		// 通过注释选择语言
//		String interfaceLau = Utils.interfaceLau_xml;
		 String interfaceLau = Utils.interfaceLau_json;
		

		// 通过注释选择接口
		 String interfaceCode = Utils.dfxj1001;//开具
//		 String interfaceCode = Utils.dfxj1004;//查询
//		 String interfaceCode = Utils.dfxj1003;//库存查询
//		String interfaceCode = Utils.dfxj1005;// 获取下载地址
		
		//通过注释选择调用方式
//		String requestInterface = Utils.webservice_axis;//请求方式使用axis的webservice
//		String requestInterface = Utils.webservice_xfire;//请求方式使用xfire的webservice		
		String requestInterface = Utils.post_https;//使用post请求方式
		
		// 组装请求报文
		if (Utils.interfaceLau_xml.equals(interfaceLau)) {
			requestData = XmlUtils.getSendToTaxXML(interfaceCode, fpqqlsh, nsrsbh, fpdm, fphm,appid,contentPassword);
			requestMethod = Utils.requestMethod_xml;//xml的请求方法
			requestUrlMethod = Utils.requestUrlMethod_xml;//xml的连接后缀
		} else if (Utils.interfaceLau_json.equals(interfaceLau)) {
			requestData = JsonUtils.getSendToTaxJson(billingParamters,interfaceCode, fpqqlsh, nsrsbh, fpdm, fphm,appid,contentPassword);
			requestMethod = Utils.requestMethod_json;//json的请求方法
			requestUrlMethod = Utils.requestUrlMethod_json;//json的连接后缀
		}else{
			System.out.println("请选择语言！");
		}
		
		System.out.println("组装报文完毕,请求使用的语言是:"+interfaceLau+",请求的方式是："+requestInterface+",请求报文为:"+requestData+",开始请求。");
		Date requestStartDate = new Date();//初始化请求开始时间
		// 调用接口
		if(Utils.webservice_axis.equals(requestInterface)){
			rsData = RequestUtils.webServiceAxis(requestData,requestMethod,requestUrl+requestUrlMethod);
		}else if(Utils.webservice_xfire.equals(requestInterface)){
			rsData = RequestUtils.webServiceXfile(requestData, requestMethod, requestUrl+requestUrlMethod+"?wsdl");
		}else if(Utils.post_https.equals(requestInterface)){
			rsData = RequestUtils.getHttpConnectResult(requestData,requestUrl+"invoice");
		}
		Date requestEndDate = new Date();//初始化请求结束时间
		System.out.println("请求完毕，耗时【"+(requestEndDate.getTime()-requestStartDate.getTime())+"ms】");
		Date endDate = new Date();//初始化结束时间
		System.out.println("请求接口结束，获得结果:" + rsData);
		System.out.println("总耗时【"+(endDate.getTime()-startDate.getTime())+"ms】");
		return rsData;
	}
}
