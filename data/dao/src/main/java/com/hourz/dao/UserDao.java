package com.hourz.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.github.pagehelper.PageInfo;
import com.hourz.dao.provider.UserProvider;
import com.hourz.pojo.User;

@Mapper
public interface UserDao {

	@SelectProvider(method = "list", type = UserProvider.class)
	PageInfo<User> list(Map<String, Object> item);
	
	@SelectProvider(method = "save", type = UserProvider.class)
	Long save(User user);
	
	@SelectProvider(method = "update", type = UserProvider.class)
	Long update(User user);
	
	@SelectProvider(method = "remove", type = UserProvider.class)
	Long remove(String ids);
}
