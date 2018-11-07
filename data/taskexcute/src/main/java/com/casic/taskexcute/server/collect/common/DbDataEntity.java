package com.casic.taskexcute.server.collect.common;

public class DbDataEntity {
	
	private String url;		// 数据库连接URL
	private String yh;		// 用户
	private String mm;		// 密码
	private String sjklx;	// 数据库类型
	private String sjkmc;	// 数据库名称
	private String tableName;	// 表名
	private Integer isCreate;	// 是否创建表
	private Object column;		// 字段信息
	
	/**
	 * 无参构造器
	 */
	public DbDataEntity(){
		super();
	}
	
	/**
	 * 有参构造器
	 * @param url 数据库连接URL
	 * @param yh 用户
	 * @param mm 密码
	 * @param sjklx 数据库类型
	 * @param sjkmc 数据库名称
	 * @param tableName 表名
	 * @param column 字段信息
	 */
	public DbDataEntity(String url, String yh, String mm, String sjklx, String sjkmc, String tableName, Integer isCreate, Object column){
		super();
		this.url = url;
		this.yh = yh;
		this.mm = mm;
		this.sjklx = sjklx;
		this.sjkmc = sjkmc;
		this.tableName = tableName;
		this.isCreate = isCreate;
		this.column = column;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getYh() {
		return yh;
	}
	public void setYh(String yh) {
		this.yh = yh;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getSjklx() {
		return sjklx;
	}
	public void setSjklx(String sjklx) {
		this.sjklx = sjklx;
	}
	public String getSjkmc() {
		return sjkmc;
	}
	public void setSjkmc(String sjkmc) {
		this.sjkmc = sjkmc;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Object getColumn() {
		return column;
	}
	public void setColumn(Object column) {
		this.column = column;
	}
	public Integer getIsCreate() {
		return isCreate;
	}
	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}
}
