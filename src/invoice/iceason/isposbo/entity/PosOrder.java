package invoice.iceason.isposbo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PosOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PosOrder", schema = "dbo", catalog = "iSPOS_DB", uniqueConstraints = @UniqueConstraint(columnNames = "OrderKey"))
public class PosOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private String storeId;
	private String posId;
	private String orderId;
	private String floorId;
	private String tableId;
	private Date businessDate;
	private String orderKey;
	private Date createTime;
	private Date paymentStartTime;
	private Date paymentCompleteTime;
	private String operatorId;
	private String managerId;
	private Integer orderType;
	private Integer orderStatus;
	private Double orderTotal;
	private Double orderChange;
	private Double orderOverCharge;
	private Integer reduceBeforeTotaQty;
	private Double reduceBeforeTotalAmt;
	private Integer reduceAfterTotaQty;
	private Double reduceAfterTotalAmt;
	private Integer orderSource;
	private Date orderSourceCreateTime;
	private Date orderSourceCallTime;
	private Date orderCalendarDate;
	private Date storeTime;
	private Date presentTime;
	private Double rroundingValue;
	private String memberShipId;
	private Integer memberShipLevel;
	private Double couponGiveoutAmt;
	private Integer THeadCounts;
	private Double netAmt;
	private Date timeCreatedInDb;
	private String orderTaker;
	private String memo;
	private String sourcePosId;

	// Constructors

	/** default constructor */
	public PosOrder() {
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

	@Column(name = "StoreID", length = 4)
	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(name = "PosID", length = 2)
	public String getPosId() {
		return this.posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	@Column(name = "OrderID", length = 5)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "FloorID", length = 4)
	public String getFloorId() {
		return this.floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	@Column(name = "TableID", length = 4)
	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Column(name = "BusinessDate", length = 23)
	public Date getBusinessDate() {
		return this.businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	@Column(name = "OrderKey", unique = true, length = 19)
	public String getOrderKey() {
		return this.orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	@Column(name = "CreateTime", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "PaymentStartTime", length = 23)
	public Date getPaymentStartTime() {
		return this.paymentStartTime;
	}

	public void setPaymentStartTime(Date paymentStartTime) {
		this.paymentStartTime = paymentStartTime;
	}

	@Column(name = "PaymentCompleteTime", length = 23)
	public Date getPaymentCompleteTime() {
		return this.paymentCompleteTime;
	}

	public void setPaymentCompleteTime(Date paymentCompleteTime) {
		this.paymentCompleteTime = paymentCompleteTime;
	}

	@Column(name = "OperatorID", length = 10)
	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "ManagerID", length = 10)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "OrderType")
	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Column(name = "OrderStatus")
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "OrderTotal", precision = 10)
	public Double getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	@Column(name = "OrderChange", precision = 10)
	public Double getOrderChange() {
		return this.orderChange;
	}

	public void setOrderChange(Double orderChange) {
		this.orderChange = orderChange;
	}

	@Column(name = "OrderOverCharge", precision = 10)
	public Double getOrderOverCharge() {
		return this.orderOverCharge;
	}

	public void setOrderOverCharge(Double orderOverCharge) {
		this.orderOverCharge = orderOverCharge;
	}

	@Column(name = "ReduceBeforeTotaQty")
	public Integer getReduceBeforeTotaQty() {
		return this.reduceBeforeTotaQty;
	}

	public void setReduceBeforeTotaQty(Integer reduceBeforeTotaQty) {
		this.reduceBeforeTotaQty = reduceBeforeTotaQty;
	}

	@Column(name = "ReduceBeforeTotalAmt", precision = 10)
	public Double getReduceBeforeTotalAmt() {
		return this.reduceBeforeTotalAmt;
	}

	public void setReduceBeforeTotalAmt(Double reduceBeforeTotalAmt) {
		this.reduceBeforeTotalAmt = reduceBeforeTotalAmt;
	}

	@Column(name = "ReduceAfterTotaQty")
	public Integer getReduceAfterTotaQty() {
		return this.reduceAfterTotaQty;
	}

	public void setReduceAfterTotaQty(Integer reduceAfterTotaQty) {
		this.reduceAfterTotaQty = reduceAfterTotaQty;
	}

	@Column(name = "ReduceAfterTotalAmt", precision = 10)
	public Double getReduceAfterTotalAmt() {
		return this.reduceAfterTotalAmt;
	}

	public void setReduceAfterTotalAmt(Double reduceAfterTotalAmt) {
		this.reduceAfterTotalAmt = reduceAfterTotalAmt;
	}

	@Column(name = "OrderSource")
	public Integer getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	@Column(name = "OrderSourceCreateTime", length = 23)
	public Date getOrderSourceCreateTime() {
		return this.orderSourceCreateTime;
	}

	public void setOrderSourceCreateTime(Date orderSourceCreateTime) {
		this.orderSourceCreateTime = orderSourceCreateTime;
	}

	@Column(name = "OrderSourceCallTime", length = 23)
	public Date getOrderSourceCallTime() {
		return this.orderSourceCallTime;
	}

	public void setOrderSourceCallTime(Date orderSourceCallTime) {
		this.orderSourceCallTime = orderSourceCallTime;
	}

	@Column(name = "OrderCalendarDate", length = 23)
	public Date getOrderCalendarDate() {
		return this.orderCalendarDate;
	}

	public void setOrderCalendarDate(Date orderCalendarDate) {
		this.orderCalendarDate = orderCalendarDate;
	}

	@Column(name = "StoreTime", length = 23)
	public Date getStoreTime() {
		return this.storeTime;
	}

	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}

	@Column(name = "PresentTime", length = 23)
	public Date getPresentTime() {
		return this.presentTime;
	}

	public void setPresentTime(Date presentTime) {
		this.presentTime = presentTime;
	}

	@Column(name = "RroundingValue", precision = 10)
	public Double getRroundingValue() {
		return this.rroundingValue;
	}

	public void setRroundingValue(Double rroundingValue) {
		this.rroundingValue = rroundingValue;
	}

	@Column(name = "MemberShipID", length = 20)
	public String getMemberShipId() {
		return this.memberShipId;
	}

	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}

	@Column(name = "MemberShipLevel")
	public Integer getMemberShipLevel() {
		return this.memberShipLevel;
	}

	public void setMemberShipLevel(Integer memberShipLevel) {
		this.memberShipLevel = memberShipLevel;
	}

	@Column(name = "CouponGiveoutAmt", precision = 10)
	public Double getCouponGiveoutAmt() {
		return this.couponGiveoutAmt;
	}

	public void setCouponGiveoutAmt(Double couponGiveoutAmt) {
		this.couponGiveoutAmt = couponGiveoutAmt;
	}

	@Column(name = "T_HeadCounts")
	public Integer getTHeadCounts() {
		return this.THeadCounts;
	}

	public void setTHeadCounts(Integer THeadCounts) {
		this.THeadCounts = THeadCounts;
	}

	@Column(name = "NetAmt", precision = 10)
	public Double getNetAmt() {
		return this.netAmt;
	}

	public void setNetAmt(Double netAmt) {
		this.netAmt = netAmt;
	}

	@Column(name = "TimeCreatedInDB", length = 23)
	public Date getTimeCreatedInDb() {
		return this.timeCreatedInDb;
	}

	public void setTimeCreatedInDb(Date timeCreatedInDb) {
		this.timeCreatedInDb = timeCreatedInDb;
	}

	@Column(name = "OrderTaker", length = 20)
	public String getOrderTaker() {
		return this.orderTaker;
	}

	public void setOrderTaker(String orderTaker) {
		this.orderTaker = orderTaker;
	}

	@Column(name = "Memo", length = 64)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "SourcePosID", length = 16)
	public String getSourcePosId() {
		return this.sourcePosId;
	}

	public void setSourcePosId(String sourcePosId) {
		this.sourcePosId = sourcePosId;
	}

}