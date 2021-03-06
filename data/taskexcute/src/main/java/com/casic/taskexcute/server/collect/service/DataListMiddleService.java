package com.casic.taskexcute.server.collect.service;

import java.util.List;
import java.util.Map;

import com.casic27.sjrh.pojo.RwglCjzt;

/**
 * <p>Description</p>后期备用
 * @author hourz
 * @since 2018-10-25
 */
public interface DataListMiddleService {

	/**
	 * 源系统mysql分页查询数据
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return mysql数据库查询结果
	 */
	List<Map<String, String>> listMiddleOfMysql(String yxtb, int pageNum,int pageSize);
	/**
	 * 源系统oracle分页查询数据
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return oracle数据库查询结果
	 */
	List<Map<String, String>> listMiddleOfOracle(String yxtb, int pageNum,int pageSize);
	/**
	 * 源系统kingbase分页查询数据
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return kingbase数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfKingBase(String yxtb, int pageNum,int pageSize);
	/**
	 * 源系统mysql带主键分页查询数据
	 * @param data 源系统连接信息
	 * @param yxtb 源系统表信息
	 * @param rwglCjzt 源系统表状态信息
	 * @param key 主键
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return mysql数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfMysql(String yxtb, RwglCjzt rwglCjzt, String key, int pageNum,int pageSize);
	/**
	 * 源系统Oracle带主键分页查询数据
	 * @param data 源系统连接信息
	 * @param yxtb 源系统表信息
	 * @param rwglCjzt 源系统表状态信息
	 * @param key 主键
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return Oracle数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfOracle(String yxtb, RwglCjzt rwglCjzt, String key, int pageNum,int pageSize);
	/**
	 * 源系统kingbase带主键分页查询数据
	 * @param data 源系统连接信息
	 * @param yxtb 源系统表信息
	 * @param rwglCjzt 源系统表状态信息
	 * @param key 主键
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return kingbase数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfKingBase(String yxtb, RwglCjzt rwglCjzt, String key, int pageNum,int pageSize);
	/**
	 * 源系统mysql带主键分页查询数据
	 * @param data 源系统连接信息
	 * @param yxtb 源系统表信息
	 * @param rwglCjzt 源系统表状态信息
	 * @param key 主键
	 * @param time 时间戳
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return mysql数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfMysql(String yxtb, RwglCjzt rwglCjzt, String key, String time, int pageNum, int pageSize);
	/**
	 * 源系统Oracle带主键分页查询数据
	 * @param data 源系统连接信息
	 * @param yxtb 源系统表信息
	 * @param rwglCjzt 源系统表状态信息
	 * @param key 主键
	 * @param time 时间戳
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return Oracle数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfOracle(String yxtb, RwglCjzt rwglCjzt, String key, String time, int pageNum, int pageSize);
	/**
	 * 源系统kingbase带主键分页查询数据
	 * @param data 源系统连接信息
	 * @param yxtb 源系统表信息
	 * @param rwglCjzt 源系统表状态信息
	 * @param key 主键
	 * @param time 时间戳
	 * @param pageNum 页码
	 * @param pageSize 页面大小
	 * @return kingbase数据库表查询结果
	 */
	List<Map<String, String>> listMiddleOfKingBase(String yxtb, RwglCjzt rwglCjzt, String key, String time, int pageNum, int pageSize);
}
