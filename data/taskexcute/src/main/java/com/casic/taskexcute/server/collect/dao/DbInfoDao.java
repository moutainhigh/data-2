package com.casic.taskexcute.server.collect.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.casic.taskexcute.server.collect.common.DbDataEntity;

@Mapper
public interface DbInfoDao {

	/**
	 * <p>获取源数据表信息</p>
	 * @param yxtId 
	 * @return
	 */
	@Select("SELECT ip url,yh yh,mm mm, mc sjklx,sjkmc sjkmc "
			+ "FROM ysj_sjkljxx x LEFT JOIN ysj_sjklx k on x.id_sjklx=k.id WHERE x.id_yxt=#{yxtId}")
	DbDataEntity getDbInfo(Integer yxtId);
	
}
