package com.casic.taskexcute.server.collect.common.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * <p> JDBC连接数据库接口 - Service层 </p>
 * @author hourz
 * @since 2018-10-24
 */
public interface JdbcTemplateService {

	/**
	 * To set up the dataSource
	 * @param dataSource dataSource
	 */
	void setDataSource(DataSource dataSource);
	
	/**
	 * To Connection DataSource
	 * @return Connection
	 */
	Connection getConnection();
	
	/**
	 * To set up the autoCommit of Connection
	 * @param autoCommit
	 * @return Connection
	 */
	Connection getConnection(boolean autoCommit);
	
	/**
	 * To Select max(ID) of rows query no params
	 * @param sql
	 * @return
	 */
	int queryForMaxInt(String sql);
	
	/**
	 * To Select max(ID) of rows query of params
	 * @param sql
	 * @param params
	 * @return
	 */
	int queryForMaxInt(String sql, Object[] params);
	
	/**
	 * To Select number of rows query no params
	 * @param sql
	 * @return
	 */
	int queryForInt(String sql);
	
	/**
	 * To Select number of rows query of params
	 * @param sql
	 * @param params
	 * @return
	 */
	int queryForInt(String sql, Object[] params);
	
	/**
	 * To Select of Mysql query no params
	 * @param sql query SQL
	 * @return result
	 */
	List<Map<String,String>> queryOfMySQL(String sql);
	
	/**
	 * To Select of Mysql query of params
	 * @param sql query SQL
	 * @param params params
	 * @return
	 */
	List<Map<String,String>> queryOfMySQL(String sql, Object[] params);
	
	/**
	 * To Select of kingbase query no params
	 * @param sql query SQL
	 * @return result
	 */
	List<Map<String,String>> queryOfKingbase(String sql);
	
	/**
	 * To Select of kingbase query of params
	 * @param sql query SQL
	 * @param params params
	 * @return result
	 */
	List<Map<String,String>> queryOfKingbase(String sql, Object[] params);
	
	/**
	 * To Select of oralce query no params
	 * @param sql query SQL
	 * @return result
	 */
	List<Map<String,String>> queryOfOracle(String sql);
	
	/**
	 * To Select of oracle query of params
	 * @param sql query SQL
	 * @param params params
	 * @return result
	 */
	List<Map<String,String>> queryOfOracle(String sql, Object[] params);
	
	/**
	 * To Select of oralce by long query no params
	 * @param sql query SQL
	 * @return result
	 */
	List<Map<String,String>> queryByFunctionLongOfOracle(String sql);
	
	/**
	 * To Select of oralce by long query of params
	 * @param sql query SQL
	 * @return result
	 */
	List<Map<String,String>> queryByFunctionLongOfOracle(String sql, Object[] params);
	
	/**
	 * Execute create sql of Mysql
	 * @param sql execute SQL
	 * @return execute result
	 */
	int executeCreateOfMySQL(String sql);
	
	/**
	 * Execute create sql of Kingbase
	 * @param sql execute SQL
	 * @return execute result
	 */
	int executeCreateOfKingbase(String sql);
	
	/**
	 * Execute create sql of Kingbase
	 * @param sql execute SQL
	 * @return execute result
	 */
	int executeCreateOfOracle(String sql);
	
	
	/**
	 * Execute sql for insert or update or delete
	 * @param sql execute SQL
	 * @return execute result
	 */
	int execute(String sql);
	
	/**
	 * Execute sql for insert or update or delete of params
	 * @param sql execute SQL
	 * @param params params
	 * @return execute result
	 */
	int execute(String sql, Object[] params);
	
	/**
	 * ExecuteBatch sql for insert or Insert or delete
	 * @param sql execute SQL
	 * @return execute result
	 */
	int executeBatch(String sql);
	
	/**
	 * ExecuteBatch sql for insert or Insert or delete
	 * @param sql execute SQL
	 * @return execute result
	 */
	int executeBatch(String sql, List<Object[]> params);
	
	/**
	 * close Connection active status
	 * @param conn Connection command
	 */
	public void free(Connection conn);
 
	/**
	 * close Statement active status
	 * @param statement statement
	 */
	public void free(Statement statement);
 
	/**
	 * close PreparedStatement active status
	 * @param preparedStatement prepare statement
	 */
	public void free(PreparedStatement preparedStatement);
 
	/**
	 * close ResultSet active status
	 * @param resultSet result set
	 */
	public void free(ResultSet resultSet);
	
	/**
	 * Mysql Turn ResultSet to List
	 * @param rs result set
	 * @return List of map key String value String
	 * @throws SQLException 
	 */
	List<Map<String, String>> setMapOfMysql(ResultSet resultSet) throws SQLException;
	
	/**
	 * KingBase Turn ResultSet to List
	 * @param rs result set
	 * @return List of map key String value String
	 * @throws SQLException 
	 */
	List<Map<String, String>> setMapOfKingBase(ResultSet resultSet) throws SQLException;
	
	/**
	 * Oracle Turn ResultSet to List
	 * @param rs result set
	 * @return List of map key String value String
	 * @throws SQLException 
	 */
	List<Map<String, String>> setMapOfOracle(ResultSet resultSet) throws SQLException;
}
