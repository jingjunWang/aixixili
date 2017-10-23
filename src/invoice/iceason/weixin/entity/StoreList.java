package invoice.iceason.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StoreList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "StoreList", schema = "dbo", catalog = "invoice_iceason")
public class StoreList implements java.io.Serializable {

	// Fields

	private Integer id;
	private String storeid;
	private String storename;
	private Date updatetime;
	private Date createtime;

	// Constructors

	/** default constructor */
	public StoreList() {
	}

	/** full constructor */
	public StoreList(String storeid, String storename, Date updatetime, Date createtime) {
		this.storeid = storeid;
		this.storename = storename;
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

	@Column(name = "storeid")
	public String getStoreid() {
		return this.storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	@Column(name = "storename")
	public String getStorename() {
		return this.storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
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