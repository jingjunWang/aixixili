package invoice.iceason.fapiao.entityjs;

public class ProductsInfo {

	/*发票行性质*/
	private String FPHXZ;
	/*商品编码*/
	private String SPBM;
	/*项目名称*/
	private String XMMC;
	/*规格型号*/
	private String GGXH;
	/*计量单位*/
	private String DW;
	/*项目数量*/
	private String XMSL;
	/*项目单价*/
	private String XMDJ;
	/*项目金额*/
	private String XMJE;
	/*税率*/
	private String SL;
	/*税额*/
	private String SE;
	/*零税率标志*/
	private String LSLBS;
	/*优惠政策标识*/
	private String YHZCBS;
	
	/*增值税特殊管理*/
	private String ZZSTSGL;
	
	private String payWay;
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getZZSTSGL() {
		return ZZSTSGL;
	}
	public void setZZSTSGL(String zZSTSGL) {
		ZZSTSGL = zZSTSGL;
	}
	public String getYHZCBS() {
		return YHZCBS;
	}
	public void setYHZCBS(String yHZCBS) {
		YHZCBS = yHZCBS;
	}
	public String getLSLBS() {
		return LSLBS;
	}
	public void setLSLBS(String lSLBS) {
		LSLBS = lSLBS;
	}
	public ProductsInfo(){
		super();
	}
	public String getFPHXZ() {
		return FPHXZ;
	}
	public void setFPHXZ(String fPHXZ) {
		FPHXZ = fPHXZ;
	}
	public String getSPBM() {
		return SPBM;
	}
	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}
	public String getXMMC() {
		return XMMC;
	}
	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}
	public String getGGXH() {
		return GGXH;
	}
	public void setGGXH(String gGXH) {
		GGXH = gGXH;
	}
	public String getDW() {
		return DW;
	}
	public void setDW(String dW) {
		DW = dW;
	}
	public String getXMSL() {
		return XMSL;
	}
	public void setXMSL(String xMSL) {
		XMSL = xMSL;
	}
	public String getXMDJ() {
		return XMDJ;
	}
	public void setXMDJ(String xMDJ) {
		XMDJ = xMDJ;
	}
	public String getXMJE() {
		return XMJE;
	}
	public void setXMJE(String xMJE) {
		XMJE = xMJE;
	}
	public String getSL() {
		return SL;
	}
	public void setSL(String sL) {
		SL = sL;
	}
	public String getSE() {
		return SE;
	}
	public void setSE(String sE) {
		SE = sE;
	}
}
