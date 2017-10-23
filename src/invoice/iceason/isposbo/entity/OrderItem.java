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
import javax.persistence.UniqueConstraint;

/**
 * OrderItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OrderItem", schema = "dbo", catalog = "iSPOS_DB", uniqueConstraints = @UniqueConstraint(columnNames = "OrderItemKey"))
public class OrderItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderKey;
	private String uuid;
	private String plu;
	private String orderItemKey;
	private Integer netQty;
	private Integer promoQty;
	private Integer discountQty;
	private Double discountAmt;
	private Double totalAmt;
	private Double unitPrice;
	private Integer itemType;
	private Integer reduceAfterTotalQty;
	private Double reduceAfterTotalAmt;
	private Date timeCreatedInDb;
	private Double finalAmt;

	// Constructors

	/** default constructor */
	public OrderItem() {
	}


	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name = "OrderKey")
	public String getOrderKey() {
		return this.orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	@Column(name = "UUID", length = 8)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "PLU", length = 5)
	public String getPlu() {
		return this.plu;
	}

	public void setPlu(String plu) {
		this.plu = plu;
	}

	@Column(name = "OrderItemKey", unique = true, length = 32)
	public String getOrderItemKey() {
		return this.orderItemKey;
	}

	public void setOrderItemKey(String orderItemKey) {
		this.orderItemKey = orderItemKey;
	}

	@Column(name = "NetQty")
	public Integer getNetQty() {
		return this.netQty;
	}

	public void setNetQty(Integer netQty) {
		this.netQty = netQty;
	}

	@Column(name = "PromoQty")
	public Integer getPromoQty() {
		return this.promoQty;
	}

	public void setPromoQty(Integer promoQty) {
		this.promoQty = promoQty;
	}

	@Column(name = "DiscountQty")
	public Integer getDiscountQty() {
		return this.discountQty;
	}

	public void setDiscountQty(Integer discountQty) {
		this.discountQty = discountQty;
	}

	@Column(name = "DiscountAmt", precision = 10)
	public Double getDiscountAmt() {
		return this.discountAmt;
	}

	public void setDiscountAmt(Double discountAmt) {
		this.discountAmt = discountAmt;
	}

	@Column(name = "TotalAmt", precision = 10)
	public Double getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Column(name = "UnitPrice", precision = 10)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "ItemType")
	public Integer getItemType() {
		return this.itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	@Column(name = "ReduceAfterTotalQty")
	public Integer getReduceAfterTotalQty() {
		return this.reduceAfterTotalQty;
	}

	public void setReduceAfterTotalQty(Integer reduceAfterTotalQty) {
		this.reduceAfterTotalQty = reduceAfterTotalQty;
	}

	@Column(name = "ReduceAfterTotalAmt", precision = 10)
	public Double getReduceAfterTotalAmt() {
		return this.reduceAfterTotalAmt;
	}

	public void setReduceAfterTotalAmt(Double reduceAfterTotalAmt) {
		this.reduceAfterTotalAmt = reduceAfterTotalAmt;
	}

	@Column(name = "TimeCreatedInDB", length = 23)
	public Date getTimeCreatedInDb() {
		return this.timeCreatedInDb;
	}

	public void setTimeCreatedInDb(Date timeCreatedInDb) {
		this.timeCreatedInDb = timeCreatedInDb;
	}

	@Column(name = "FinalAmt", precision = 10)
	public Double getFinalAmt() {
		return this.finalAmt;
	}

	public void setFinalAmt(Double finalAmt) {
		this.finalAmt = finalAmt;
	}



}