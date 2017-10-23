package invoice.iceason.isposbo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OrderPayment", schema = "dbo", catalog = "iSPOS_DB")
public class OrderPayment implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderKey;
	private String tenderId;
	private String paymentKey;
	private Double payValue;
	private Double overCharged;
	private Double fxvalue;
	private Date timeCreatedInDb;
	private String usedProduct;
	private String memo;

	// Constructors

	
	public OrderPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Column(name = "OrderKey")
	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "TenderID", length = 5)
	public String getTenderId() {
		return this.tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	@Column(name = "PaymentKey", length = 24)
	public String getPaymentKey() {
		return this.paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}

	@Column(name = "PayValue", precision = 10)
	public Double getPayValue() {
		return this.payValue;
	}

	public void setPayValue(Double payValue) {
		this.payValue = payValue;
	}

	@Column(name = "OverCharged", precision = 10)
	public Double getOverCharged() {
		return this.overCharged;
	}

	public void setOverCharged(Double overCharged) {
		this.overCharged = overCharged;
	}

	@Column(name = "FXValue", precision = 10)
	public Double getFxvalue() {
		return this.fxvalue;
	}

	public void setFxvalue(Double fxvalue) {
		this.fxvalue = fxvalue;
	}

	@Column(name = "TimeCreatedInDB", length = 23)
	public Date getTimeCreatedInDb() {
		return this.timeCreatedInDb;
	}

	public void setTimeCreatedInDb(Date timeCreatedInDb) {
		this.timeCreatedInDb = timeCreatedInDb;
	}

	@Column(name = "UsedProduct", length = 512)
	public String getUsedProduct() {
		return this.usedProduct;
	}

	public void setUsedProduct(String usedProduct) {
		this.usedProduct = usedProduct;
	}

	@Column(name = "Memo", length = 64)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}