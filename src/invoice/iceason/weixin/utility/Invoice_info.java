package invoice.iceason.weixin.utility;

public class Invoice_info {
	Base_info base_info;
	String payee;
	String type;
	String detail;
	public Base_info getBase_info() {
		return base_info;
	}
	public void setBase_info(Base_info base_info) {
		this.base_info = base_info;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Invoice_info() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
