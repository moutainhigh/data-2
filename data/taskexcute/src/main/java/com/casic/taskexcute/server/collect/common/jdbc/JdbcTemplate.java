package com.casic.taskexcute.server.collect.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

import com.casic.taskexcute.server.collect.common.jdbc.proxy.ConnectionProxy;
import com.casic.taskexcute.server.collect.common.jdbc.service.JdbcTemplateService;

/**
 * <p>Jdbc连接模板</p>
 * @author hourz
 * @since 2018-10-24
 */
public class JdbcTemplate {
	
	// 设置连接池大小
	private int poolSize = 5;
	
	private LinkedList<Connection> pool = new LinkedList<Connection>();
	
	public JdbcTemplate(String driver, String url, String name, String pwd, int poolSize) {
		try {
			// 设置驱动
			Class.forName(driver);
			// 
			this.poolSize = poolSize;
			// 判断连接池是否够用
			if(poolSize < 5) {
				if (poolSize <= 0) {
					throw new RuntimeException("初始化池大小失败: " + poolSize);
				}
				for (int i = 0; i < poolSize; i++) {
					Connection con = DriverManager.getConnection(url, name, pwd);
					con = ConnectionProxy.getProxy(con, pool);// 获取被代理的对象
					pool.add(con);// 添加被代理的对象
				}
			} else {
				// 增加连接池大小
				poolSize++;
				if (poolSize <= 0) {
					throw new RuntimeException("初始化池大小失败: " + poolSize);
				}
				for (int i = 0; i < poolSize; i++) {
					// 连接数据库
					Connection con = DriverManager.getConnection(url, name, pwd);
					// 获取被代理的对象
					con = ConnectionProxy.getProxy(con, pool);
					// 添加被代理的对象
					pool.add(con);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public JdbcTemplate(String driver, String url, String name, String pwd) {
		try {
			// 设置驱动
			Class.forName(driver);
			// 判断连接池是否够用
			if (poolSize <= 0) {
				throw new RuntimeException("初始化池大小失败: " + poolSize);
			}
			for (int i = 0; i < poolSize; i++) {
				Connection con = DriverManager.getConnection(url, name, pwd);
				con = ConnectionProxy.getProxy(con, pool);// 获取被代理的对象
				pool.add(con);// 添加被代理的对象
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Autowired
	JdbcTemplateService jdbcTemplateService;
	
	
	
	
	
	public static void main(String[] args) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(null, null, null, null);
		
	}
	
}
