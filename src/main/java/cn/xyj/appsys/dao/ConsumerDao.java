package cn.xyj.appsys.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.Consumer;

@Repository("consumerDao")
public interface ConsumerDao {

	public Consumer findUser(@Param("conCode")String conCode,@Param("conPassword")String conPassword);
	
}
