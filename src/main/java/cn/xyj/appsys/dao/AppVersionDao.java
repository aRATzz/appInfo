package cn.xyj.appsys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.AppVersion;

@Repository("appVersionDao")
public interface AppVersionDao {

	AppVersion getAppVersion(int versionId);
	List<AppVersion> getAppVersions(int appId);
	int addVersion(AppVersion appVersion);
	AppVersion getLatestAppversion(int appId);
	int updateVersion(AppVersion appVersion);
	int delVersionByAppId(int appId);
}
