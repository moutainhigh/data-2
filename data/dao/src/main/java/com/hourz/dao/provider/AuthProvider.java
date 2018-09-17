package com.hourz.dao.provider;

import org.apache.ibatis.jdbc.SQL;

public class AuthProvider {

	public String email() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE(" email=#{loginName} ");
		}}.toString();
	}
		
	public String mobile() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE(" mobile=#{loginName} ");
		}}.toString();
	}
	
	public String name() {
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE(" login_name=#{loginName} ");
		}}.toString();
	}
	
}
