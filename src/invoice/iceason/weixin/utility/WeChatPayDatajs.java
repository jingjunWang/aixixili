package invoice.iceason.weixin.utility;

public class WeChatPayDatajs {
	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public WeChatPayDatajs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WeChatPayDatajs(String appId, String timestamp, String nonceStr, String signature) {
		super();
		this.appId = appId;
		this.timestamp = timestamp;
		this.nonceStr = nonceStr;
		this.signature = signature;
	}
	
	
}
