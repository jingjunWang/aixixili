package invoice.iceason.fapiao.entityjs;

import java.util.List;
/*发票信息*/
public class BillingParamters {
	
	//private String FPQQLSH;
	/*销售方名称*/
	private String XSF_MC;
	/*销售方地址、电话*/
	private String XSF_DZDH;
	/*销售方银行账号*/
	private String XSF_YHZH;
	private String GMF_MC;
	private String GMF_DZDH;
	private String GMF_YHZH;
	private String GMF_NSRSBH;
	private String GMF_DZYX;
	private String GMF_SJH;
	/*开票人*/
	private String KPR;
	/*收款人*/
	private String SKR;
	/*复核人*/
	private String FHR;
	/*价税合计*/
	private String JSHJ;
	/*合计金额*/
	private String HJJE;
	/*合计税额*/
	private String HJSE;
	/*备注*/
	private String BZ;
	/*销售方纳税人识别号*/
	private String XSF_NSRSBH;
	/*开票类型*/
	private String KPLX;
	/*征税方式*/
	private String ZSFS;
	/*发票请求流水号*/
	private String FPQQLSH;
	
	private String payway;
	
	
	public String getGMF_DZYX() {
		return GMF_DZYX;
	}
	public void setGMF_DZYX(String gMF_DZYX) {
		GMF_DZYX = gMF_DZYX;
	}
	public String getGMF_SJH() {
		return GMF_SJH;
	}
	public void setGMF_SJH(String gMF_SJH) {
		GMF_SJH = gMF_SJH;
	}
	public String getGMF_NSRSBH() {
		return GMF_NSRSBH;
	}
	public void setGMF_NSRSBH(String gMF_NSRSBH) {
		GMF_NSRSBH = gMF_NSRSBH;
	}
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	public BillingParamters(){
		super();
	}
	private List<ProductsInfo> pInfo;
	
	public String getXSF_MC() {
		return XSF_MC;
	}
	public void setXSF_MC(String xSF_MC) {
		XSF_MC = xSF_MC;
	}
	public String getXSF_DZDH() {
		return XSF_DZDH;
	}
	public void setXSF_DZDH(String xSF_DZDH) {
		XSF_DZDH = xSF_DZDH;
	}
	public String getXSF_YHZH() {
		return XSF_YHZH;
	}
	public void setXSF_YHZH(String xSF_YHZH) {
		XSF_YHZH = xSF_YHZH;
	}
	public String getGMF_MC() {
		return GMF_MC;
	}
	public void setGMF_MC(String gMF_MC) {
		GMF_MC = gMF_MC;
	}
	public String getGMF_DZDH() {
		return GMF_DZDH;
	}
	public void setGMF_DZDH(String gMF_DZDH) {
		GMF_DZDH = gMF_DZDH;
	}
	public String getGMF_YHZH() {
		return GMF_YHZH;
	}
	public void setGMF_YHZH(String gMF_YHZH) {
		GMF_YHZH = gMF_YHZH;
	}
	public String getKPR() {
		return KPR;
	}
	public void setKPR(String kPR) {
		KPR = kPR;
	}
	public String getSKR() {
		return SKR;
	}
	public void setSKR(String sKR) {
		SKR = sKR;
	}
	public String getFHR() {
		return FHR;
	}
	public void setFHR(String fHR) {
		FHR = fHR;
	}
	public String getJSHJ() {
		return JSHJ;
	}
	public void setJSHJ(String jSHJ) {
		JSHJ = jSHJ;
	}
	public String getHJJE() {
		return HJJE;
	}
	public void setHJJE(String hJJE) {
		HJJE = hJJE;
	}
	public String getHJSE() {
		return HJSE;
	}
	public void setHJSE(String hJSE) {
		HJSE = hJSE;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public String getXSF_NSRSBH() {
		return XSF_NSRSBH;
	}
	public void setXSF_NSRSBH(String xSF_NSRSBH) {
		XSF_NSRSBH = xSF_NSRSBH;
	}
	public String getKPLX() {
		return KPLX;
	}
	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}
	public String getZSFS() {
		return ZSFS;
	}
	public void setZSFS(String zSFS) {
		ZSFS = zSFS;
	}
	public String getFPQQLSH() {
		return FPQQLSH;
	}
	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}
	public List<ProductsInfo> getpInfo() {
		return pInfo;
	}
	public void setpInfo(List<ProductsInfo> pInfo) {
		this.pInfo = pInfo;
	}

}
