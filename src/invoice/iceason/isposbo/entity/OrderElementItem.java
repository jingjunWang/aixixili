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
 * OrderElementItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OrderElementItem", schema = "dbo", catalog = "iSPOS_DB", uniqueConstraints = @UniqueConstraint(columnNames = "OrderElementItemKey"))
public class OrderElementItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String  orderKey;
	private String uuid;
	private String productUuid;
	private String orderElementItemKey;
	private String plu;
	private Integer elementItemQty;
	private Integer discountQty;
	private Double discountAmt;
	private Double elementItemUp;
	private Double elementItemAmt;
	private Integer elementOp;
	private Date timeCreatedInDb;

	// Constructors

	/** default constructor */
	public OrderElementItem() {
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

	@Column(name = "ProductUUID", length = 8)
	public String getProductUuid() {
		return this.productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	@Column(name = "OrderElementItemKey", unique = true, length = 35)
	public String getOrderElementItemKey() {
		return this.orderElementItemKey;
	}

	public void setOrderElementItemKey(String orderElementItemKey) {
		this.orderElementItemKey = orderElementItemKey;
	}

	@Column(name = "PLU", length = 8)
	public String getPlu() {
		return this.plu;
	}

	public void setPlu(String plu) {
		this.plu = plu;
	}

	@Column(name = "ElementItemQty")
	public Integer getElementItemQty() {
		return this.elementItemQty;
	}

	public void setElementItemQty(Integer elementItemQty) {
		this.elementItemQty = elementItemQty;
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

	@Column(name = "ElementItemUP", precision = 10)
	public Double getElementItemUp() {
		return this.elementItemUp;
	}

	public void setElementItemUp(Double elementItemUp) {
		this.elementItemUp = elementItemUp;
	}

	@Column(name = "ElementItemAmt", precision = 10)
	public Double getElementItemAmt() {
		return this.elementItemAmt;
	}

	public void setElementItemAmt(Double elementItemAmt) {
		this.elementItemAmt = elementItemAmt;
	}

	@Column(name = "ElementOP")
	public Integer getElementOp() {
		return this.elementOp;
	}

	public void setElementOp(Integer elementOp) {
		this.elementOp = elementOp;
	}

	@Column(name = "TimeCreatedInDB", length = 23)
	public Date getTimeCreatedInDb() {
		return this.timeCreatedInDb;
	}

	public void setTimeCreatedInDb(Date timeCreatedInDb) {
		this.timeCreatedInDb = timeCreatedInDb;
	}


}