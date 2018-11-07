package com.casic.taskexcute.server.collect.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.casic.taskexcute.server.collect.common.jdbc.column.ColumnModel;

@Mapper
public interface ColumnInfoDao {

	@Select("SELECT ywmc columnName, sjlx columnType, zdcd columnLength, is_zj columnKey "
			+ "FROM ysj_yxtbzd WHERE id_yxtb=#{idYxtb}")
	List<ColumnModel> getcolumnInfo(Integer idYxtb);

}
