package cn.xyj.appsys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.ManagerDao;
import cn.xyj.appsys.pojo.BackendUser;

@Service("managerService")
public class ManagerService {

	@Resource(name = "managerDao")
	private ManagerDao managerDao;
	
	public BackendUser findUser(String userCode,String userPassword) {
		return managerDao.findUser(userCode, userPassword);
	}
	
}
