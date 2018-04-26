package cn.xyj.appsys.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AppInfo implements Serializable{
    
	private Integer id;//主键id
	private String softwareName;//软件名称
	private String APKName;//apk名称
	private String supportROM;//支持ROM
	private String interfaceLanguage;//界面语言
	private BigDecimal softwareSize;//软件大小
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;//更新日期
	private Integer devId;//开发者id
	private String appInfo;//应用简介
	private Integer status;//状态（data_dictionary 1.待审核2.审核通过3.审核不通过4.已上架5.已下架）
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date onSaleDate;//上架时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date offSaleDate;//下架时间
	private Integer flatformId;//所属平台（data_dictionary，1 手机 2 平板 3 通用）
	private Integer categoryLevel3;//所属三级分类（来源于：data_dictionary）
	private Integer downloads;//下载量（单位：次）
	private Integer createdBy;//创建者（来源于dev_user开发者信息表的用户id）
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者（dev_user开发者信息表的用户id）
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Integer modifyDate;//最新更新时间
	private Integer categoryLevel1;//所属一级分类（来源于：data_dictionary）
	private Integer categoryLevel2;//所属二级分类（来源于：data_dictionary）
	private String logoPicPath;//LOGO图片url路径
	private String logoLocPath;//LOGO图片的服务器存储路径
	private Integer versionId;//最新版本id
	
	//后加
	private String flatformName;
	private String categoryLevel1Name;
	private String categoryLevel2Name;
	private String categoryLevel3Name;
	private String statusName;
	private String versionNo;
	
	public AppInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getAPKName() {
		return APKName;
	}

	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}

	public String getSupportROM() {
		return supportROM;
	}

	public void setSupportROM(String supportROM) {
		this.supportROM = supportROM;
	}

	public String getInterfaceLanguage() {
		return interfaceLanguage;
	}

	public void setInterfaceLanguage(String interfaceLanguage) {
		this.interfaceLanguage = interfaceLanguage;
	}

	public BigDecimal getSoftwareSize() {
		return softwareSize;
	}

	public void setSoftwareSize(BigDecimal softwareSize) {
		this.softwareSize = softwareSize;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOnSaleDate() {
		return onSaleDate;
	}

	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
	}

	public Date getOffSaleDate() {
		return offSaleDate;
	}

	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}

	public Integer getFlatformId() {
		return flatformId;
	}

	public void setFlatformId(Integer flatformId) {
		this.flatformId = flatformId;
	}

	public Integer getCategoryLevel3() {
		return categoryLevel3;
	}

	public void setCategoryLevel3(Integer categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Integer getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Integer modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getCategoryLevel1() {
		return categoryLevel1;
	}

	public void setCategoryLevel1(Integer categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}

	public Integer getCategoryLevel2() {
		return categoryLevel2;
	}

	public void setCategoryLevel2(Integer categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}

	public String getLogoPicPath() {
		return logoPicPath;
	}

	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}

	public String getLogoLocPath() {
		return logoLocPath;
	}

	public void setLogoLocPath(String logoLocPath) {
		this.logoLocPath = logoLocPath;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getFlatformName() {
		return flatformName;
	}

	public void setFlatformName(String flatformName) {
		this.flatformName = flatformName;
	}

	public String getCategoryLevel1Name() {
		return categoryLevel1Name;
	}

	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.categoryLevel1Name = categoryLevel1Name;
	}

	public String getCategoryLevel2Name() {
		return categoryLevel2Name;
	}

	public void setCategoryLevel2Name(String categoryLevel2Name) {
		this.categoryLevel2Name = categoryLevel2Name;
	}

	public String getCategoryLevel3Name() {
		return categoryLevel3Name;
	}

	public void setCategoryLevel3Name(String categoryLevel3Name) {
		this.categoryLevel3Name = categoryLevel3Name;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "AppInfo [id=" + id + ", softwareName=" + softwareName + ", APKName=" + APKName + ", supportROM="
				+ supportROM + ", interfaceLanguage=" + interfaceLanguage + ", softwareSize=" + softwareSize
				+ ", updateDate=" + updateDate + ", devId=" + devId + ", appInfo=" + appInfo + ", status=" + status
				+ ", onSaleDate=" + onSaleDate + ", offSaleDate=" + offSaleDate + ", flatformId=" + flatformId
				+ ", categoryLevel3=" + categoryLevel3 + ", downloads=" + downloads + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", categoryLevel1=" + categoryLevel1 + ", categoryLevel2=" + categoryLevel2 + ", logoPicPath="
				+ logoPicPath + ", logoLocPath=" + logoLocPath + ", versionId=" + versionId + ", flatformName="
				+ flatformName + ", categoryLevel1Name=" + categoryLevel1Name + ", categoryLevel2Name="
				+ categoryLevel2Name + ", categoryLevel3Name=" + categoryLevel3Name + ", statusName=" + statusName
				+ ", versionNo=" + versionNo + "]";
	}

	

	
	

	
	
	
	
}