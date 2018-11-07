package com.casic.taskexcute.server.collect.common.jdbc.create;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.casic.taskexcute.server.collect.common.jdbc.column.ColumnModel;

/**
 * <p>创建表SQL组装</p>
 * @author hourz
 * @since 2018-10-24
 */
public class JdbcCreateTable {

	protected Logger logger = LoggerFactory.getLogger(JdbcCreateTable.class);

	private static JdbcCreateTable singleton;
	/**
	 * <p>无参构造器</p>
	 */
	private JdbcCreateTable(){
	}
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static JdbcCreateTable getInstance(){
		if(singleton == null){
			synchronized(JdbcCreateTable.class){
				if(singleton == null){
					singleton = new JdbcCreateTable();
				}
			}
		}
		return singleton;
	}
	/**
	 * 创建MYSQL数据库表执行SQL组装-交换库
	 * @param Dbname 表名
	 * @param params 列项
	 * @return 创建表SQL
	 */
	public String createTableofMysqlDBOfExchange(String Dbname, List<ColumnModel> params) {
		StringBuffer createSQL = new StringBuffer();
		createSQL.append(" create table " + Dbname + " ( ");
		createSQL.append(" id_cpm_jh " +" int(11) NOT NULL AUTO_INCREMENT, ");
		for(int i=0;i<=params.size()-1; i++){
			if(Long.parseLong(params.get(i).columnLength.toString())==0) {
				createSQL.append(params.get(i).getColumnName() +" "+params.get(i).getColumnType()+"("+ 255 +") ,");
			}else {
				createSQL.append(params.get(i).getColumnName() +" "+params.get(i).getColumnType()+"("+ params.get(i).getColumnLength() +") ,");
			}
		}
		createSQL.append( " primary key (id_cpm_jh)");
		createSQL.append(" ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8");
		return createSQL.toString();
	}
	
	/**
	 * 创建KINGBASE数据库表执行SQL组装-交换库
	 * @param Dbname 表名
	 * @param params 列项
	 * @return 创建表SQL
	 */
	public String createTableofKingBaseDBOfExchange(String Dbname, List<ColumnModel> params) {
		StringBuffer createSQL = new StringBuffer();
		createSQL.append(" create table " + Dbname + " ( ");
		createSQL.append(" ID_CPM_JH " +" INTEGER IDENTITY (1,1), ");
		for(int i=0;i<=params.size()-1; i++){
			if(Long.parseLong(params.get(i).columnLength.toString())==0) {
				createSQL.append(params.get(i).getColumnName() +" "+params.get(i).getColumnType()+"("+ 255 +") ,");
			}else {
				createSQL.append(params.get(i).getColumnName() +" "+params.get(i).getColumnType()+"("+ params.get(i).getColumnLength() +") ,");
			}
		}
		createSQL.append(" PRIMARY KEY (ID_CPM_JH) ");
		createSQL.append(" )");
		return createSQL.toString();
	}
	
	/**
	 * 创建MYSQL数据库表执行SQL组装
	 * @param Dbname 表名
	 * @param params 列项
	 * @return 创建表SQL
	 */
	public String createTableofMysqlDB(String Dbname, List<ColumnModel> params) {
		StringBuffer createSQL = new StringBuffer();
		createSQL.append(" create table " + Dbname + " ( ");
		String keySQL = null;
		createSQL.append(" id_cpm_jh " +" int(11) NOT NULL AUTO_INCREMENT, ");
		for(int i=0;i<=params.size()-1; i++){
			if(Long.parseLong(params.get(i).columnLength.toString())==0) {
				createSQL.append(params.get(i).getColumnName() +" "+ params.get(i).getColumnType()+"("+ 255 +")");
			}else {
				createSQL.append(params.get(i).getColumnName() +" "+ params.get(i).getColumnType()+"("+ params.get(i).getColumnLength() +")");
			}
			if(params.get(i).getColumnKey() != null) {
				if(params.get(i).columnAutoIncrement != null) {
					createSQL.append(" NOT NULL AUTO_INCREMENT,");
				} else {
					createSQL.append(" ,");
				}
				keySQL = " primary key ("+params.get(i).getColumnName()+")";
			}
		}
		createSQL.append(keySQL);
		createSQL.append(" ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ");
		return createSQL.toString();
	}
	
	/**
	 * 创建KINGBASE数据库表执行SQL组装-交换库
	 * @param Dbname 表名
	 * @param params 列项
	 * @return 创建表SQL
	 */
	public String createTableofKingBaseDB(String Dbname, List<ColumnModel> params) {
		StringBuffer createSQL = new StringBuffer();
		createSQL.append(" create table " + Dbname + " ( ");
		String keySQL = null;
		for(int i=0;i<=params.size()-1; i++){
			if(Long.parseLong(params.get(i).columnLength.toString())==0) {
				createSQL.append(params.get(i).getColumnName() +" "+ params.get(i).getColumnType()+"("+ 255 +")");
			}else {
				createSQL.append(params.get(i).getColumnName() +" "+ params.get(i).getColumnType()+"("+ params.get(i).getColumnLength() +")");
			}
			if(params.get(i).getColumnKey() != null) {
				if(params.get(i).columnAutoIncrement != null) {
					createSQL.append(" INTEGER IDENTITY (1,1),");
				} else {
					createSQL.append(" ,");
				}
				keySQL = " primary key ("+params.get(i).getColumnName()+") ";
			}
		}
		createSQL.append(keySQL);
		createSQL.append(" )");
		return createSQL.toString();
	}
	/**
	 * 创建ORACLE数据库表执行SQL组装-交换库(暂时不支持)
	 * @param Dbname 表名
	 * @param params 列项
	 * @return 创建表SQL
	 */
	public String createTableofOracleDB(String Dbname, List<ColumnModel> params) {
		StringBuffer createSQL = new StringBuffer();
		createSQL.append("");
		createSQL.append("");
		createSQL.append("");
		return createSQL.toString();
	}
}
