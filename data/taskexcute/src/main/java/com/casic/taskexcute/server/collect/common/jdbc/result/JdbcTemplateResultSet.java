package com.casic.taskexcute.server.collect.common.jdbc.result;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.casic.taskexcute.server.collect.common.stream.StreamUtils;

/**
 * <p>Jdbc的ResultSet结果集封装</p>
 * @author hourz
 * @since 2018-10-24
 */
public class JdbcTemplateResultSet {
	
	protected Logger logger = LoggerFactory.getLogger(JdbcTemplateResultSet.class);

	private static JdbcTemplateResultSet singleton;
	/**
	 * <p>无参构造器</p>
	 */
	private JdbcTemplateResultSet(){
		
	}
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static JdbcTemplateResultSet getInstance(){
		if(singleton == null){
			synchronized(JdbcTemplateResultSet.class){
				if(singleton == null){
					singleton = new JdbcTemplateResultSet();
				}
			}
		}
		return singleton;
	}
	/**
	 * 将mysql结果集转换为的List
	 * @param resultSet 查询结果
	 * @return List<Map<String, String>>
	 * @throws SQLException 
	 */
	public List<Map<String, String>> setMapOfMysql(ResultSet resultSet) throws SQLException {
		ResultSetMetaData resultSetTable = null;
		try {
			resultSetTable = resultSet.getMetaData();
		} catch (SQLException e1) {
			logger.error("获取表信息失败！");
			e1.printStackTrace();
		}
		// 获取查询总数
		int columnCount = 0;
		try {
			columnCount = resultSetTable.getColumnCount();
		} catch (SQLException e1) {
			logger.error("获取列总数失败！");
			e1.printStackTrace();
		}
		return cycleResultOfMysql(resultSet, columnCount, resultSetTable);
	
	}
	
	private List<Map<String, String>>  cycleResultOfMysql(ResultSet resultSet, int columnCount, ResultSetMetaData resultSetTable) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		while (resultSet.next()) {
			map = new HashMap<String, String>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				try {
					if(resultSet.getObject(i) != null) {
						try {
							if(resultSetTable.getColumnTypeName(i).toLowerCase().contains("blob")) {
								Blob b = null;
								try {
									b = resultSet.getBlob(i);
								} catch (SQLException e) {
									logger.error("获取Blob内容失败！");
									e.printStackTrace();
								}
								InputStream in = null;
								try {
									in = b.getBinaryStream();
								} catch (SQLException e) {
									logger.error("转换Blob为二进制流失败！");
									e.printStackTrace();
								}
								try {
									map.put(resultSetTable.getColumnName(i), StreamUtils.getInstance().InputStreamTOString(in));
								} catch (SQLException e) {
									logger.error("获取表列名失败！");
									e.printStackTrace();
								}
							} else {
								try {
									map.put(resultSetTable.getColumnName(i), new String(resultSet.getString(i).getBytes(), "UTF-8"));
								} catch (UnsupportedEncodingException e) {
									logger.error("格式化String编码格式失败！");
									e.printStackTrace();
								}
							}
						} catch (SQLException e) {
							logger.error("获取列类型失败！");
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					logger.error("获取Object结果类型数据失败！");
					e.printStackTrace();
				}
			}
			list.add(map);
		}
		return list;
	}
	public List<Map<String, String>> setMapOfKingBase(ResultSet resultSet) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		ResultSetMetaData resultSetTable = null;
		try {
			resultSetTable = resultSet.getMetaData();
			} catch (SQLException e1) {
				logger.error("获取表信息失败！");
				e1.printStackTrace();
			}
			// 获取查询总数
			int columnCount = 0;
			try {
				columnCount = resultSetTable.getColumnCount();
			} catch (SQLException e1) {
				logger.error("获取列总数失败！");
				e1.printStackTrace();
			}
			try {
				while (resultSet.next()) {
				map = new HashMap<String, String>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					try {
						if(resultSet.getObject(i) != null) {
							try {
								if(resultSetTable.getColumnTypeName(i).toLowerCase().contains("blob")) {
									Blob b = null;
									try {
										b = resultSet.getBlob(i);
									} catch (SQLException e) {
										logger.error("获取Blob内容失败！");
										e.printStackTrace();
									}
									InputStream in = null;
									try {
										in = b.getBinaryStream();
									} catch (SQLException e) {
										logger.error("转换Blob为二进制流失败！");
										e.printStackTrace();
									}
									try {
										map.put(resultSetTable.getColumnName(i), StreamUtils.getInstance().InputStreamTOString(in));
									} catch (SQLException e) {
										logger.error("获取表列名失败！");
										e.printStackTrace();
									}
								} else {
									try {
										map.put(resultSetTable.getColumnName(i), new String(resultSet.getString(i).getBytes(), "UTF-8"));
									} catch (UnsupportedEncodingException e) {
										logger.error("格式化String编码格式失败！");
										e.printStackTrace();
									}
								}
							} catch (SQLException e) {
								logger.error("获取列类型失败！");
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						logger.error("获取Object结果类型数据失败！");
						e.printStackTrace();
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("循环结果数据失败！");
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String, String>> setMapOfOracle(ResultSet resultSet) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		ResultSetMetaData resultSetTable = null;
		try {
			resultSetTable = resultSet.getMetaData();
			} catch (SQLException e1) {
				logger.error("获取表信息失败！");
				e1.printStackTrace();
			}
			// 获取查询总数
			int columnCount = 0;
			try {
				columnCount = resultSetTable.getColumnCount();
			} catch (SQLException e1) {
				logger.error("获取列总数失败！");
				e1.printStackTrace();
			}
			try {
				while (resultSet.next()) {
				map = new HashMap<String, String>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					try {
						if(resultSet.getObject(i) != null) {
							try {
								if(resultSetTable.getColumnTypeName(i).toLowerCase().contains("blob")) {
									Blob b = null;
									try {
										b = resultSet.getBlob(i);
									} catch (SQLException e) {
										logger.error("获取Blob内容失败！");
										e.printStackTrace();
									}
									InputStream in = null;
									try {
										in = b.getBinaryStream();
									} catch (SQLException e) {
										logger.error("转换Blob为二进制流失败！");
										e.printStackTrace();
									}
									try {
										map.put(resultSetTable.getColumnName(i), StreamUtils.getInstance().InputStreamTOString(in));
									} catch (SQLException e) {
										logger.error("获取表列名失败！");
										e.printStackTrace();
									}
								} else {
									try {
										map.put(resultSetTable.getColumnName(i), new String(resultSet.getString(i).getBytes(), "UTF-8"));
									} catch (UnsupportedEncodingException e) {
										logger.error("格式化String编码格式失败！");
										e.printStackTrace();
									}
								}
							} catch (SQLException e) {
								logger.error("获取列类型失败！");
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						logger.error("获取Object结果类型数据失败！");
						e.printStackTrace();
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("循环结果数据失败！");
			e.printStackTrace();
		}
		return list;
	}
}
