package com.casic.taskexcute.server.collect.common.jdbc.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.casic.taskexcute.server.collect.common.jdbc.result.JdbcTemplateResultSet;
import com.casic.taskexcute.server.collect.common.jdbc.service.JdbcTemplateService;

/**
 * <p> JDBC连接数据库接口实现 -implements层 </p>
 * @author hourz
 * @since 2018-10-24
 */
@Service
public class JdbcTemplateServiceImpl implements JdbcTemplateService {

	protected Logger logger = LoggerFactory.getLogger(JdbcTemplateServiceImpl.class);
	// 自动提交是否
	private static final boolean AUTO_COMMIT = true;
	// 设置
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	// 设置自动提交
	public Connection getConnection() {
		return getConnection(AUTO_COMMIT);
	}
	// 设置手动提交
	@Override
	public Connection getConnection(boolean autoCommit) {
		try {
			Connection conn = dataSource.getConnection();
			if (!autoCommit)
				conn.setAutoCommit(autoCommit);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// 查询最大自增字段值-无参数
	@Override
	public int queryForMaxInt(String sql) {
		return queryForMaxInt(sql, new Object[] {});
	}
	// 查询最大自增字段值-有参数
	@Override
	public int queryForMaxInt(String sql, Object[] params) {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet resultSet = null;
		try {
			// 设置SQL预处理声明
			stmt = createPreparedStatement(conn, sql, params);
			// 执行SQL
			resultSet = createResultSet(stmt);
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			logger.error("查询自增主键最大值失败！");
			e.printStackTrace();
		} finally {
			free(resultSet);
			free(stmt);
			free(conn);
		}
		return 0;
	}
	// 统计表行数-无参数
	@Override
	public int queryForInt(String sql) {
		return queryForInt(sql, new Object[] {});
	}
	// 统计表行数-有参数
	@Override
	public int queryForInt(String sql, Object[] params) {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet resultSet = null;
		try {
			// 设置SQL预处理声明
			stmt = createPreparedStatement(conn, sql, params);
			// 执行SQL
			resultSet = createResultSet(stmt);
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			logger.error("查询自增主键表行数失败！");
			e.printStackTrace();
		} finally {
			free(resultSet);
			free(stmt);
			free(conn);
		}
		return 0;
	}
	// 查询MYSQL表中数据-无参数
	@Override
	public List<Map<String, String>> queryOfMySQL(String sql) {
		return queryOfMySQL(sql, new Object[] {});
	}
	// 查询MYSQL表中数据-有参数
	@Override
	public List<Map<String, String>> queryOfMySQL(String sql, Object[] params) {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet resultSet = null;
		try {
			// 设置预处理
			stmt = createPreparedStatement(conn, sql, params);
			// 执行结果
			resultSet = createResultSet(stmt);
			List<Map<String, String>> resultList = setMapOfMysql(resultSet);
			return resultList;
		} catch (Exception e) {
			logger.error("循环列表失败！");
			e.printStackTrace();
			return null;
		} finally {
			free(resultSet);
			free(stmt);
			free(conn);
		}
	}
	// 查询KINGBASE表中数据-无参数
	@Override
	public List<Map<String, String>> queryOfKingbase(String sql) {
		return queryOfKingbase(sql, new Object[] {});
	}
	// 查询KINGBASE表中数据-有参数
	@Override
	public List<Map<String, String>> queryOfKingbase(String sql, Object[] params) {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet resultSet = null;
		try {
			// 设置预处理
			stmt = createPreparedStatement(conn, sql, params);
			// 执行结果
			resultSet = createResultSet(stmt);
			List<Map<String, String>> resultList = setMapOfKingBase(resultSet);
			return resultList;
		} catch (Exception e) {
			logger.error("循环列表失败！");
			e.printStackTrace();
			return null;
		} finally {
			free(resultSet);
			free(stmt);
			free(conn);
		}
	}
	// 查询ORACLE表中数据-无参数
	@Override
	public List<Map<String, String>> queryOfOracle(String sql) {
		return queryOfOracle(sql, new Object[] {});
	}
	// 查询ORACLE表中数据-有参数
	@Override
	public List<Map<String, String>> queryOfOracle(String sql, Object[] params) {
		// 设置自动提交
		Connection conn = getConnection();
		// 创建声明
		PreparedStatement stmt = null;
		// 执行结果
		ResultSet resultSet = null;
		try {
			// 设置预处理
			stmt = createPreparedStatement(conn, sql, params);
			// 执行结果
			resultSet = createResultSet(stmt);
			List<Map<String, String>> resultList = setMapOfOracle(resultSet);
			return resultList;
		} catch (Exception e) {
			logger.error("循环列表失败！");
			e.printStackTrace();
			return null;
		} finally {
			free(resultSet);
			free(stmt);
			free(conn);
		}
	}
	// 判断CPM_LONG_TO_CHAR函数是否存在：存在执行SQL,不存在创建函数-有参数
	@Override
	public List<Map<String, String>> queryByFunctionLongOfOracle(String sql) {
		return queryByFunctionLongOfOracle(sql, new Object[] {});
	}
	// 判断CPM_LONG_TO_CHAR函数是否存在：存在执行SQL,不存在创建函数-有参数
	@Override
	public List<Map<String, String>> queryByFunctionLongOfOracle(String sql, Object[] params) {
		// 组装CPM_LONG_TO_CHAR函数查询SQL
		String queryFunction="SELECT OBJECT_NAME OBJECTNAME FROM USER_PROCEDURES where OBJECT_NAME='CPM_LONG_TO_CHAR'";
		List<Map<String, String>> list= queryOfOracle(queryFunction);
		if(list.size() != 0) {
			// 组装CPM_LONG_TO_CHAR函数创建SQL
			String createFunction="CREATE OR REPLACE FUNCTION CPM_LONG_TO_CHAR( "
					+ " IN_ROWID ROWID,  "
					+ " IN_OWNER VARCHAR, "
					+ " IN_TABLE_NAME VARCHAR, "
					+ " IN_COLUMN VARCHAR2) " 
					+ " RETURN VARCHAR AS "
					+ " TEXT_C1 VARCHAR2(32767); "
					+ " SQL_CUR VARCHAR2(2000); "
					+ " BEGIN  "
					+ " DBMS_OUTPUT.ENABLE (BUFFER_SIZE=>NULL); " 
					+ " SQL_CUR := 'SELECT '||IN_COLUMN||' FROM '||IN_OWNER||'.'||IN_TABLE_NAME||' WHERE ROWID = '||CHR(39)||IN_ROWID||CHR(39); " 
					+ " DBMS_OUTPUT.PUT_LINE (SQL_CUR); " 
					+ " EXECUTE IMMEDIATE SQL_CUR INTO TEXT_C1; " 
					+ " TEXT_C1 := SUBSTR(TEXT_C1, 1, 4000); " 
					+ " RETURN TEXT_C1; " 
					+ " END ";
			execute(createFunction);
		}
		return queryOfOracle(sql, params);
	}
	// 数据库-MYSQL-创建表
	@Override
	public int executeCreateOfMySQL(String sql) {
		return execute(sql);
	}
	// 数据库-KINGBASE-创建表
	@Override
	public int executeCreateOfKingbase(String sql) {
		return execute(sql);
	}
	// 数据库-ORACLE-创建表
	@Override
	public int executeCreateOfOracle(String sql) {
		return execute(sql);
	}
	// 单条数据执行-无参数
	@Override
	public int execute(String sql) {
		return execute(sql, new Object[] {});
	}
	// 单条数据执行-有参数
	@Override
	public int execute(String sql, Object[] params) {
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
			logger.error("变更(包含增、删、改)操作异常！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("变更(包含增、删、改)回滚异常！");
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			free(stmt);
			free(conn);
		}
		return result;
	}
	// 批量数据执行-无参数
	@Override
	public int executeBatch(String sql) {
		return executeBatch(sql, new ArrayList<Object[]>());
	}
	// 批量数据执行-有参数
	@Override
	public int executeBatch(String sql, List<Object[]> params) {
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
			logger.error("批量变更(包含增、删、改)操作异常！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("批量变更(包含增、删、改)回滚异常！");
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			free(stmt);
			free(conn);
		}
		return result;
	}
	// 释放连接
	@Override
	public void free(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	// 关闭声明
	@Override
	public void free(Statement statement) {
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	// 关闭预置
	@Override
	public void free(PreparedStatement preparedStatement) {
		if (preparedStatement != null)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	// 关闭结果集
	@Override
	public void free(ResultSet resultSet) {
		if (resultSet != null)
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * 关闭所有
	 * @param conn
	 * @param statement
	 * @param preparedStatement
	 * @param resultSet
	 * @return
	 */
	public void freeAll(Connection conn, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet){
		free(conn);
		free(statement);
		free(preparedStatement);
		free(resultSet);
	}
	
	/**
	 * <p>执行结果</p>
	 * @param stmt
	 * @return 结果集
	 * @throws SQLException SQL组装异常
	 */
	private ResultSet createResultSet(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * <p>设置SQL执行</p>
	 * @param conn 连接信息
	 * @param sql 执行语句
	 * @param params 条件
	 * @return 预处理声明
	 */
	private PreparedStatement createPreparedStatement(Connection conn, String sql, Object[] params) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			logger.error("设置预处理声明失败！");
			e.printStackTrace();
		}
		for (int i = 0; i < params.length; i++)
			try {
				stmt.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				logger.error("设置组装条件失败！");
				e.printStackTrace();
			}
		return stmt;
	}
	// 封装mysql的结果集
	@Override
	public List<Map<String, String>> setMapOfMysql(ResultSet resultSet) throws SQLException {
		return JdbcTemplateResultSet.getInstance().setMapOfMysql(resultSet);
	}
	// 封装kingbase的结果集
	@Override
	public List<Map<String, String>> setMapOfKingBase(ResultSet resultSet) throws SQLException {
		return JdbcTemplateResultSet.getInstance().setMapOfKingBase(resultSet);
	}
	// 封装oracle的结果集
	@Override
	public List<Map<String, String>> setMapOfOracle(ResultSet resultSet) throws SQLException {
		return JdbcTemplateResultSet.getInstance().setMapOfOracle(resultSet);
	}
}
