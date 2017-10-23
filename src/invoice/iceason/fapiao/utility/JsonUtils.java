package invoice.iceason.fapiao.utility;


import invoice.iceason.fapiao.entityjs.BillingInfo;
import invoice.iceason.fapiao.entityjs.BillingParamters;
import invoice.iceason.fapiao.entityjs.ProductsInfo;
import invoice.iceason.utility.ConstantStaticVariablesSet;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import sun.misc.BASE64Encoder;
import sun.net.www.content.text.plain;

import com.baiwang.utility.encrypt.MyAES;

public class JsonUtils {

	/**
	 * 拼通用报文(json)
	 * 
	 * @return
	 * @throws Exception 
	 */

	public static String getSendToTaxJson(BillingParamters billingParamters,String interfaceCode, String fpqqlsh,String nsrsbh,String fpdm,String fphm,String appid,String contentPassword)
			throws Exception {
		String content = "";
		StringBuffer sb = new StringBuffer("");
		
		sb.append("{");
		sb.append("\"interface\": {");
		sb.append("\"globalInfo\": {");
		sb.append("\"appId\": \"").append(appid).append("\","); 
		sb.append("\"interfaceId\": \"\","); 
		sb.append("\"interfaceCode\": \"").append(interfaceCode).append("\","); 
		sb.append("\"requestCode\": \"DZFPQZ\",");
		sb.append("\"requestTime\": \"").append(new Date()).append("\","); 
		sb.append("\"responseCode\": \"Ds\","); 
		sb.append("\"dataExchangeId\": \"").append("DZFPQZ").append(interfaceCode).append(Utils.formatToDay()).append( Utils.randNineData()).append( "\"");
		sb.append("},"); 
		sb.append("\"returnStateInfo\": {");
		sb.append("\"returnCode\": \"\","); 
		sb.append("\"returnMessage\": \"\"");
		sb.append("},"); 
		sb.append("\"Data\": {");
		sb.append("\"dataDescription\": {");
		sb.append("\"zipCode\": \"0\"");
		sb.append("},"); 
		sb.append("\"content\": \"");
		if (interfaceCode.equals(Utils.dfxj1001)) {
			content = getUploadContent(billingParamters);
		} 
		else if (interfaceCode.equals(Utils.dfxj1003)) {
			content = getRemainContent(nsrsbh);
		}
		else if (interfaceCode.equals(Utils.dfxj1004)) {
			content = getSearchContent(nsrsbh,fpqqlsh);
		}
		else if (interfaceCode.equals(Utils.dfxj1005)) {
			content = getDownloadAddrContent(nsrsbh, fpqqlsh, fpdm, fphm);
		}
		content  = content.replace("\r\n", "").replace("\n", "").replace("\r", "");//json的报文不允许有换行，base64会产生。因此此处做去换行处理。
		sb.append(content).append("\",");
		sb.append("\"contentKey\":\"");
		String contentMD5 = MyAES.MD5(content.getBytes("UTF-8"));
		String contentKey = MyAES.encryptBASE64(MyAES.encrypt(contentMD5.getBytes("UTF-8"), contentPassword)).replaceAll("\r\n", "").replaceAll("\n", "").replace("\r", "");
		sb.append(contentKey).append("\"");;
		sb.append("}");
		sb.append("}");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 根据加密上传发票内容报文（发票开具）
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getUploadContent(BillingParamters billingParamters) throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPKJ\": {");
		content.append("\"FPQQLSH\": \" "+ billingParamters.getFPQQLSH() +"\",");
		//content.append("\"FPQQLSH\": \"TEST2017022415272501"+ "\",");
		content.append("\"ZSFS\": \"0\",");
		content.append("\"KPLX\": \"0\",");
		
		content.append("\"XSF_NSRSBH\": \""+billingParamters.getXSF_NSRSBH()+"\",");
		//content.append("\"XSF_MC\": \"百旺电子测试2\",");
		if(billingParamters.getXSF_MC()!=null){
			content.append("\"XSF_MC\": \""+billingParamters.getXSF_MC() +"\",");//销售方名称
		}else{
			content.append("\"XSF_MC\": \"\",");
		}
		
		//content.append("\"XSF_MC\": \""+billingParamters.getXSF_MC()!=null?billingParamters.getXSF_MC():"" +"\",");//销售方名称
		
		if(billingParamters.getXSF_DZDH()!=null){
			content.append("\"XSF_DZDH\": \""+billingParamters.getXSF_DZDH()+"\",");//销售方地址电话
		}else{
			content.append("\"XSF_DZDH\": \"\",");//销售方地址电话
		}
		if(billingParamters.getXSF_YHZH()!=null){
			content.append("\"XSF_YHZH\": \""+billingParamters.getXSF_YHZH()+"\",");//销售方银行账号
		}else{
			content.append("\"XSF_YHZH\": \"\",");//销售方银行账号
		}
		
		if(billingParamters.getGMF_NSRSBH()!=null){
			content.append("\"GMF_NSRSBH\": \""+billingParamters.getGMF_NSRSBH()+"\",");//购买方纳税人识别号
		}else{
			content.append("\"GMF_NSRSBH\": \"\",");
		}
		
		if(billingParamters.getGMF_MC()!=null){
			content.append("\"GMF_MC\": \""+billingParamters.getGMF_MC()+"\",");//购买方名称
		}else{
			content.append("\"GMF_MC\": \"\",");//购买方名称
		}
		if(billingParamters.getGMF_DZDH()!=null){
			content.append("\"GMF_DZDH\": \""+billingParamters.getGMF_DZDH()+"\",");//购买方地址、电话
		}else{
			content.append("\"GMF_DZDH\": \"\",");//购买方地址、电话
		}
		if(billingParamters.getGMF_YHZH()!=null){
			content.append("\"GMF_YHZH\": \""+billingParamters.getGMF_YHZH()+"\",");//购买方银行账号
		}else{
			content.append("\"GMF_YHZH\": \"\",");//购买方银行账号
		}
		
		content.append("\"GMF_SJH\": \"\",");
		content.append("\"GMF_DZYX\": \"\",");
		content.append("\"FPT_ZH\": \"\",");
		content.append("\"WX_OPENID\": \"\",");
		if(billingParamters.getKPR()!=null){
			content.append("\"KPR\": \""+billingParamters.getKPR()+"\",");//开票人
		}else{
			content.append("\"KPR\": \"\",");//开票人
		}
		if(billingParamters.getSKR()!=null){
			content.append("\"SKR\": \""+billingParamters.getSKR()+"\",");//收款人
		}else{
			content.append("\"SKR\": \"\",");//收款人
		}
		if(billingParamters.getFHR()!=null){
			content.append("\"FHR\": \""+billingParamters.getFHR()+"\",");//复核人
		}else{
			content.append("\"FHR\": \"\",");//复核人
		}
		
		content.append("\"YFP_DM\": \"\",");
		content.append("\"YFP_HM\": \"\",");
		if(billingParamters.getJSHJ()!=null){
			content.append("\"JSHJ\": \""+billingParamters.getJSHJ()+"\",");//价税合计
		}else{
			content.append("\"JSHJ\": \"\",");//价税合计
		}
		if(billingParamters.getHJJE()!=null){
			content.append("\"HJJE\": \""+billingParamters.getHJJE()+"\",");//合计金额
		}else{
			content.append("\"HJJE\": \"\",");//合计金额
		}
		if(billingParamters.getHJSE()!=null){
			content.append("\"HJSE\": \""+billingParamters.getHJSE()+"\",");//合计税额
		}else{
			content.append("\"HJSE\": \"\",");//合计税额
		}
		
		content.append("\"KCE\": \"\",");
		if(billingParamters.getBZ()!=null){
			content.append("\"BZ\": \""+billingParamters.getBZ()+"\",");//开票备注
		}else{
			content.append("\"BZ\": \"\",");//开票备注
		}
	
		content.append("\"HYLX\": \"1\",");
		content.append("\"BY1\": \"\",");
		content.append("\"BY2\": \"\",");
		content.append("\"BY3\": \"\",");
		content.append("\"BY4\": \"\",");
		content.append("\"BY5\": \"\",");
		content.append("\"BY6\": \"\",");
		content.append("\"BY7\": \"\",");
		content.append("\"BY8\": \"\",");
		content.append("\"BY9\": \"\",");
		content.append("\"BY10\": \"\",");
		content.append("\"WX_ORDER_ID\": \"\",");
		content.append("\"WX_APP_ID\": \"\",");
		content.append("\"ZFB_UID\": \"\",");
		content.append("\"TSPZ\": \"00\",");
		content.append("\"COMMON_FPKJ_XMXXS\": {");
		content.append("\"COMMON_FPKJ_XMXX\": [");
		List<ProductsInfo> pList = billingParamters.getpInfo();
		if(pList != null && pList.size() > 0){
			for(int i = 0; i < pList.size(); i++){
				if(i == pList.size() - 1){
					content.append("{");
					if(pList.get(i).getFPHXZ()!=null){
						content.append("\"FPHXZ\": \""+pList.get(i).getFPHXZ()+"\",");
					}else{
						content.append("\"FPHXZ\": \"\",");
					}
					if(pList.get(i).getSPBM()!=null){
						content.append("\"SPBM\": \""+pList.get(i).getSPBM()+"\",");
					}else{
						content.append("\"SPBM\": \"\",");
					}
					
					
					content.append("\"ZXBM\": \"\",");
					if(pList.get(i).getSL()=="0"||pList.get(i).equals("0")){//pList.get(i).getSL()=="0"||pList.get(i).equals("0")
						content.append("\"YHZCBS\": \"1\",");
						content.append("\"LSLBS\": \"2\",");
						content.append("\"ZZSTSGL\": \"不征税\",");
					}else{
						content.append("\"YHZCBS\": \"\",");
						content.append("\"LSLBS\": \"\",");
						content.append("\"ZZSTSGL\": \"\",");
					}
					
					if(pList.get(i).getXMMC()!=null){
						content.append("\"XMMC\": \""+pList.get(i).getXMMC()+"\",");
					}else{
						content.append("\"XMMC\": \"\",");
					}
					if(pList.get(i).getGGXH()!=null){
						content.append("\"GGXH\": \""+pList.get(i).getGGXH()+"\",");
					}else{
						content.append("\"GGXH\": \"\",");
					}
					if(pList.get(i).getDW()!=null){
						content.append("\"DW\": \""+pList.get(i).getDW()+"\",");
					}else{
						content.append("\"DW\": \"\",");
					}
					if(pList.get(i).getXMSL()!=null){
						content.append("\"XMSL\": \""+pList.get(i).getXMSL()+"\",");
					}else{
						content.append("\"XMSL\": \"\",");
					}
					if(pList.get(i).getXMDJ()!=null){
						content.append("\"XMDJ\": \""+pList.get(i).getXMDJ()+"\",");
					}else{
						content.append("\"XMDJ\": \"\",");
					}
					if(pList.get(i).getXMJE()!=null){
						content.append("\"XMJE\": \""+pList.get(i).getXMJE()+"\",");
					}else{
						content.append("\"XMJE\": \"\",");
					}
					if(pList.get(i).getSL()!=null){
						content.append("\"SL\": \""+pList.get(i).getSL()+"\",");
					}else{
						content.append("\"SL\": \"\",");
					}
					if(pList.get(i).getSE()!=null){
						content.append("\"SE\": \""+pList.get(i).getSE()+"\",");
					}else{
						content.append("\"SE\": \"\",");
					}
					content.append("\"BY1\": \"\",");
					content.append("\"BY2\": \"\",");
					content.append("\"BY3\": \"\",");
					content.append("\"BY4\": \"\",");
					content.append("\"BY5\": \"\"}");
				}else{
					content.append("{");
					if(pList.get(i).getFPHXZ()!=null){
						content.append("\"FPHXZ\": \""+pList.get(i).getFPHXZ()+"\",");
					}else{
						content.append("\"FPHXZ\": \"\",");
					}
					if(pList.get(i).getSPBM()!=null){
						content.append("\"SPBM\": \""+pList.get(i).getSPBM()+"\",");
					}else{
						content.append("\"SPBM\": \"\",");
					}
					
					content.append("\"ZXBM\": \"\",");
					if(pList.get(i).getSL()=="0.00"||pList.get(i).equals("0.00")){//pList.get(i).getSL()=="0.00"||pList.get(i).equals("0.00")
						content.append("\"YHZCBS\": \"1\",");
						content.append("\"LSLBS\": \"2\",");
						content.append("\"ZZSTSGL\": \"不征税\",");
					}else{
						content.append("\"YHZCBS\": \"\",");
						content.append("\"LSLBS\": \"\",");
						content.append("\"ZZSTSGL\": \"\",");
					}
					if(pList.get(i).getXMMC()!=null){
						content.append("\"XMMC\": \""+pList.get(i).getXMMC()+"\",");
					}else{
						content.append("\"XMMC\": \"\",");
					}
					if(pList.get(i).getGGXH()!=null){
						content.append("\"GGXH\": \""+pList.get(i).getGGXH()+"\",");
					}else{
						content.append("\"GGXH\": \"\",");
					}
					if(pList.get(i).getDW()!=null){
						content.append("\"DW\": \""+pList.get(i).getDW()+"\",");
					}else{
						content.append("\"DW\": \"\",");
					}
					if(pList.get(i).getXMSL()!=null){
						content.append("\"XMSL\": \""+pList.get(i).getXMSL()+"\",");
					}else{
						content.append("\"XMSL\": \"\",");
					}
					if(pList.get(i).getXMDJ()!=null){
						content.append("\"XMDJ\": \""+pList.get(i).getXMDJ()+"\",");
					}else{
						content.append("\"XMDJ\": \"\",");
					}
					if(pList.get(i).getXMJE()!=null){
						content.append("\"XMJE\": \""+pList.get(i).getXMJE()+"\",");
					}else{
						content.append("\"XMJE\": \"\",");
					}
					if(pList.get(i).getSL()!=null){
						content.append("\"SL\": \""+pList.get(i).getSL()+"\",");
					}else{
						content.append("\"SL\": \"\",");
					}
					if(pList.get(i).getSE()!=null){
						content.append("\"SE\": \""+pList.get(i).getSE()+"\",");
					}else{
						content.append("\"SE\": \"\",");
					}
					content.append("\"BY1\": \"\",");
					content.append("\"BY2\": \"\",");
					content.append("\"BY3\": \"\",");
					content.append("\"BY4\": \"\",");
					content.append("\"BY5\": \"\"},");
				}
			}
		}

		content.append("]");
		content.append("}");
		content.append("}");
		content.append("}");
		content.append("}");
		System.out.println(content);
		
		return  new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}

	/**
	 * 获取加密查询报文内容(发票查询报文)
	 * 
	 * @param fpqqlsh
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getSearchContent(String nsrsbh ,String fpqqlsh)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPCX\":{");
		content.append("\"FPQQLSH\":\"").append(fpqqlsh).append("\",");
		content.append("\"XSF_NSRSBH\":\"").append(nsrsbh).append("\"}}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}

	/**
	 * 获取加密查询报文内容(发票结余报文)
	 * 
	 * @param nsrsbh
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getRemainContent(String nsrsbh)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPKCCX\":{");
		content.append("\"NSRSBH\":\"").append(nsrsbh).append("\"}}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}

	/**
	 * 获取加密查询报文内容(发票下载地址查询)
	 * @param nsrsbh
	 * @param fpqqlsh
	 * @param fpdm
	 * @param fphm
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getDownloadAddrContent(String nsrsbh,String fpqqlsh,String fpdm,String fphm)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPXZDZCX\":{");
		content.append("\"FPQQLSH\":\"").append(fpqqlsh).append("\",");
		content.append("\"NSRSBH\":\"").append(nsrsbh).append("\",");
		content.append("\"FP_DM\":\"").append(nsrsbh).append("\",");
		content.append("\"FP_HM\":\"").append(nsrsbh).append("\"}}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}
}
