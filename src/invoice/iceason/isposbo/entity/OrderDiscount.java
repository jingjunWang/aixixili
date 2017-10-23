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
 * OrderDiscount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OrderDiscount", schema = "dbo", catalog = "iSPOS_DB")
public class OrderDiscount implements java.io.Serializable {

	// Fields

	private Integer id;
	private PosOrder posOrder;
	private String discountId;
	private String discountKey;
	private Double discountValue;
	private String discountDescribe;
	private String discountedItems;
	private Date timeCreatedInDb;

	// Constructors

	/** default constructor */
	public OrderDiscount() {
	}

	/** full constructor */
	public OrderDiscount(PosOrder posOrder, String discountId, String discountKey, Double discountValue, String discountDescribe, String discountedItems, Date timeCreatedInDb) {
		this.posOrder = posOrder;
		this.discountId = discountId;
		this.discountKey = discountKey;
		this.discountValue = discountValue;
		this.discountDescribe = discountDescribe;
		this.discountedItems = discountedItems;
		this.timeCreatedInDb = timeCreatedInDb;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderKey")
	public PosOrder getPosOrder() {
		return this.posOrder;
	}

	public void setPosOrder(PosOrder posOrder) {
		this.posOrder = posOrder;
	}

	@Column(name = "DiscountID", length = 5)
	public String getDiscountId() {
		return this.discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	@Column(name = "DiscountKey", length = 24)
	public String getDiscountKey() {
		return this.discountKey;
	}

	public void setDiscountKey(String discountKey) {
		this.discountKey = discountKey;
	}

	@Column(name = "DiscountValue", precision = 10)
	public Double getDiscountValue() {
		return this.discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	@Column(name = "DiscountDescribe", length = 40)
	public String getDiscountDescribe() {
		return this.discountDescribe;
	}

	public void setDiscountDescribe(String discountDescribe) {
		this.discountDescribe = discountDescribe;
	}

	@Column(name = "DiscountedItems", length = 512)
	public String getDiscountedItems() {
		return this.discountedItems;
	}

	public void setDiscountedItems(String discountedItems) {
		this.discountedItems = discountedItems;
	}

	@Column(name = "TimeCreatedInDB", length = 23)
	public Date getTimeCreatedInDb() {
		return this.timeCreatedInDb;
	}

	public void setTimeCreatedInDb(Date timeCreatedInDb) {
		this.timeCreatedInDb = timeCreatedInDb;
	}

}