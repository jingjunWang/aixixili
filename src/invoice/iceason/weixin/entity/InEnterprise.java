package invoice.iceason.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InEnterprise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "InEnterprise", schema = "dbo", catalog = "invoice_iceason")
public class InEnterprise implements java.io.Serializable {

	// Fields

	private Integer id;
	private String openid;
	private String orderkey;
	//价税合计  总金额+税额
	private String amount;
	private String orderid;
	private String date;
	private String PName;
	private String PMphone;
	private String PEmail;
	private String ENumber;
	private String EAccount;
	private String EBank;
	private String EAddress;
	private String EPhone;
	private Date updatetime;
	private Date createtime;
	private String payWay;
	private String nsrsbh;
	//总金额
	private String totalFee;
	//税额
	private String SE;
	//税率
	private String SL;
	
	private String sp_URL;
	
	private String pdf_URL;
	
	

	// Constructors

	public String getPdf_URL() {
		return pdf_URL;
	}

	public void setPdf_URL(String pdf_URL) {
		this.pdf_URL = pdf_URL;
	}

	public String getSp_URL() {
		return sp_URL;
	}

	public void setSp_URL(String sp_URL) {
		this.sp_URL = sp_URL;
	}

	public String getSL() {
		return SL;
	}

	public void setSL(String sL) {
		SL = sL;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getSE() {
		return SE;
	}

	public void setSE(String sE) {
		SE = sE;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	/** default constructor */
	public InEnterprise() {
	}

	/** minimal constructor */
	public InEnterprise(String openid) {
		this.openid = openid;
	}

	/** full constructor */
	public InEnterprise(String openid, String orderkey, String amount, String orderid, String date, String PName, String PMphone, String PEmail, String ENumber, String EAccount, String EBank, String EAddress, String EPhone, Date updatetime, Date createtime) {
		this.openid = openid;
		this.orderkey = orderkey;
		this.amount = amount;
		this.orderid = orderid;
		this.date = date;
		this.PName = PName;
		this.PMphone = PMphone;
		this.PEmail = PEmail;
		this.ENumber = ENumber;
		this.EAccount = EAccount;
		this.EBank = EBank;
		this.EAddress = EAddress;
		this.EPhone = EPhone;
		this.updatetime = updatetime;
		this.createtime = createtime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "openid", nullable = false)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "orderkey")
	public String getOrderkey() {
		return this.orderkey;
	}

	public void setOrderkey(String orderkey) {
		this.orderkey = orderkey;
	}

	@Column(name = "amount")
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "orderid")
	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Column(name = "date")
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(name = "p_name")
	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	@Column(name = "p_mphone")
	public String getPMphone() {
		return this.PMphone;
	}

	public void setPMphone(String PMphone) {
		this.PMphone = PMphone;
	}

	@Column(name = "p_email")
	public String getPEmail() {
		return this.PEmail;
	}

	public void setPEmail(String PEmail) {
		this.PEmail = PEmail;
	}

	@Column(name = "e_number")
	public String getENumber() {
		return this.ENumber;
	}

	public void setENumber(String ENumber) {
		this.ENumber = ENumber;
	}

	@Column(name = "e_account")
	public String getEAccount() {
		return this.EAccount;
	}

	public void setEAccount(String EAccount) {
		this.EAccount = EAccount;
	}

	@Column(name = "e_bank")
	public String getEBank() {
		return this.EBank;
	}

	public void setEBank(String EBank) {
		this.EBank = EBank;
	}

	@Column(name = "e_address")
	public String getEAddress() {
		return this.EAddress;
	}

	public void setEAddress(String EAddress) {
		this.EAddress = EAddress;
	}

	@Column(name = "e_phone")
	public String getEPhone() {
		return this.EPhone;
	}

	public void setEPhone(String EPhone) {
		this.EPhone = EPhone;
	}

	@Column(name = "updatetime", length = 23)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "createtime", length = 23)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}