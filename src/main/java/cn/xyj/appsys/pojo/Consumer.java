package cn.xyj.appsys.pojo;

import java.io.Serializable;
import java.util.Date;

public class Consumer implements Serializable{

	private Integer id;
	private String conCode;
	private String conName;
	private String conPassword;
	private String conEmail;
	private Date creationDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConCode() {
		return conCode;
	}
	public void setConCode(String conCode) {
		this.conCode = conCode;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConPassword() {
		return conPassword;
	}
	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}
	public String getConEmail() {
		return conEmail;
	}
	public void setConEmail(String conEmail) {
		this.conEmail = conEmail;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "Consumer [id=" + id + ", conCode=" + conCode + ", conName=" + conName + ", conPassword=" + conPassword
				+ ", conEmail=" + conEmail + ", creationDate=" + creationDate + "]";
	}
	
	
	
	
	
	
}
