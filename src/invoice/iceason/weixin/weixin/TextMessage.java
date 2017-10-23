package invoice.iceason.weixin.weixin;
/**

 * 文本信息

 * @author sun

 *

 */

public class TextMessage extends BaseMessage{

	// 回复的消息内容   
	private String respContent;
	
	// 文本消息
 	private String Content;
 	// 图片消息
 	private String PicUrl;
 	// 位置消息
 	private String Location_X;
 	private String Location_Y;
 	private Long Scale;
 	private String Label;
 	// 链接消息
 	private String Title;
 	private String Description;
 	private String Url;
 	// 语音信息
 	private String MediaId;
 	private String Format;
 	private String Recognition;
 	//视频消息
 	private String ThumbMediaId;
 	// 事件
 	private String Event;
 	private String EventKey;
 	private String Ticket;
 	// 上报地理位置事件
 	private String Precision;
 	private String Latitude;
 	private String Longitude;
 	//群发事件
 	private String Status;
 	private String TotalCount;
 	private String FilterCount;
 	private String SentCount;
 	private String ErrorCount;
 	
 	private String SceneValue;
 	
    public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}

	public String getContent() {  

        return Content;  

    }  

    public void setContent(String content) {  

        Content = content;  

    }

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public Long getScale() {
		return Scale;
	}

	public void setScale(Long scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(String totalCount) {
		TotalCount = totalCount;
	}

	public String getFilterCount() {
		return FilterCount;
	}

	public void setFilterCount(String filterCount) {
		FilterCount = filterCount;
	}

	public String getSentCount() {
		return SentCount;
	}

	public void setSentCount(String sentCount) {
		SentCount = sentCount;
	}

	public String getErrorCount() {
		return ErrorCount;
	}

	public void setErrorCount(String errorCount) {
		ErrorCount = errorCount;
	}

	public String getSceneValue() {
		return SceneValue;
	}

	public void setSceneValue(String sceneValue) {
		SceneValue = sceneValue;
	}  

    
    
}