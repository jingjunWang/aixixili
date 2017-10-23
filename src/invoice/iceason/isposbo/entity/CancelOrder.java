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
 * CancelOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CancelOrder", schema = "dbo", catalog = "iSPOS_DB")
public class CancelOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderKey;
	private String operatorId;
	private String managerId;
	private Date timeCreatedInDb;

	// Constructors

	/** default constructor */
	public CancelOrder() {
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

	@Column(name = "TimeCreatedInDB", length = 23)
	public Date getTimeCreatedInDb() {
		return this.timeCreatedInDb;
	}

	public void setTimeCreatedInDb(Date timeCreatedInDb) {
		this.timeCreatedInDb = timeCreatedInDb;
	}

}