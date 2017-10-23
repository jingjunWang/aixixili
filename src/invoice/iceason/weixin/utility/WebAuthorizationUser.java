package invoice.iceason.weixin.utility;


public class WebAuthorizationUser extends WebAuthorization{
	private String unionid;
	
	public WebAuthorizationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebAuthorizationUser(String access_token, int expires_in, String refresh_token, String openid, String scope) {
		super(access_token, expires_in, refresh_token, openid, scope);
		// TODO Auto-generated constructor stub
	}

	public WebAuthorizationUser(String access_token, int expires_in, String refresh_token, String openid, String scope,
			String unionid) {
		super(access_token, expires_in, refresh_token, openid, scope);
		this.unionid = unionid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
