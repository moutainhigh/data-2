package com.hourz.common.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.hourz.common.jdbc.row.RowMapper;

/**
 * <p>数据库操作接口</p>
 * @author hourz
 * @since 2018-09-21
 */
public interface JdbcService {
	
	/**
	 * <p>update或delete功能</p>
	 * @param sql
	 * @param params
	 * @return 变更记录数
	 * @throws SQLException
	 */
	public abstract int execute(String sql, Object[] params) throws SQLException;
 
	/**
	 * <p>update或delete功能</p>
	 * @param sql
	 * @return 变更记录数
	 * @throws SQLException
	 */
	public abstract int execute(String sql) throws SQLException;
 
	/**
	 * <p>批处理update或delete功能</p>
	 * @param sql
	 * @param params
	 * @return 变更记录数
	 * @throws SQLException
	 */
	public abstract int executeBatch(String sql, List<Object[]> params) throws SQLException;
 
	/**
	 * <p>批处理update或delete功能</p>
	 * @param sql
	 * @param params
	 * @return 变更记录数
	 * @throws SQLException
	 */
	public abstract int executeBatch(String sql) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @param params
	 * @return 原生ResultSet数据集合
	 * @throws SQLException
	 */
	public abstract ResultSet queryForResultSet(String sql, Object[] params) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @return 原生ResultSet数据集合
	 * @throws SQLException
	 */
	public abstract ResultSet queryForResultSet(String sql) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @param params
	 * @return List<?>数据集合
	 * @throws SQLException
	 */
	public abstract List<?> queryForBean(String sql, Object[] params, RowMapper<?> mapper) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @param params
	 * @return List<?>数据集合
	 * @throws SQLException
	 */
	public abstract List<?> queryForBean(String sql, RowMapper<?> mapper) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @param params
	 * @return List<Map<String, Object>>数据集合
	 * @throws SQLException
	 */
	public abstract List<Map<String, Object>> queryForMap(String sql, Object[] params) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @param params
	 * @return List<Map<String, Object>>数据集合
	 * @throws SQLException
	 */
	public abstract List<Map<String, Object>> queryForMap(String sql) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @return 统计单列记录数
	 * @throws SQLException
	 */
	public abstract int queryForInt(String sql, Object[] params) throws SQLException;
 
	/**
	 * <p>select功能</p>
	 * @param sql
	 * @return 统计单列记录数
	 * @throws SQLException
	 */
	public abstract int queryForInt(String sql) throws SQLException;
 
	/**
	 * <p>释放Connection资源</p>
	 * @param x
	 */
	public abstract void free(Connection x);
 
	/**
	 * <p>释放Statement资源</p>
	 * @param x
	 */
	public abstract void free(Statement x);
 
	/**
	 * <p>释放PreparedStatement资源</p>
	 * @param x
	 */
	public abstract void free(PreparedStatement x);
 
	/**
	 * <p>释放ResultSet资源</p>
	 * @param x
	 */
	public abstract void free(ResultSet x);
 
	/**
	 * <p>设置数据源
	 * @param dataSource
	 */
	public abstract void setDataSource(DataSource dataSource);
	
	/**
	 * <p>获取数据库链接</p>
	 * @return Connection
	 */
	public abstract Connection getConnection();
	
	/**
	 * <p>获取数据库链接自动提交</p>
	 * @param autoCommit
	 * @return Connection
	 */
	public Connection getConnection(boolean autoCommit);
}
