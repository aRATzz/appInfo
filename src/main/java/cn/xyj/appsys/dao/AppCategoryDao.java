package cn.xyj.appsys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.AppCategory;

@Repository("appCategoryDao")
public interface AppCategoryDao {

	AppCategory getCategory(int id);
	List<AppCategory> getCategories(int parentId);
}
