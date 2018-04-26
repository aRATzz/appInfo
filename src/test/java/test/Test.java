package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xyj.appsys.dao.ManagerDao;
import cn.xyj.appsys.pojo.BackendUser;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		ManagerDao dao = (ManagerDao) context.getBean("managerDao");
		BackendUser user = dao.findUser("admin", "123456");
		System.out.println(user);
		
	}
	
}
