package cn.xyj.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xyj.appsys.pojo.DataDictionary;

@Repository("dataDictionaryDao")
public interface DataDictionaryDao {

	//获取数据字典
	public DataDictionary getDataDictionary(@Param("typeCode")String typeCode,@Param("valueId")Integer valueId);
	public List<DataDictionary> getDataDictionaries(String typeCode);
	public DataDictionary getValueIdByName(String valueName);
}
