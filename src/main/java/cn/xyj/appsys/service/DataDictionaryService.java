package cn.xyj.appsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import cn.xyj.appsys.dao.DataDictionaryDao;
import cn.xyj.appsys.pojo.DataDictionary;

@Service("dataDictionaryService")
public class DataDictionaryService {

	@Resource(name="dataDictionaryDao")
	private DataDictionaryDao dataDictionaryDao;
	
	public DataDictionary getDataDictionary(String typeCode,Integer valueId) {
		//System.out.println(dataDictionaryDao.getDataDictionary("USER_TYPE", 1));
		return dataDictionaryDao.getDataDictionary(typeCode, valueId);
	}

	public List<DataDictionary> getDataDictionaries(String typeCode){
		return dataDictionaryDao.getDataDictionaries(typeCode);
	}
	
	public DataDictionary getValueIdByName(String valueName) {
		return dataDictionaryDao.getValueIdByName(valueName);
	}
}
