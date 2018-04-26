package cn.xyj.appsys.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.BackendUser;

@Repository("managerDao")
public interface ManagerDao {

	//判断用户是否存在
	public BackendUser findUser(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
	
	
	
}
