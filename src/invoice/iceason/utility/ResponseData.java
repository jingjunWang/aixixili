package invoice.iceason.utility;
/**
 * JSON 响应数据 对象
*    
* 项目名称：CustomerRelationshipManagement   
* 类名称：ResponseData   
* 类描述：   
* 创建人：LRF  
* 创建时间：2016年4月6日 上午10:08:12   
* 修改人：LRF   
* 修改时间：2016年4月6日 上午10:08:12   
* 修改备注：   
* @version    
*
 */
public class ResponseData {
	/**
	 * 返回参数  成功与否
	 */
	private boolean success = false;
	/**
	 * 返回信息
	 */
	private String message = "失败";
	
	public ResponseData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResponseData(boolean success) {
		super();
		if(success&&message == "失败"){
			message = "成功";
		}
		this.success = success;
	}

	public ResponseData(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
