package com.hourz.common.jdbc.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.hourz.common.jdbc.row.RowMapper;
import com.hourz.common.jdbc.service.JdbcService;
import com.hourz.common.stream.StreamUtils;

/**
 * <p>Jdbc接口实现</p>
 * @author hourz
 * @since 2018-09-21
 */
public class JdbcServiceImpl implements JdbcService {
	// 自动提交是否
	private static final boolean AUTO_COMMIT = true;
	// 设置
	private DataSource dataSource;
	// 无参构造器
	public JdbcServiceImpl() {
	}
	// 有参构造器
	public JdbcServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	// 设置自动提交
	public Connection getConnection() {
		return getConnection(AUTO_COMMIT);
	}
	// 连接服务器
	public Connection getConnection(boolean autoCommit) {
		try {
			Connection conn = dataSource.getConnection();
			if (!autoCommit)
				conn.setAutoCommit(autoCommit);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 执行SQL(有条件)-(增加|修改|删除)
	@Override
	public int execute(String sql, Object[] params) throws SQLException {
		// 取消自动提交
		Connection conn = getConnection(false);
		// 设置声明
		PreparedStatement stmt = null;
		int result = -1;
		try {
			// 创建执行
			stmt = createPreparedStatement(conn, sql, params);
			// 获取执行结果
			result = stmt.executeUpdate();
			// 手动提交
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			free(stmt);
			free(conn);
		}
		return result;
	}
	// 执行SQL(无条件)-(增加|修改|删除)
	@Override
	public int execute(String sql) throws SQLException {
		return execute(sql, new Object[] {});
	}
	// 条件查询
	@Override
	public ResultSet queryForResultSet(String sql, Object[] params) throws SQLException {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		try {
			// 设置预执行
			stmt = createPreparedStatement(conn, sql, params);
			// 执行查询获取ResultSet
			return stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(stmt);
			free(conn);
		}
		return null;
	}
	// 无条件查询
	@Override
	public ResultSet queryForResultSet(String sql) throws SQLException {
		return queryForResultSet(sql, new Object[] {});
	}
	// 条件查询总数
	@Override
	public int queryForInt(String sql, Object[] params) throws SQLException {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet rs = null;
		try {
			// 设置SQL预处理声明
			stmt = createPreparedStatement(conn, sql, params);
			// 执行SQL
			rs = createResultSet(stmt);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(rs);
			free(stmt);
			free(conn);
		}
		return 0;
	}
	// 无条件查询总数
	@Override
	public int queryForInt(String sql) throws SQLException {
		return queryForInt(sql, new Object[] {});
	}
	// 固定列表-有条件查询
	@Override
	public List<?> queryForBean(String sql, Object[] params, RowMapper<?> mapper) throws SQLException {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet rs = null;
		List<Object> list = null;
		try {
			// 设置条件执行
			stmt = createPreparedStatement(conn, sql, params);
			// 
			rs = createResultSet(stmt);
			list = new ArrayList<Object>();
			while (rs.next()) {
				list.add(mapper.mapRow(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(rs);
			free(stmt);
			free(conn);
		}
		return list;
	}
	// 固定列表-无条件查询
	@Override
	public List<?> queryForBean(String sql, RowMapper<?> mapper) throws SQLException {
		return queryForBean(sql, new Object[] {}, mapper);
	}
	// 查询列表-有条件
	@Override
	public List<Map<String, String>> queryForMap(String sql, Object[] params) throws SQLException {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet rs = null;
		try {
			// 设置预处理
			stmt = createPreparedStatement(conn, sql, params);
			// 执行结果
			rs = createResultSet(stmt);
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			ResultSetMetaData rsd = rs.getMetaData();
			// 获取查询总数
			int columnCount = rsd.getColumnCount();
			while (rs.next()) {
				map = new HashMap<String, String>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i) != null) {
						if(rsd.getColumnTypeName(i).toLowerCase().contains("blob")) {
							Blob b = rs.getBlob(i);
							InputStream in = b.getBinaryStream();
							//InputStream in = rs.getBinaryStream(i);
							map.put(rsd.getColumnName(i), StreamUtils.getInstance().InputStreamTOString(in));
						} else {
							map.put(rsd.getColumnName(i), new String(rs.getString(i).getBytes(), "UTF-8"));
						}
					}
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(rs);
			free(stmt);
			free(conn);
		}
		return null;
	}
	
	// 查询列表-无条件
	@Override
	public List<Map<String, String>> queryForMap(String sql) throws SQLException {
		return queryForMap(sql, new Object[] {});
	}
	// 有条件-批量执行
	@Override
	public int executeBatch(String sql, List<Object[]> params) throws SQLException {
		int result = 0;
		// 设置取消自动提交
		Connection conn = getConnection(false);
		// 创建声明
		PreparedStatement stmt = null;
		try {
			// 设置预处理
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				Object[] param = params.get(i);
				for (int j = 0; j < param.length; j++)
					stmt.setObject(j + 1, param[j]);
				stmt.addBatch();
				if (i % 1000 == 0) {
					stmt.executeBatch();
					stmt.clearBatch();
				}
			}
			stmt.executeBatch();
			conn.commit();
			result = params.size();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			free(stmt);
			free(conn);
		}
		return result;
	}
	// 无条件-批量执行
	@Override
	public int executeBatch(String sql) throws SQLException {
		return executeBatch(sql, new ArrayList<Object[]>());
	}
	/**
	 * 释放Connection
	 */
	@Override
	public void free(Connection x) {
		if (x != null)
			try {
				x.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 释放Statement
	 */
	@Override
	public void free(Statement x) {
		if (x != null)
			try {
				x.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 释放PreparedStatement
	 */
	@Override
	public void free(PreparedStatement x) {
		if (x != null)
			try {
				x.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 释放ResultSet
	 */
	@Override
	public void free(ResultSet x) {
		if (x != null)
			try {
				x.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * <p>设置SQL执行</p>
	 * @param conn 连接信息
	 * @param sql 执行语句
	 * @param params 条件
	 * @return 声明
	 * @throws SQLException SQL组装异常
	 */
	public PreparedStatement createPreparedStatement(Connection conn, String sql, Object[] params) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) stmt.setObject(i + 1, params[i]);
		return stmt;
	}
	/**
	 * <p>执行结果</p>
	 * @param stmt
	 * @return 结果集
	 * @throws SQLException SQL组装异常
	 */
	public ResultSet createResultSet(PreparedStatement stmt) throws SQLException {
		return stmt.executeQuery();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
