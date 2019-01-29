package com.hourz.jdbc.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hourz.common.json.CResult;
import com.hourz.common.status.ResultStatus;
import com.hourz.jdbc.server.service.UserService;
import com.hourz.pojo.User;

/**
 * <p>Description</p>
 * @author hourz
 * @since 2018-11-09
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/list")
	public CResult<User> list(HttpServletRequest request, HttpServletResponse response, 
			int pagesize, int pageNum, @RequestBody User item) {
		return new CResult<User>(true, ResultStatus.OK, null, userService.list(pagesize, pageNum, item), userService.count(item), "获取用户列表数据成功！");
	}
}
