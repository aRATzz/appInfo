package cn.xyj.appsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.AppVersionDao;
import cn.xyj.appsys.pojo.AppVersion;

@Service("appVersionService")
public class AppVersionService {

	@Resource(name="appVersionDao")
	private AppVersionDao appVersionDao;
	
	public AppVersion getAppVersion(int versionId) {
		return appVersionDao.getAppVersion(versionId);
	}
	
	public List<AppVersion> getAppVersions(int appId){
		return appVersionDao.getAppVersions(appId);
	}
	
	public int addVersion(AppVersion appVersion) {
		return appVersionDao.addVersion(appVersion);
	}
	
	public AppVersion getLatestAppversion(int appId) {
		return appVersionDao.getLatestAppversion(appId);
	}
	
	public int updateVersion(AppVersion appVersion) {
		return appVersionDao.updateVersion(appVersion);
	}
	
	public int delVersionByAppId(int appId) {
		return appVersionDao.delVersionByAppId(appId);
	}
}
