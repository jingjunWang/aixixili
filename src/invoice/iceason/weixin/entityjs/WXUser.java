package invoice.iceason.weixin.entityjs;

public class WXUser {
	String openid;
	String orderkey;
	String amount;
	String orderid;
	String date;
	String storeId;
	String posId;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	String payway;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOrderkey() {
		return orderkey;
	}
	public void setOrderkey(String orderkey) {
		this.orderkey = orderkey;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public WXUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
