package com.hourz.cas.server.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hourz.cas.server.service.UserService;
import com.hourz.dao.UserDao;
import com.hourz.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public PageInfo<User> list(int pagesize, int pageNum, Map<String, Object> item) {
		PageHelper.startPage(pageNum, pagesize);
		PageInfo<User> page = userDao.list(item);
		return page;
	}

}
