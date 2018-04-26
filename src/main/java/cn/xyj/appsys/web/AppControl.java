package cn.xyj.appsys.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.xyj.appsys.pojo.AppCategory;
import cn.xyj.appsys.pojo.AppInfo;
import cn.xyj.appsys.pojo.AppVersion;
import cn.xyj.appsys.pojo.DataCode;
import cn.xyj.appsys.pojo.DataDictionary;
import cn.xyj.appsys.pojo.DevUser;
import cn.xyj.appsys.service.AppCategoryService;
import cn.xyj.appsys.service.AppService;
import cn.xyj.appsys.service.AppVersionService;
import cn.xyj.appsys.service.DataDictionaryService;
import cn.xyj.appsys.service.DevService;

@Controller("appControl")
@RequestMapping("/appInfo")
public class AppControl {

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
	
	/**
	 * 给那个APPInfo加一系列名字
	 * @param appInfo
	 */
	public void appGetMessage(AppInfo appInfo) {
		//获取flatformName
		DataDictionary dataDictionary = dataDictionaryService.getDataDictionary("APP_FLATFORM", appInfo.getFlatformId());
		appInfo.setFlatformName(dataDictionary.getValueName());
		//获取app状态status
		DataDictionary dataDictionary1 = dataDictionaryService.getDataDictionary("APP_STATUS", appInfo.getStatus());
		appInfo.setStatusName(dataDictionary1.getValueName());
		//获取分类名
		AppCategory appCategory1 = appCategoryService.getCategory(appInfo.getCategoryLevel1());
		appInfo.setCategoryLevel1Name(appCategory1.getCategoryName());
		AppCategory appCategory2 = appCategoryService.getCategory(appInfo.getCategoryLevel2());
		appInfo.setCategoryLevel2Name(appCategory2.getCategoryName());
		AppCategory appCategory3 = appCategoryService.getCategory(appInfo.getCategoryLevel3());
		appInfo.setCategoryLevel3Name(appCategory3.getCategoryName());
		//获取版本号
		//System.out.println("*:"+appInfo);
		//System.out.println("**:"+appInfo.getVersionId());
		if(appInfo.getVersionId()!=null) {
			AppVersion appVersion = appVersionSerice.getAppVersion(appInfo.getVersionId());
			System.out.println("***:"+appVersion);
			appInfo.setVersionNo(appVersion.getVersionNo());
		}
	}
	
	
	@RequestMapping("/appview/{appInfo}")
	public String showView(@PathVariable("appInfo")int appId,Model model) {
		log.info("appId------"+appId);
		AppInfo appInfo = appService.queryAppById(appId);
		appGetMessage(appInfo);
		//历史版本信息
		List<AppVersion> appVersionList = appVersionSerice.getAppVersions(appId);
		for (AppVersion appVersion : appVersionList) {
			DataDictionary dataDictionary = dataDictionaryService.getDataDictionary("PUBLISH_STATUS", appVersion.getPublishStatus());
			appVersion.setPublishStatusName(dataDictionary.getValueName());
			appVersion.setAppName(appInfo.getSoftwareName());
		}
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute("appInfo", appInfo);
		return "/developer/appinfoview";
	}
	
	@RequestMapping("/modifyAppInfo")
	public String modifyAppInfo(int id,Model model) {
		AppInfo appInfo = appService.queryAppById(id);
		//获取app状态status
		DataDictionary dataDictionary1 = dataDictionaryService.getDataDictionary("APP_STATUS", appInfo.getStatus());
		appInfo.setStatusName(dataDictionary1.getValueName());
		model.addAttribute("appInfo", appInfo);
		return "/developer/appinfomodify";
	}
	
	@RequestMapping("/getFlatFormList")
	public @ResponseBody Object getFlatFormList(String tcode){
		log.info("tcode-----"+tcode);
		return dataDictionaryService.getDataDictionaries(tcode);
	}
	
	@RequestMapping("/categorylevellist")
	public @ResponseBody Object categorylevellist(int pid) {
		log.info("pid-----"+pid);
		return appCategoryService.getCategories(pid);
	}
	
	@RequestMapping("/delfile")
	public @ResponseBody Object delfile(int id,String flag) {
		log.info("id-----"+id);
		log.info("flag-----"+flag);
		//获得appinfo对象
		int result=0;
		if(flag.equals("apk")) {
			AppVersion appVersion = appVersionSerice.getAppVersion(id);
			appVersion.setDownloadLink(null);
			appVersion.setApkFileName(null);
			System.out.println(appVersion);
			result=appVersionSerice.updateVersion(appVersion);
		}else {
			result = appService.setLogoPic(id, null, null);
			log.info("result-----"+result);
		}
		if(result==1) {
			return "success";			
		}else {
			return "failed";
		}
	}
	
	@RequestMapping("/appinfomodifysave")
	public String changeModify(AppInfo appInfo,MultipartFile attach,Model model) throws IllegalStateException, IOException {
		//要获得所属平台和等级分类
		//System.out.println(appInfo);
		AppInfo appInfo2 = appService.queryAppById(appInfo.getId());
		//System.out.println(appInfo2);
		appInfo2.setSoftwareName(appInfo.getSoftwareName());
		appInfo2.setSupportROM(appInfo.getSupportROM());
		appInfo2.setInterfaceLanguage(appInfo.getInterfaceLanguage());
		appInfo2.setSoftwareSize(appInfo.getSoftwareSize());
		appInfo2.setAppInfo(appInfo.getAppInfo());
		//改status
		System.out.println("------"+appInfo.getStatusName()+"------");
		if(!appInfo.getStatusName().equals("")) {
			DataDictionary dataDictionary = dataDictionaryService.getValueIdByName(appInfo.getStatusName());
			appInfo2.setStatus(dataDictionary.getValueId());
			if((appInfo.getStatusName()).equals("审核未通过")) {
				//强行改成待审核1
				appInfo2.setStatus(1);
				appInfo2.setStatusName("待审核");
			}
		}
		//改所属平台
		//DataDictionary dataDictionary1 = dataDictionaryService.getValueIdByName(appInfo.getFlatformName());
		appInfo2.setFlatformId(appInfo.getFlatformId());
		appInfo2.setCategoryLevel1(appInfo.getCategoryLevel1());
		appInfo2.setCategoryLevel2(appInfo.getCategoryLevel2());
		appInfo2.setCategoryLevel3(appInfo.getCategoryLevel3());
		appInfo2.setDownloads(appInfo.getDownloads());
		if(attach != null) {
			System.out.println("文件名："+attach.getOriginalFilename());
			if(attach.getOriginalFilename()!="") {	
				attach.transferTo(new File("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+attach.getOriginalFilename()));
				//MultipartFile file = 
				//attach.transferTo(new File("D:\\Workspaces\\app\\src\\main\\webapp\\statics\\uploadfiles\\"+attach.getOriginalFilename()));
				appInfo2.setLogoPicPath("/app/statics/uploadfiles/"+attach.getOriginalFilename());
				appInfo2.setLogoLocPath("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+attach.getOriginalFilename());
			}
		}
		appService.updateAppInfo(appInfo2);
		System.out.println(appInfo2);
		model.addAttribute("appInfo", appInfo2);
		return "/developer/appinfomodify";
	}
	
	@RequestMapping("/appversionadd")
	public String appversionadd(Integer id,HttpServletRequest request) {
		//System.out.println("我来了");
		System.out.println("id:"+id);
		List<AppVersion> appVersionList = appVersionSerice.getAppVersions(id);
		if(appVersionList.size()!=0) {//有list
			for (AppVersion appVersion : appVersionList) {
				DataDictionary dataDictionary = dataDictionaryService.getDataDictionary("PUBLISH_STATUS", appVersion.getPublishStatus());
				appVersion.setPublishStatusName(dataDictionary.getValueName());
				appVersion.setAppName(appService.queryAppById(id).getSoftwareName());
				request.setAttribute("appVersion", appVersion);
			}
		}else {
			//没有版本信息历史，就不会有appVersionId
			AppVersion appVersion = new AppVersion();
			appVersion.setAppId(id);
			request.setAttribute("appVersion", appVersion);
		}
		
		request.setAttribute("appVersionList", appVersionList);
		return "/developer/appversionadd";
	}
	
	@RequestMapping("/addversionsave")
	public String addversionsave(AppVersion appVersion,MultipartFile a_downloadLink,HttpSession session,Model model,HttpServletRequest request) throws IllegalStateException, IOException, ParseException, ServletException {
		//保存
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		//、向数据库中添加记录
		if(a_downloadLink!=null) {
			a_downloadLink.transferTo(new File("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+a_downloadLink.getOriginalFilename()));
			appVersion.setDownloadLink("/app/statics/uploadfiles/"+a_downloadLink.getOriginalFilename());
			appVersion.setCreatedBy(user.getId());
			appVersion.setCreationDate(new Date());
			appVersion.setApkLocPath("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+a_downloadLink.getOriginalFilename());
			appVersion.setApkFileName(a_downloadLink.getOriginalFilename());
			appVersionSerice.addVersion(appVersion);
			//在APPInfo里面改versionId
		}
		//获得app对象
		AppInfo appInfo = appService.queryAppById(appVersion.getAppId());
		//获取该appId下的最新版本信息
		AppVersion appVersion2 = appVersionSerice.getLatestAppversion(appVersion.getAppId());
		//修改这个APP的versionId
		appInfo.setVersionId(appVersion2.getId());
		appService.updateAppInfo(appInfo);
		//再次重定向到添加界面，
		return "redirect:/appInfo/appversionadd?id="+appVersion.getAppId();
	}
	
	@RequestMapping("/appversionmodify")
	public String appversionmodify(int vid,int aid,HttpServletRequest request) {
		System.out.println("versionId:"+vid);
		System.out.println("appId:"+aid);
		List<AppVersion> appVersionList = appVersionSerice.getAppVersions(aid);
		for (AppVersion appVersion : appVersionList) {
			DataDictionary dataDictionary = dataDictionaryService.getDataDictionary("PUBLISH_STATUS", appVersion.getPublishStatus());
			appVersion.setPublishStatusName(dataDictionary.getValueName());
			appVersion.setAppName(appService.queryAppById(aid).getSoftwareName());
		}
		//这里没有用到vid,因为我是直接按照appId查询的而不是versionId
		AppVersion appVersion = appVersionSerice.getLatestAppversion(aid);
		//System.out.println("appVersion:"+appVersion);
		request.setAttribute("appVersion", appVersion);
		request.setAttribute("appVersionList", appVersionList);
		return "/developer/appversionmodify";
	}
	
	//修改信息保存
	@RequestMapping("/appversionmodifysave")
	public String appversionmodifysave(AppVersion appVersion,MultipartFile attach,HttpSession session) throws IllegalStateException, IOException {
		System.out.println(appVersion);
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		if(attach!=null) {
			System.out.println("---"+attach.getOriginalFilename());
			System.out.println("---"+appVersion.getApkFileName());
			if(attach.getOriginalFilename()!="") {				
				attach.transferTo(new File("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+attach.getOriginalFilename()));
				appVersion.setDownloadLink("/app/statics/uploadfiles/"+attach.getOriginalFilename());
				appVersion.setModifyBy(user.getId());
				appVersion.setModifyDate(new Date());
				appVersion.setApkLocPath("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+attach.getOriginalFilename());
				appVersion.setApkFileName(attach.getOriginalFilename());
			}
		}
		appVersionSerice.updateVersion(appVersion);
		return "redirect:/appInfo/appversionmodify?vid="+appVersion.getId()+"&aid="+appVersion.getAppId();
	}

	@RequestMapping("/saleSwitchAjax")
	public @ResponseBody Object saleSwitchAjax(Integer appId) {
		DataCode dataCode = new DataCode();
		try {
			//根据id查找到该app对象
			AppInfo appInfo = appService.queryAppById(appId);
			//查看下status状态码
			System.out.println("status:"+appInfo.getStatus());
			if(appInfo.getStatus()==4) {
				appInfo.setStatus(5);
			}else if(appInfo.getStatus()==5){
				appInfo.setStatus(4);
			}
			appService.updateAppInfo(appInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataCode.setResultMsg("failed");
			dataCode.setErrorCode(0+"");
			return JSON.toJSONString(dataCode);
		}
		dataCode.setResultMsg("success");
		dataCode.setErrorCode(0+"");
		return JSON.toJSONString(dataCode);
	}
	
	//删除app
	@RequestMapping("/deleteApp")
	public @ResponseBody Object deleteApp(int id) {
		System.out.println(id);
		//删除该商品
		appService.deleteAppById(id);
		//删除该商品的所有版本
		appVersionSerice.delVersionByAppId(id);
		DataCode dataCode = new DataCode();
		dataCode.setDelResult("true");
		return dataCode;
	}
	
	//判断该apk是否存在
	@RequestMapping("/apkexist")
	public @ResponseBody Object apkexist(String APKName) {
		System.out.println(APKName);
		DataCode dataCode = new DataCode();
		dataCode.setAPKName("noexist");
		if(APKName=="") {
			dataCode.setAPKName("empty");
		}else {
			List<AppInfo> list = appService.queryAllApps();
			for (AppInfo appInfo : list) {
				System.out.println("--"+appInfo.getAPKName());
				if ((appInfo.getAPKName()).equals(APKName)) {
					dataCode.setAPKName("exist");
				}
			}
		}
		System.out.println(dataCode.getAPKName());
		System.out.println(JSON.toJSONString(dataCode));
		return JSON.toJSONString(dataCode);
	}
	
	@RequestMapping("/toappinfoadd")
	public String add() {
		return "/developer/appinfoadd";
	}
	
	@RequestMapping("/appinfoaddsave")
	public String appinfoaddsave(AppInfo appInfo,HttpSession session,MultipartFile a_logoPicPath) throws IllegalStateException, IOException {
		System.out.println(appInfo);
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		appInfo.setDevId(user.getId());
		appInfo.setCreatedBy(user.getId());
		appInfo.setCreationDate(new Date());
		if(a_logoPicPath!=null) {
			if(a_logoPicPath.getOriginalFilename()!="") {
				a_logoPicPath.transferTo(new File("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+a_logoPicPath.getOriginalFilename()));
				appInfo.setLogoPicPath("/app/statics/uploadfiles/"+a_logoPicPath.getOriginalFilename());
				appInfo.setLogoLocPath("D:\\apache-tomcat-8.0.47\\webapps\\app\\statics\\uploadfiles\\"+a_logoPicPath.getOriginalFilename());
			}
		}
		appService.addAppInfo(appInfo);
		//return "/app/appInfo/toappinfoadd";
		return "redirect:../dev/flatform/app/list";
	}
}
