package invoice.iceason.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PaymentWay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PaymentWay", schema = "dbo", catalog = "invoice_iceason")
public class PaymentWay implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tenderId;
	private String tenderName;
	private Date updatetime;
	private Date createtime;

	// Constructors

	/** default constructor */
	public PaymentWay() {
	}

	/** full constructor */
	public PaymentWay(String tenderId, String tenderName, Date updatetime, Date createtime) {
		this.tenderId = tenderId;
		this.tenderName = tenderName;
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

	@Column(name = "TenderID")
	public String getTenderId() {
		return this.tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	@Column(name = "TenderName")
	public String getTenderName() {
		return this.tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
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