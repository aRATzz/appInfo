package cn.xyj.appsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.AppDao;
import cn.xyj.appsys.pojo.AppInfo;
import cn.xyj.appsys.pojo.QueryForm;

@Service("appService")
public class AppService {

	@Resource(name="appDao")
	private AppDao appDao;
	
	
	public List<AppInfo> queryAllApps() {
		return appDao.queryAllApps();
	}
	
	public List<AppInfo> queryAppsByPage(int startIndex,int pageSize){
		return appDao.queryAppsByPage(startIndex, pageSize);
	}
	
	public List<AppInfo> queryAppsLikeParams(String softwareName,int startIndex,int  pageSize){
		return appDao.queryAppsLikeParams(softwareName, startIndex, pageSize);
	}

	public List<AppInfo> queryAppsLikeParam(String softwareName){
		return appDao.queryAppsLikeParam(softwareName);
	}
	
	public List<AppInfo> queryAppsByCategories(QueryForm queryForm){
		return appDao.queryAppsByCategories(queryForm);
	}
	
	public List<AppInfo> queryAppsByCategoriesLimit(QueryForm queryForm){
		return appDao.queryAppsByCategoriesLimit(queryForm);
	}
	
	public AppInfo queryAppById(int id) {
		return appDao.queryAppById(id);
	}

	public int setLogoPic(int id,String logoPicPath,String logoLocPath) {
		return appDao.setLogoPic(id, logoPicPath, logoLocPath);
	}
	
	public int updateAppInfo(AppInfo appInfo) {
		return appDao.updateAppInfo(appInfo);
	}
	
	public int deleteAppById(int id) {
		return appDao.deleteAppById(id);
	}
	
	public int addAppInfo(AppInfo appInfo) {
		return appDao.addAppInfo(appInfo);
	}
}
