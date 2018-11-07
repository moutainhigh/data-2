package com.hourz.cas.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hourz.cas.server.service.UserService;
import com.hourz.common.json.CResult;
import com.hourz.common.status.ResultStatus;
import com.hourz.pojo.User;

/**
 * 用户API层
 * @author hourz
 * @since 2018-08-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * 用户列表
	 * @param request 
	 * @param response
	 * @param pagesize
	 * @param pageNum
	 * @param item
	 * @return 用户列表信息
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ExceptionHandler(value = Exception.class)
	public CResult<User> list(HttpServletRequest request, HttpServletResponse response, 
			int pagesize, int pageNum, @RequestBody Map<String, Object> item) {
		PageInfo<User> page;

		page = userService.list(pagesize, pageNum, item);
		return new CResult<User>(true, ResultStatus.OK, null, page.getList(), page.getTotal(), "获取用户列表数据成功！");
	}

	
}
