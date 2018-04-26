package cn.xyj.appsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.AppCategoryDao;
import cn.xyj.appsys.pojo.AppCategory;

@Service("appCategoryService")
public class AppCategoryService {

	@Resource(name="appCategoryDao")
	private AppCategoryDao appCategoryDao;
	
	public AppCategory getCategory(int id) {
		return appCategoryDao.getCategory(id);
	}
	
	public List<AppCategory> getCategories(int parentId){
		return appCategoryDao.getCategories(parentId);
	}
}
