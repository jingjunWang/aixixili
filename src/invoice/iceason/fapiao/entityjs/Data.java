package invoice.iceason.fapiao.entityjs;

import com.google.gson.annotations.Expose;

public class Data {
	DataDescription dataDescription;
	String content;
	public DataDescription getDataDescription() {
		return dataDescription;
	}
	public void setDataDescription(DataDescription dataDescription) {
		this.dataDescription = dataDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
