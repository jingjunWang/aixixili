package invoice.iceason.weixin.entityjs;

import java.util.Collections;
import java.util.List;

/**   
 * @Title: Page.java 
 * @Package 
 * @Description: 分页实体类
 * @author lrf
 * @version V1.0   
 */
public class Page<T> {
	//-- 分页参数 --//
	
	/**
	 * 当前页码
	 */
	protected int pageNo = 1; 
	
	
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return this.pageNo;
	}
	
	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}
	
	
	
	
	
	/**
	 * 每页记录数
	 */
	protected int pageSize = 20; 
	
	
	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize);
	}
	
	
	/**
	 * 总的记录数
	 */
	protected int totalCount = 0;
	/**
	 * 列表界面的数据
	 */
	protected List<T> result = Collections.emptyList(); 
	
	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数, 默认值为0.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
		getTotalPages();
		//getNum();
	}
	
	
	/**
	 * 总的页数
	 */
	protected int totalPages = 0;
	
	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为0.
	 */
	public int getTotalPages() {
		if (totalCount < 0) {
			return 0;
		}

		totalPages = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			totalPages++;
		}
		return totalPages;
	}
	
	
	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public Page(int pageNo, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 页面显示最大数字
	 */
	private int num=5;
	private int num1;


	public int getNum() {
		if (num>totalPages) {
			num=totalPages;
			return num;
		}
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum1() {
		int i=(num%2==1?num/2+1:num/2);
		if (pageNo<=i) {
			num1=0;
		}else {						
			if (pageNo>(totalPages-i)) {				
				num1=totalPages-num;
			}else {				
			num1=pageNo-i;
			}						
		}
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}


	public boolean getQian()
	{
		int i=(num%2==1?num/2+1:num/2);
		
		if (pageNo>i&&num<totalPages) {
			return true;
		}
		return false;
	}
	public boolean getHou()
	{
		int i=(num%2==1?num/2+1:num/2);
		if (pageNo<=totalPages-i&&num<totalPages) {
			return true;
		}
		return false;
	}

}
