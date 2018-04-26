package cn.xyj.appsys.pojo;

public class Page {

	private Integer totalCount;//总记录数
	private Integer currentPageNo;//当前页
	private Integer totalPageCount;//总页数
	private Integer pageSize=5;//每页显示条数
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public Integer getTotalPageCount() {
		 int p = totalCount / pageSize;
	        if (totalCount % pageSize == 0)
	            return p;
	        else
	            return p + 1;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
}
