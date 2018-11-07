package com.casic.taskexcute.server.collect.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.casic27.common.entity.ColumnEntity;
import com.casic27.common.entity.DataBase;
import com.casic27.common.entity.TableEntity;
/**
 * jdbc 工具
 * @author:zgp
 * @time:2018-08-03
 */
public class JdbcUtils {
	
	
	public static final String ID_CMP_JH = "id_cpm_jh";//交换库默认id
	public static final String ID_CMP_MB = "id_cpm_mb";//目标库默认id
	public static final String ID_CMP_TS = "id_cpm_ts";//推送库默认id
	public static final String ID_CMP_TYPE = "INT";//默认id字段类型
	public static final int queryStep = 100;
	public static final String ORACLE = "oracle";
	public static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	public static final String ORACLE_DRIVER2 = "oracle.jdbc.driver.OracleDriver";
	public static final String ORACLE_PROTOCOL = "jdbc:oracle:thin:@//";

	public static final String ALI_ORACLE = "AliOracle";
	public static final String ALI_ORACLE_DRIVER = "com.alibaba.jdbc.AlibabaDriver";
	public static final String ALI_ORACLE_PROTOCOL = "";

	public static final String MYSQL = "mysql";
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_DRIVER_6 = "com.mysql.cj.jdbc.Driver";
	public static final String MYSQL_PROTOCOL = "jdbc:mysql://";

	public static final String MARIADB = "mariadb";
	public static final String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";
	public static final String MARIADB_PROTOCOL = "";
	
	public static final String KINGBASE = "kingbase";
	public static final String KINGBASE_DRIVER = "com.kingbase.Driver";
	public static final String KINGBASE_PROTOCOL = "jdbc:kingbase://";

	private Connection conn = null;
	private PreparedStatement statement;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public JdbcUtils() {
		super();
	}

	public JdbcUtils(String url, String username, String password, String dataBase, String dataBaseName)
			throws ClassNotFoundException, SQLException {
		super();
		String protocol = "";
		String type = dataBase.trim().toLowerCase();
		if (ORACLE.equals(type)) {
			protocol = ORACLE_PROTOCOL;
		} else if (ALI_ORACLE.equals(type)) {
			protocol = ALI_ORACLE_PROTOCOL;
		} else if (MYSQL.equals(type)) {
			protocol = MYSQL_PROTOCOL;
		} else if (KINGBASE.equals(type)) {
			protocol = KINGBASE_PROTOCOL;
		}  else if (MARIADB.equals(type)) {
			protocol = MARIADB_PROTOCOL;
		} else {
			throw new RuntimeException("未知数据库类型");
		}
		createDriver();
		this.createConn(protocol + url + "/" + dataBaseName, username, password);
	}
	/**
	 * 新建连接
	 * @param dataBase 数据库信息
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/**
	 * 根据封装对象连接
	 * @param dataBase
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public JdbcUtils(DataBase dataBase) throws ClassNotFoundException, SQLException {
		super();
		String protocol= "";
		String type = dataBase.getDataBaseType().toLowerCase();
		if(ORACLE.equals(type)){
			protocol = ORACLE_PROTOCOL+ dataBase.getUrl() + "/" + dataBase.getDataBaseName();
		} else if(MYSQL.equals(type)) {
			protocol = MYSQL_PROTOCOL+ dataBase.getUrl() + "/" + dataBase.getDataBaseName() + "?useUnicode=true&characterEncoding=utf-8&useSSL=false" ;
		} else if (KINGBASE.equals(type)) {
			protocol = KINGBASE_PROTOCOL+ dataBase.getUrl() + "/" + dataBase.getDataBaseName() ;
		}  else if(MARIADB.equals(type)) {
			protocol = MARIADB_PROTOCOL+ dataBase.getUrl() + "/" + dataBase.getDataBaseName();
		} else {
			throw new RuntimeException("未知数据库类型");
		}
		//protocol = MYSQL_PROTOCOL;
		createDriver();
		this.createConn(protocol, dataBase.getUsername(), dataBase.getPassword());
	}
	
	/**
	 * 测试数据库连接
	 * @param url	连接URL
	 * @param username	用户名
	 * @param password	密码
	 * @param dataBase	数据库类型
	 * @param dataBaseName	数据库名称
	 * @return 连接状态 true为成功;false为失败;
	 * @throws SQLException 关闭异常
	 * @throws ClassNotFoundException 未发现异常
	 */
	public boolean testConn(String url, String username, String password, String dataBase,String dataBaseName) {
		// 
		boolean state = true;
		String protocol= "";
		String type = dataBase.toLowerCase();
		// 根据数据库类型设置连接参数
		if(ORACLE.equals(type)){
			protocol = ORACLE_PROTOCOL;
		}else if(MYSQL.equals(type)){
			protocol = MYSQL_PROTOCOL;
		} else if (KINGBASE.equals(type)) {
			protocol = KINGBASE_PROTOCOL;
		} else if(MARIADB.equals(type)){
			protocol = MARIADB_PROTOCOL;
		}else{
			throw new RuntimeException("未知数据库类型");
		}
		try {
			createDriver();
			// 连接到数据库
			conn = DriverManager.getConnection(protocol + url + "/" + dataBaseName, username, password);
			if(getStatement() !=null)getStatement().close();
			if(getConn() !=null) getConn() .close();
			state = true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// 获取连接异常
			state = false;
		}
		// 返回连接状态
		return state;
	}

	private static volatile Boolean driver = null;
	// 数据库驱动类型
	public static String[] types = { ORACLE_DRIVER, MYSQL_DRIVER, };
	//加载驱动
	public static Boolean createDriver() throws ClassNotFoundException {
		Boolean flash = driver;
		if (flash == null) {
			driver(types);
			flash = new Boolean(true);
			driver = flash;
		}
		return flash;
	}

	public static synchronized void driver(String[] driverTypes) throws ClassNotFoundException {
		for (int i = 0; i < driverTypes.length; i++) {
			String driverType = driverTypes[i];
			Class.forName(driverType);
		}
	}

	public void createConn(String url, String user, String password) throws ClassNotFoundException, SQLException {
		conn = DriverManager.getConnection(url, user, password);
		System.out.println(url+";"+user+";"+password);
		conn.setAutoCommit(false);
	}

	public void createStatement(String sql) throws SQLException {
		closeStatement();
		statement = conn.prepareStatement(sql);
	}
	public void createStatementAutoKey(String sql,int autoGeneratedKeys) throws SQLException {
		closeStatement();
		statement = conn.prepareStatement(sql, autoGeneratedKeys);
	}
	public String getLastKey() throws SQLException{
		ResultSet idSet = statement.getGeneratedKeys();
		idSet.last();
		String lastKey = idSet.getString(1);
		idSet.close();
		return lastKey;
	}
	public void setParameter(List<String> parameters) throws SQLException {
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				statement.setString(i + 1, parameters.get(i));
			}
		}
	}
	/**
	 * 组装目标表
	 * @param tableEntity 采集规范表信息
	 * @param columns 主键/时间标识字段
	 * @param idCmpJhStr 交换库默认id
	 * @param idCmpMbStr 目标库默认id
	 */
	public static void tableMbDetails(TableEntity tableEntity,List<ColumnEntity> columns,
			String idCmpJhStr,String idCmpMbStr){
		List<ColumnEntity> columnEntities = new ArrayList<>();
		if(tableEntity!=null&&tableEntity.getColumnEntities()!=null){
			columnEntities = tableEntity.getColumnEntities();
			ColumnEntity cmpMbId = new ColumnEntity();
			cmpMbId.setNameEng(idCmpMbStr);
			cmpMbId.setType(ID_CMP_TYPE);
			cmpMbId.setLenght(11);;
			cmpMbId.setIsZj(1);
			cmpMbId.setIsAutoIncrement(1);
			cmpMbId.setIsNotNull(1);
			columnEntities.add(cmpMbId);
			ColumnEntity cmpJhId = new ColumnEntity();
			cmpJhId.setNameEng(idCmpJhStr);
			cmpJhId.setType(ID_CMP_TYPE);
			cmpJhId.setLenght(11);
			columnEntities.add(cmpJhId);
		}
		for (ColumnEntity coEntity : columns) {
			String name = coEntity.getNameEng().toLowerCase();
			Iterator<ColumnEntity> iterator = columnEntities.iterator();
			while(iterator.hasNext()){
				ColumnEntity eName = iterator.next();
				if(name.equals(eName.getNameEng().toLowerCase())){
					iterator.remove();
				}else if(eName.getNameEng()!=idCmpJhStr&&eName.getNameEng()!=idCmpMbStr){
					eName.setType("varchar");
				}
				
			}
			ColumnEntity cEntity = new ColumnEntity();
			cEntity.setNameEng(name);
			cEntity.setType("varchar");
			cEntity.setLenght(coEntity.getLenght());;
			columnEntities.add(cEntity);
		}
	}
	/**
	 * 建表
	 * 
	 * @param tableEntity: not null(列：not null)
	 * @return true 已存在  false 建表
	 * @throws SQLException
	 */
	public boolean createTable(DataBase dataBase, TableEntity tableEntity) throws SQLException {
		boolean b = false;
		if (MYSQL.equals(dataBase.getDataBaseType().toLowerCase())) {
			b = createTableMysql(tableEntity);
		}
		return b;
	}
	
	/**
	 * myslq 建表
	 * 
	 * @param tableEntity: not null(列：not null)
	 * @return true 已存在 false 建表
	 * @throws SQLException
	 */
	public boolean createTableMysql(TableEntity tableEntity) throws SQLException {
		boolean b = false;
		StringBuffer queryTable =new StringBuffer().append("show tables like '").append(tableEntity.getNameEng()).append("'");
		createStatement(queryTable.toString());
		statement.executeQuery();
		ResultSet tableSet = statement.getResultSet();
		if (tableSet.next())
			return true;
		closeStatement();
		StringBuffer creatTable = new StringBuffer().append("create table ").append(tableEntity.getNameEng()).append("(");
		for (int i = 0; i < tableEntity.getColumnEntities().size(); i++) {
			ColumnEntity columnEntity = tableEntity.getColumnEntities().get(i);
			String type = columnEntity.getType();
			creatTable.append(" ").append(columnEntity.getNameEng()).append(" ").append(type);
			if (columnEntity.getLenght() != null) {
				creatTable.append(" (").append(columnEntity.getLenght()).append(")");
			}
			if(1==columnEntity.getIsNotNull().intValue()){
				creatTable.append(" not null");
			}
			if(1==columnEntity.getIsZj().intValue()){
				creatTable.append(" primary key");
				if(1==columnEntity.getIsAutoIncrement().intValue()){
					creatTable.append(" auto_increment");
				}
			}
			if (i == (tableEntity.getColumnEntities().size() - 1)) {
				creatTable.append(")");
			} else {
				creatTable.append(",");
			}
		}
		createStatement(creatTable.toString());
		statement.executeUpdate();
		commitConn();
		b = true;
		return b;
	}

	/**
	 * 数据集转map
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<Map<String, String>> setTranMap(ResultSet resultSet)
			throws SQLException, ClassNotFoundException {
		ResultSetMetaData setMetaData = resultSet.getMetaData();
		List<Map<String, String>> resultList = new ArrayList<>();
		while (resultSet.next()) {
			Map<String, String> result = new HashMap<String, String>();
			for (int i = 1; i <= setMetaData.getColumnCount(); i++) {
				String columnName = setMetaData.getColumnName(i).toLowerCase();
				String str = resultSet.getString(i);

				result.put(columnName, str);
			}
			resultList.add(result);
		}
		return resultList;
	}

	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public void commitConn() throws SQLException {
		if (conn != null) {
			conn.commit();
		}
	}

	public void closeStatement() throws SQLException {
		if (statement != null) {
			statement.close();
		}
	}

	public static void main(String[] args) throws Exception {
		createDriver();
		JdbcUtils jdbcUtils = new JdbcUtils(getExchangeDataBase());
		//jdbcUtils.createConn("jdbc:mysql://192.168.1.222:3306/dataintegration", "zhougp", "123456");
		jdbcUtils.createStatement("select * from s2_code_hospital");
		ResultSet resultSet = jdbcUtils.getStatement().executeQuery();
		List<Map<String, String>> resultList = JdbcUtils.setTranMap(resultSet);
		resultSet.close();
		jdbcUtils.closeStatement();
		jdbcUtils.closeConn();
		System.out.println(JSONObject.toJSONString(resultList));
		Map<String, String> map = new HashMap<>();
		System.out.println(map.get("key"));
	}
	/**
	 * 交换库信息
	 */
	public static DataBase getExchangeDataBase() {
		return new DataBase(PropertyUtils.getPro("DATABASE.EXCHANGE.URL"),
				PropertyUtils.getPro("DATABASE.EXCHANGE.USERNAME"), PropertyUtils.getPro("DATABASE.EXCHANGE.PASSWORD"),
				PropertyUtils.getPro("DATABASE.EXCHANGE.TYPE"), PropertyUtils.getPro("DATABASE.EXCHANGE.NAME"));
	}
    /**
     * 目标库信息
     * @return
     */
	public static DataBase getTargetDataBase() {
		return new DataBase(PropertyUtils.getPro("DATABASE.TARGET.URL"), PropertyUtils.getPro("DATABASE.TARGET.USERNAME"),
				PropertyUtils.getPro("DATABASE.TARGET.PASSWORD"), PropertyUtils.getPro("DATABASE.TARGET.TYPE"),
				PropertyUtils.getPro("DATABASE.TARGET.NAME"));

	}
}
