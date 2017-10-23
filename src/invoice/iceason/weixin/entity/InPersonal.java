package invoice.iceason.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InPersonal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "InPersonal", schema = "dbo", catalog = "invoice_iceason")
public class InPersonal implements java.io.Serializable {

	// Fields

	private Integer id;
	private String openid;
	private String orderkey;
	private String amount;
	private String orderid;
	private String date;
	private String PName;
	private String PMphone;
	private String PEmail;
	private Date updatetime;
	private Date createtime;
	private String sp_url;
	private String pdf_url;

	// Constructors

	public String getPdf_url() {
		return pdf_url;
	}

	public void setPdf_url(String pdf_url) {
		this.pdf_url = pdf_url;
	}

	public String getSp_url() {
		return sp_url;
	}

	public void setSp_url(String sp_url) {
		this.sp_url = sp_url;
	}

	/** default constructor */
	public InPersonal() {
	}

	/** minimal constructor */
	public InPersonal(String openid) {
		this.openid = openid;
	}

	/** full constructor */
	public InPersonal(String openid, String orderkey, String amount, String orderid, String date, String PName, String PMphone, String PEmail, Date updatetime, Date createtime) {
		this.openid = openid;
		this.orderkey = orderkey;
		this.amount = amount;
		this.orderid = orderid;
		this.date = date;
		this.PName = PName;
		this.PMphone = PMphone;
		this.PEmail = PEmail;
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