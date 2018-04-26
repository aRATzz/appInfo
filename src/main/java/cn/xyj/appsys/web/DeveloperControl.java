package cn.xyj.appsys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyj.appsys.pojo.AppCategory;
import cn.xyj.appsys.pojo.AppInfo;
import cn.xyj.appsys.pojo.AppVersion;
import cn.xyj.appsys.pojo.DataDictionary;
import cn.xyj.appsys.pojo.DevUser;
import cn.xyj.appsys.pojo.Page;
import cn.xyj.appsys.pojo.QueryForm;
import cn.xyj.appsys.service.AppCategoryService;
import cn.xyj.appsys.service.AppVersionService;
import cn.xyj.appsys.service.AppService;
import cn.xyj.appsys.service.DataDictionaryService;
import cn.xyj.appsys.service.DevService;

@Controller
@RequestMapping("/dev")
public class DeveloperControl {

	private Logger log = Logger.getLogger(DeveloperControl.class);
	
	@Resource(name="devService")
	private DevService devService;
	
	@Resource(name="appService")
	private AppService appService;
	
	@Resource(name="dataDictionaryService")
	private DataDictionaryService dataDictionaryService;
	
	@Resource(name="appCategoryService")
	private AppCategoryService appCategoryService;
	
	@Resource(name="appVersionService")
	private AppVersionService appVersionSerice;
	
	@Resource(name="appControl")
	private AppControl appControl;
	/**
	 * 去登陆界面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String chooseDev() {
		return "/devlogin";
	}
	
	/**
	 * 登录
	 * 获取状态列表
	 * 获得所属平台列表
	 * 获得一级分类列表
	 * 获得二级分类列表
	 * 获得三级分类列表
	 * 获取appinfo的列表
	 * @param devCode
	 * @param devPassword
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/dologin")
	public String doLogin(@RequestParam("devCode")String devCode,@RequestParam("devPassword")String devPassword,HttpSession session,Model model) {
		DevUser dUser = devService.findUser(devCode, devPassword);
		if(dUser!=null) {
			session.setAttribute("devUserSession", dUser);
			return "/developer/main";
		}else {
			model.addAttribute("error", "用户名或密码错误");
			return "/devlogin";
		}
	}
	
	@RequestMapping("/flatform/app/list")
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
		session.setAttribute("statusList", statusList);
		session.setAttribute("flatFormList", flatFormList);
		session.setAttribute("categoryLevel1List", categoryLevel1List);
		session.setAttribute("categoryLevel2List", categoryLevel2List);
		session.setAttribute("categoryLevel3List", categoryLevel3List);
		session.setAttribute("appInfoList", appInfoList);
		
		return "/developer/appinfolist";
	}
	

	
	/**
	 * 级联查询
	 * @param pid
	 * @return
	 */
	@RequestMapping("/category")
	public @ResponseBody Object category(String pid) {
		List<AppCategory> categoryLevelList =  appCategoryService.getCategories(Integer.parseInt(pid));
		return categoryLevelList;
	}
	
	/**
	 * 分页操作
	 * @param pid
	 * @return
	 */
	@RequestMapping("/list")
	public String page(HttpSession session,Integer pageIndex,QueryForm queryForm,String querySoftwareName,Model model) {
		System.out.println("pageIndex:"+pageIndex);
		Page pages = new Page();
		List<AppInfo> appInfoList = null;
		//System.out.println("queryForm+++++"+queryForm);
		System.out.println("querySoftwareName"+querySoftwareName+"---");
		if(querySoftwareName=="" && queryForm.getQueryStatus()==null && queryForm.getQueryFlatformId()==null && queryForm.getQueryCategoryLevel1()==null && queryForm.getQueryCategoryLevel2()==null && queryForm.getQueryCategoryLevel3()==null ) {
			System.out.println("我来了111");
			pages.setTotalCount(appService.queryAllApps().size());//总记录数
			pages.setCurrentPageNo(pageIndex);//当前页
			int pageSize = pages.getPageSize();
			appInfoList = appService.queryAppsByPage((pageIndex-1)*5,pageSize);
			for (AppInfo appInfo : appInfoList) {
				appControl.appGetMessage(appInfo);
			}
		}else if(querySoftwareName=="" && queryForm!=null){
			System.out.println("我来了 222");
			List<AppInfo> appInfoList1 = appService.queryAppsByCategories(queryForm);
			queryForm.setStartIndex((pageIndex-1)*5);
			queryForm.setPageSize(pages.getPageSize());
			appInfoList = appService.queryAppsByCategoriesLimit(queryForm);
			for (AppInfo appInfo : appInfoList) {
				appControl.appGetMessage(appInfo);
			}
			model.addAttribute("queryStatus", queryForm.getQueryStatus());
			model.addAttribute("queryFlatformId", queryForm.getQueryFlatformId());
			model.addAttribute("queryCategoryLevel1", queryForm.getQueryCategoryLevel1());
			model.addAttribute("queryCategoryLevel2", queryForm.getQueryCategoryLevel2());
			model.addAttribute("queryCategoryLevel3", queryForm.getQueryCategoryLevel3());
			pages.setTotalCount(appInfoList1.size());//总记录数
			pages.setCurrentPageNo(pageIndex);//当前页
		}else {//查询
			System.out.println("我来了333");
			System.out.println(querySoftwareName);
			System.out.println(pageIndex);
			List<AppInfo> appInfoList1 = appService.queryAppsLikeParam(querySoftwareName);
			appInfoList = appService.queryAppsLikeParams(querySoftwareName, (pageIndex-1)*5,pages.getPageSize());
			//System.out.println(appInfoList);
			model.addAttribute("querySoftwareName", querySoftwareName);
			for (AppInfo appInfo : appInfoList) {
				appControl.appGetMessage(appInfo);
			}
			pages.setTotalCount(appInfoList1.size());//总记录数
			pages.setCurrentPageNo(pageIndex);//当前页
		}
		
		session.setAttribute("appInfoList", appInfoList);
		session.setAttribute("pages", pages);
		return "/developer/appinfolist";
	} 
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
//		session.setAttribute("devUserSession", dUser);
//		session.setAttribute("pages", pages);
//		session.setAttribute("statusList", statusList);
//		session.setAttribute("flatFormList", flatFormList);
//		session.setAttribute("categoryLevel1List", categoryLevel1List);
//		session.setAttribute("categoryLevel2List", categoryLevel2List);
//		session.setAttribute("categoryLevel3List", categoryLevel3List);
//		session.setAttribute("appInfoList", appInfoList);
		session.removeAttribute("devUserSession");
		session.removeAttribute("pages");
		session.removeAttribute("statusList");
		session.removeAttribute("flatFormList");
		session.removeAttribute("categoryLevel1List");
		session.removeAttribute("categoryLevel2List");
		session.removeAttribute("categoryLevel3List");
		session.removeAttribute("appInfoList");
		return "redirect:/index.jsp";
	}
	
	
}
