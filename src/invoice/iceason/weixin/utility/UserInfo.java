package invoice.iceason.weixin.utility;


public class UserInfo {
	private String openid;//用户的唯一标识 
	private String nickname;//用户昵称 
	private int sex;//1为男，2为女，0未知
	private String province;//省份 
	private String city;//城市 
	private String country;//国家，如中国为CN 
	private String headimgurl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 
	private String[] privilege;//用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） 
	private String unionid;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） 
	
	public UserInfo(String openid, String nickname, int sex, String province, String city, String country,
			String headimgurl, String[] privilege, String unionid) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headimgurl = headimgurl;
		this.privilege = privilege;
		this.unionid = unionid;
	}
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String[] getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	
}
