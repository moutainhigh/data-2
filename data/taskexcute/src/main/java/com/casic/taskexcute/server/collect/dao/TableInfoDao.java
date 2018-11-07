package com.casic.taskexcute.server.collect.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.casic.taskexcute.server.collect.common.jdbc.table.TableModel;

@Mapper
public interface TableInfoDao {

	@Select("SELECT ywbmc tableName,zwbmc tableComment, sfcgcj isCreate "
			+ "FROM ysj_yxtb WHERE id_yxt=#{idYxt} and id=#{idYxtb}")
	TableModel getTableInfo(@Param("idYxt") Integer idYxt, @Param("idYxt") Integer idYxtb);
}
