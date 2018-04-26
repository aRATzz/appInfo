package cn.xyj.appsys.pojo;

public class QueryForm {

	private Integer status;
	private Integer flatformId;
	private Integer categoryLevel1;
	private Integer categoryLevel2;
	private Integer categoryLevel3;
	
	private Integer startIndex;
	private Integer pageSize;
	
	private Integer queryStatus;
	private Integer queryFlatformId;
	private Integer queryCategoryLevel1;
	private Integer queryCategoryLevel2;
	private Integer queryCategoryLevel3;
	public Integer getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(Integer queryStatus) {
		this.queryStatus = queryStatus;
	}
	public Integer getQueryFlatformId() {
		return queryFlatformId;
	}
	public void setQueryFlatformId(Integer queryFlatformId) {
		this.queryFlatformId = queryFlatformId;
	}
	public Integer getQueryCategoryLevel1() {
		return queryCategoryLevel1;
	}
	public void setQueryCategoryLevel1(Integer queryCategoryLevel1) {
		this.queryCategoryLevel1 = queryCategoryLevel1;
	}
	public Integer getQueryCategoryLevel2() {
		return queryCategoryLevel2;
	}
	public void setQueryCategoryLevel2(Integer queryCategoryLevel2) {
		this.queryCategoryLevel2 = queryCategoryLevel2;
	}
	public Integer getQueryCategoryLevel3() {
		return queryCategoryLevel3;
	}
	public void setQueryCategoryLevel3(Integer queryCategoryLevel3) {
		this.queryCategoryLevel3 = queryCategoryLevel3;
	}
	
	
	
	public Integer getStatus() {
		return queryStatus;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFlatformId() {
		return queryFlatformId;
	}
	public void setFlatformId(Integer flatformId) {
		this.flatformId = flatformId;
	}
	public Integer getCategoryLevel1() {
		return queryCategoryLevel1;
	}
	public void setCategoryLevel1(Integer categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}
	public Integer getCategoryLevel2() {
		return queryCategoryLevel2;
	}
	public void setCategoryLevel2(Integer categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}
	public Integer getCategoryLevel3() {
		return queryCategoryLevel3;
	}
	public void setCategoryLevel3(Integer categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "QueryForm [status=" + status + ", flatformId=" + flatformId + ", categoryLevel1=" + categoryLevel1
				+ ", categoryLevel2=" + categoryLevel2 + ", categoryLevel3=" + categoryLevel3 + ", startIndex="
				+ startIndex + ", pageSize=" + pageSize + ", queryStatus=" + queryStatus + ", queryFlatformId="
				+ queryFlatformId + ", queryCategoryLevel1=" + queryCategoryLevel1 + ", queryCategoryLevel2="
				+ queryCategoryLevel2 + ", queryCategoryLevel3=" + queryCategoryLevel3 + "]";
	}
	
	
	
	
	
}
