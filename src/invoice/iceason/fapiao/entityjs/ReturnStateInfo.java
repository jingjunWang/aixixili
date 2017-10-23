package invoice.iceason.fapiao.entityjs;

import com.google.gson.annotations.Expose;

public class ReturnStateInfo {
	String returnCode;
	String returnMessage;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public ReturnStateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
