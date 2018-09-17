package com.hourz.tmodel;

import java.util.Date;

/**
 * 用户对象
 * @author hourz
 * @since 2018-09-01
 */
public class T_User {

	// 主键
	private Long id;
	// 登录名
	private String loginName;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 手机号
	private String mobile;
	// 单位ID
	private String deptId;
	// 单位名称
	private String deptName;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;
	// 删除时间
	private Date removeTime;
	// 操作人ID
	private Long operateUserID;
	// 操作人
	private String operateUser;
	// 备注
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}
	public Long getOperateUserID() {
		return operateUserID;
	}
	public void setOperateUserID(Long operateUserID) {
		this.operateUserID = operateUserID;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
