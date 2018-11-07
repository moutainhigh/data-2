package com.casic.taskexcute.server.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casic.taskexcute.server.collect.common.DbDataEntity;
import com.casic.taskexcute.server.collect.common.jdbc.column.ColumnModel;
import com.casic.taskexcute.server.collect.common.jdbc.table.TableModel;
import com.casic.taskexcute.server.collect.dao.ColumnInfoDao;
import com.casic.taskexcute.server.collect.dao.DbInfoDao;
import com.casic.taskexcute.server.collect.dao.TableInfoDao;
import com.casic.taskexcute.server.collect.service.DataBaseInfoService;

@Service
public class DataBaseInfoServiceImpl implements DataBaseInfoService {
	
	@Autowired
	DbInfoDao dbInfoDao;
	@Autowired
	TableInfoDao tableInfoDao;
	@Autowired
	ColumnInfoDao columnInfoDao;
	
	@Override
	public DbDataEntity getDbInfo(Integer yxtId, Integer idYxtb) {
		// 获取源系统信息
		DbDataEntity dbInfo= dbInfoDao.getDbInfo(yxtId);
		// 获取源系统表信息
		TableModel table = tableInfoDao.getTableInfo(yxtId, idYxtb);
		dbInfo.setTableName(table.getTableName());
		// 获取源系统字段信息
		List<ColumnModel> column = columnInfoDao.getcolumnInfo(idYxtb);
		dbInfo.setColumn(column);
		return dbInfo;
	}

}
