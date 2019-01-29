package com.hourz.jdbc.server.service;

import java.util.List;

import com.hourz.pojo.User;

public interface UserService {

	List<User> list(int pagesize, int pageNum, User item);

	Long count(User item);
}
