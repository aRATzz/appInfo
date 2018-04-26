package cn.xyj.appsys.pojo;

import java.io.Serializable;
import java.util.Date;

public class AppCategory implements Serializable{
    
	private int id;//主键id
	private String categoryCode;//分类编码
	private String categoryName;//分类名称
	private int parentId;//父级节点id
	private int createdBy;//创建者（backend_user用户表的用户id）
	private Date creationTime;//创建时间
	private int modifyBy;//更新者（backend_user用户表的用户id）
	private Date modifyDate;//最新更新时间
	
	public AppCategory() {
	}

	public AppCategory(int id, String categoryCode, String categoryName, int parentId, int createdBy, Date creationTime,
			int modifyBy, Date modifyDate) {
		
		this.id = id;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.parentId = parentId;
		this.createdBy = createdBy;
		this.creationTime = creationTime;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "AppCategory [id=" + id + ", categoryCode=" + categoryCode + ", categoryName=" + categoryName
				+ ", parentId=" + parentId + ", createdBy=" + createdBy + ", creationTime=" + creationTime
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}
	
	
	
}