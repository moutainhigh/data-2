package com.casic.taskexcute.server.collect.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.casic.taskexcute.server.collect.common.DbDataEntity;
import com.casic.taskexcute.server.collect.service.DataListSourceService;
import com.casic27.sjrh.pojo.RwglCjzt;

@Service
public class DataListSourceServiceImpl implements DataListSourceService {

	@Override
	public boolean testConnection(DbDataEntity dbinfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Map<String, String>> listSourceOfMysql(DbDataEntity dbinfo, String yxtb, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfOracle(DbDataEntity dbinfo, String yxtb, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfKingBase(DbDataEntity dbinfo, String yxtb, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfMysql(DbDataEntity dbinfo, String yxtb, RwglCjzt rwglCjzt, String key,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfOracle(DbDataEntity dbinfo, String yxtb, RwglCjzt rwglCjzt, String key,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfKingBase(DbDataEntity dbinfo, String yxtb, RwglCjzt rwglCjzt,
			String key, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfMysql(DbDataEntity dbinfo, String yxtb, RwglCjzt rwglCjzt, String key,
			String time, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfOracle(DbDataEntity dbinfo, String yxtb, RwglCjzt rwglCjzt, String key,
			String time, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> listSourceOfKingBase(DbDataEntity dbinfo, String yxtb, RwglCjzt rwglCjzt,
			String key, String time, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
