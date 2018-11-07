package com.hourz.cas.server.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hourz.pojo.User;
/**
 * <p>用户接口</p>
 * @author hourz
 * @since 2018-09-14
 */
public interface UserService {

	PageInfo<User> list(int pagesize, int pageNum, Map<String, Object> item);

}
