package com.hourz.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.hourz.pojo.User;

public class UserProvider {

	public String list(Map<String, Object> item) {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			if(item.get("loginName") != null && item.get("loginName") != "") {
				WHERE("login_name=#{loginName}");
			}
			if(item.get("username") != null && item.get("username") != "") {
				WHERE("username=#{username}");
			}
			if(item.get("deptId") != null && item.get("deptId") != "") {
				WHERE("dept_id=#{deptId}");
			}
			if(item.get("mobile") != null && item.get("mobile") != "") {
				WHERE("mobile=#{mobile}");
			}
			if(item.get("mail") != null && item.get("mail") != "") {
				WHERE("mail=#{mail}");
			}
			ORDER_BY("create_time desc");
		}}.toString();
	}
	
	public String save(User user) {
		return new SQL(){{
			INSERT_INTO("t_user");
			INTO_COLUMNS("login_name", "username", "password", 
					"dept_id", "mobile", "mail", 
					"create_time", "operate_user_id", "status");
            INTO_VALUES("#{loginName}", "#{username}", "#{password}", 
            		"#{deptId}", "#{mobile}", "#{mail}", 
            		"#{createTime}", "#{operateUserId}", "#{status}");
		}}.toString();
	}
	
	public String update(User user) {
		return new SQL(){{
			UPDATE("t_user");
			if(user.getLoginName() != null && user.getLoginName() != "") {
				SET("");
			}
			WHERE("id=#{id}");
		}}.toString();
	}
	
	public String remove(String ids) {
		StringBuilder sb = new StringBuilder("delete t_user where id in (");
        for (String id: ids.split(",")) {
            sb.append("\"").append(id).append("\",");
        }
        int index = sb.lastIndexOf(",");
        sb.replace(index, index+1, ")");
        return sb.toString();
	}
}
