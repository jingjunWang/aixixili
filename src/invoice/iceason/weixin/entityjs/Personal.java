package invoice.iceason.weixin.entityjs;

public class Personal extends WXUser{
	String p_name ;
	String p_mphone ;
	String p_email;
	
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_mphone() {
		return p_mphone;
	}
	public void setP_mphone(String p_mphone) {
		this.p_mphone = p_mphone;
	}
	public String getP_email() {
		return p_email;
	}
	public void setP_email(String p_email) {
		this.p_email = p_email;
	}
	public Personal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
