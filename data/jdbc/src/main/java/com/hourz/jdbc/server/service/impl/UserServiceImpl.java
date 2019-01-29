package com.hourz.jdbc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hourz.jdbc.server.service.UserService;
import com.hourz.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	
	@Override
	public List<User> list(int pagesize, int pageNum, User item) {
		StringBuffer querySql = new StringBuffer();
		querySql.append("");
		List<User> list = jdbcTemplate.query(querySql.toString(), new BeanPropertyRowMapper<User>(User.class));
		return list;
	}


	@Override
	public Long count(User item) {
		StringBuffer querySql = new StringBuffer();
		querySql.append("");
		//jdbcTemplate.setDataSource(dataSource);
		jdbcTemplate.execute(querySql.toString());
		return 0L;
	}
	
}
