package cn.xyj.appsys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.DevDao;
import cn.xyj.appsys.pojo.BackendUser;
import cn.xyj.appsys.pojo.DevUser;

@Service("devService")
public class DevService {

	@Resource(name="devDao")
	private DevDao devDao;
	
	public DevUser findUser(String devCode,String devPassword) {
		return devDao.findUser(devCode, devPassword);
	}
	
}
