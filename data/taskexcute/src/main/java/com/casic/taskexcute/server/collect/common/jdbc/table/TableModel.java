package com.casic.taskexcute.server.collect.common.jdbc.table;

/**
 * <p> 表信息新增 </p>
 * @author hourz
 * @since 2018-10-28
 */
public class TableModel {

	// 表名
	private String tableName;
	// 表别名
	private String tableComment;
	// 是否创建表
	public Integer isCreate;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public Integer getIsCreate() {
		return isCreate;
	}
	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}
}
