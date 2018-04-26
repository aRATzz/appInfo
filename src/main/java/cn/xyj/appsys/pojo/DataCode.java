package cn.xyj.appsys.pojo;

/**
 * 错误码
 * @author 22858
 *
 */

public class DataCode {

	/* * resultMsg:success/failed
	 * errorCode:exception000001
	 * appId:appId
	 * errorCode:param000001 */
	
	private String resultMsg;
	private String errorCode;
	private String delResult;
	private String APKName;
	
	
	public String getAPKName() {
		return APKName;
	}
	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}
	public String getDelResult() {
		return delResult;
	}
	public void setDelResult(String delResult) {
		this.delResult = delResult;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
