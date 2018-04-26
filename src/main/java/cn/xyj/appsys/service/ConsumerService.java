package cn.xyj.appsys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.ConsumerDao;
import cn.xyj.appsys.pojo.Consumer;

@Service("consumerService")
public class ConsumerService {

	@Resource(name = "consumerDao")
	private ConsumerDao consumerDao;
	
	/*
	 * 判断该用户是否存在
	 */
	public Consumer findUser(String conCode,String conPassword) {
		return consumerDao.findUser(conCode, conPassword);
	}
	
}
