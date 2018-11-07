package com.casic.taskexcute.server.collect.common.jdbc.column;

public class ColumnModel {
	// 列名
	public String columnName;
	// 列类型
	public String columnType;
	// 列长度
	public String columnLength;
	// 列主键
	public String columnKey;
	// 自增
	public String columnAutoIncrement;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnLength() {
		return columnLength;
	}
	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getColumnAutoIncrement() {
		return columnAutoIncrement;
	}
	public void setColumnAutoIncrement(String columnAutoIncrement) {
		this.columnAutoIncrement = columnAutoIncrement;
	}
}
