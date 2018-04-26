package cn.xyj.appsys.pojo;

import java.io.Serializable;
import java.util.Date;

public class DevUser implements Serializable{
    
	private int id;//主键id
	private String devCode;//开发者账号
	private String devName;//开发者名称
	private String devPassword;//开发者密码
	private String devEmail;//开发者电子邮箱
	private String devInfo;//
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	
	public DevUser() {
	}

	public DevUser(int id, String devCode, String devName, String devPassword, String devEmail, String devInfo,
			int createdBy, Date creationDate, int modifyBy, Date modifyDate) {
		
		this.id = id;
		this.devCode = devCode;
		this.devName = devName;
		this.devPassword = devPassword;
		this.devEmail = devEmail;
		this.devInfo = devInfo;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevPassword() {
		return devPassword;
	}

	public void setDevPassword(String devPassword) {
		this.devPassword = devPassword;
	}

	public String getDevEmail() {
		return devEmail;
	}

	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}

	public String getDevInfo() {
		return devInfo;
	}

	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
		return "DevUser [id=" + id + ", devCode=" + devCode + ", devName=" + devName + ", devPassword=" + devPassword
				+ ", devEmail=" + devEmail + ", devInfo=" + devInfo + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}
	
	
	
}