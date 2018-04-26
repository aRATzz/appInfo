package cn.xyj.appsys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.xyj.appsys.pojo.AppCategory;
import cn.xyj.appsys.pojo.AppInfo;
import cn.xyj.appsys.pojo.AppVersion;
import cn.xyj.appsys.pojo.BackendUser;import cn.xyj.appsys.pojo.DataDictionary;
import cn.xyj.appsys.pojo.Page;
import cn.xyj.appsys.pojo.QueryForm;
import cn.xyj.appsys.service.AppCategoryService;
import cn.xyj.appsys.service.AppService;
import cn.xyj.appsys.service.AppVersionService;
import cn.xyj.appsys.service.DataDictionaryService;
import cn.xyj.appsys.service.DevService;
import cn.xyj.appsys.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerControl {

	private Logger log =Logger.getLogger(ManagerControl.class);
	
	@Resource(name="managerService")
	private ManagerService managerService;
	
	@Resource(name="dataDictionaryService")
	private DataDictionaryService dataDictionaryService;
	
	
	@Resource(name="devService")
	private DevService devService;
	
	@Resource(name="appService")
	private AppService appService;
	
	
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
	public String chooseManager() {
		return "/backendlogin";
	}
	
	/**
	 * 登录
	 * @param userCode
	 * @param userPassword
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/dologin")
	public String doLogin(String userCode,String userPassword,HttpSession session,Model model) {
		BackendUser bUser = managerService.findUser(userCode, userPassword);
		log.debug(bUser);
		if(bUser!=null) {
			DataDictionary dataDictionary = dataDictionaryService.getDataDictionary("USER_TYPE", bUser.getUserType());
			System.out.println(dataDictionary.getValueName());
			bUser.setUserTypeName(dataDictionary.getValueName());
			log.debug(bUser);
			session.setAttribute("userSession", bUser);
			return "/backend/main";
		}else {
			model.addAttribute("error", "用户名或密码错误");
			return "/backendlogin";
		}
		
	}
	
	@RequestMapping("/backend/app/list")
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
		
		return "/backend/applist";
	}
	
	/**
	 * 分页操作
	 * @param pid
	 * @return
	 */
	@RequestMapping("/list")
	public String page(HttpSession session,Integer pageIndex,QueryForm queryForm,String querySoftwareName,Model model) {
		Page pages = new Page();
		List<AppInfo> appInfoList = null;
		System.out.println("queryForm+++++"+queryForm);
		System.out.println("querySoftwareName:"+querySoftwareName+"---");
		if((querySoftwareName==null || querySoftwareName=="")  && queryForm.getQueryFlatformId()==null && queryForm.getQueryCategoryLevel1()==null && queryForm.getQueryCategoryLevel2()==null && queryForm.getQueryCategoryLevel3()==null ) {
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
		return "/backend/applist";
	}
	
	@RequestMapping("/check")
	public String check(Integer aid,Integer vid,Model model) {
		System.out.println("appInfoId:"+aid);
		System.out.println("versionId:"+vid);
		//根据aid获得appinfo对象
		AppInfo appInfo = appService.queryAppById(aid);
		appControl.appGetMessage(appInfo);
		//根据vid获得version对象
		AppVersion appVersion = appVersionSerice.getAppVersion(vid);
		model.addAttribute("appInfo", appInfo);
		model.addAttribute("appVersion", appVersion);
		return "/backend/appcheck";
	}
	
	
	@RequestMapping("/checksave")
	public String checksave(Integer id,Integer status) {
		AppInfo appInfo = appService.queryAppById(id);
		System.out.println("来了");
		appInfo.setStatus(status);
		appService.updateAppInfo(appInfo);
		return "redirect:/manager/check?aid="+appInfo.getId()+"&vid="+appInfo.getVersionId();
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
		session.removeAttribute("userSession");
		session.removeAttribute("pages");
		session.removeAttribute("flatFormList");
		session.removeAttribute("categoryLevel1List");
		session.removeAttribute("categoryLevel2List");
		session.removeAttribute("categoryLevel3List");
		session.removeAttribute("appInfoList");
		return "redirect:/index.jsp";
	}
	
}
