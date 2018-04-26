package cn.xyj.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.AppInfo;
import cn.xyj.appsys.pojo.QueryForm;

@Repository("appDao")
public interface AppDao {

	List<AppInfo> queryAllApps();
	List<AppInfo> queryAppsByPage(@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);
	List<AppInfo> queryAppsLikeParams(@Param("softwareName")String softwareName,@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);//模糊查询分页显示
	List<AppInfo> queryAppsLikeParam(@Param("softwareName")String softwareName);//模糊查询总的
	List<AppInfo> queryAppsByCategories(QueryForm queryForm);
	List<AppInfo> queryAppsByCategoriesLimit(QueryForm queryForm);
	AppInfo queryAppById(int id);
	int setLogoPic(@Param("id")int id,@Param("logoPicPath")String logoPicPath,@Param("logoLocPath")String logoLocPath);
	int updateAppInfo(AppInfo appInfo);
	int deleteAppById(int id);
	int addAppInfo(AppInfo appInfo);
}
