package cn.xyj.appsys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.xyj.appsys.pojo.AppCategory;
import cn.xyj.appsys.pojo.AppInfo;
import cn.xyj.appsys.pojo.Consumer;
import cn.xyj.appsys.pojo.DataDictionary;
import cn.xyj.appsys.pojo.DevUser;
import cn.xyj.appsys.pojo.Page;
import cn.xyj.appsys.service.AppCategoryService;
import cn.xyj.appsys.service.AppService;
import cn.xyj.appsys.service.ConsumerService;
import cn.xyj.appsys.service.DataDictionaryService;

@Controller
@RequestMapping("/consumer")
public class ConsumerControl {

	@Resource(name = "consumerService")
	private ConsumerService consumerService;
	
	@Resource(name="dataDictionaryService")
	private DataDictionaryService dataDictionaryService;
	
	@Resource(name="appCategoryService")
	private AppCategoryService appCategoryService;
	
	@Resource(name="appControl")
	private AppControl appControl;
	
	@Resource(name="appService")
	private AppService appService;
	
	/*
	 * 前往消费者的登录界面
	 */
	@RequestMapping("/toLogin")
	public String chooseConsumer() {
		return "/consumerlogin";
	}
	
	@RequestMapping("/dologin")
	public String doLogin(@RequestParam("conCode")String conCode,@RequestParam("conPassword")String conPassword,HttpSession session,Model model) {
		Consumer consumer = consumerService.findUser(conCode, conPassword);
		if(consumer!=null) {
			session.setAttribute("consumerSession", consumer);
			return "/consumer/main";
		}else {
			model.addAttribute("error", "用户名或密码错误");
			return "/consumerlogin";
		}
	}
	
	
	@RequestMapping("/app/list")
	public String check(HttpSession session) {
		//状态列表
		List<DataDictionary> statusList = dataDictionaryService.getDataDictionaries("APP_STATUS");
		List<DataDictionary> flatFormList = dataDictionaryService.getDataDictionaries("APP_FLATFORM");
		List<AppCategory> categoryLevel1List =  appCategoryService.getCategories(0);
		List<AppCategory> categoryLevel2List =  null;
		List<AppCategory> categoryLevel3List =  null;
		Page pages = new Page();
		pages.setTotalCount(appService.queryAllApps().size());//总记录数
		pages.setCurrentPageNo(1);//当前页默认1
		int pageSize = pages.getPageSize();
		//System.out.println(pageSize);
		List<AppInfo> appInfoList = appService.queryAppsByPage(0,pageSize);
		//System.out.println(appInfoList);
		for (AppInfo appInfo : appInfoList) {
			appControl.appGetMessage(appInfo);
		}
		session.setAttribute("pages", pages);
		//session.setAttribute("statusList", statusList);
		session.setAttribute("flatFormList", flatFormList);
		session.setAttribute("categoryLevel1List", categoryLevel1List);
		session.setAttribute("categoryLevel2List", categoryLevel2List);
		session.setAttribute("categoryLevel3List", categoryLevel3List);
		session.setAttribute("appInfoList", appInfoList);
		
		return "/consumer/applist";
	}
	
	
}
