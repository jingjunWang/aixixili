package invoice.iceason.fapiao.entityjs;

import com.google.gson.annotations.Expose;

public class DataDescription {
	@Expose
	String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public DataDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
