package cn.xyj.appsys.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.DevUser;

@Repository("devDao")
public interface DevDao {

	public DevUser findUser(@Param("devCode")String devCode,@Param("devPassword")String devPassword);
	
}
