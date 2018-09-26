package com.hourz.common.jdbc.data;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.hourz.common.jdbc.row.RowMapper;
import com.hourz.common.jdbc.service.impl.JdbcServiceImpl;


/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-21
 */
public class JdbcDataSource implements DataSource {
	
	private int poolSize = 5;
	 
	private LinkedList<Connection> pool = new LinkedList<Connection>();
 
	public JdbcDataSource(String driver, String url, String name, String pwd) {
		this(driver, url, name, pwd, 5);
	}
 
	public JdbcDataSource(String driver, String url) {
		this(driver, url, "", "", 5);
	}
 
	public JdbcDataSource(String driver, String url, String name, String pwd, int poolSize) {
		try {
			Class.forName(driver);
			this.poolSize = poolSize;
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
 
	/** 获取池大小 */
	public int getPoolSize() {
		return poolSize;
 
	}
 
	/** 不支持日志操作 */
	public PrintWriter getLogWriter() throws SQLException {
		throw new RuntimeException("Unsupport Operation.");
	}
 
	public void setLogWriter(PrintWriter out) throws SQLException {
		throw new RuntimeException("Unsupport operation.");
	}
 
	/** 不支持超时操作 */
	public void setLoginTimeout(int seconds) throws SQLException {
		throw new RuntimeException("Unsupport operation.");
	}
 
	public int getLoginTimeout() throws SQLException {
		return 0;
	}
 
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return (T) this;
	}
 
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return DataSource.class.equals(iface);
	}
 
	/** 从池中取一个连接对象,使用了同步和线程调度 */
	public Connection getConnection() throws SQLException {
		synchronized (pool) {
			if (pool.size() == 0) {
				try {
					pool.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				return getConnection();
			} else {
				return pool.removeFirst();
			}
		}
	}
 
	public Connection getConnection(String username, String password) throws SQLException {
		throw new RuntimeException("不支持接收用户名和密码的操作");
	}
 
	/** 实现对Connection的动态代理 */
	static class ConnectionProxy implements InvocationHandler {
 
		private Object obj;
		private LinkedList<Connection> pool;
 
		private ConnectionProxy(Object obj, LinkedList<Connection> pool) {
			this.obj = obj;
			this.pool = pool;
		}
 
		public static Connection getProxy(Object o, LinkedList<Connection> pool) {
			Object proxed = Proxy.newProxyInstance(o.getClass().getClassLoader(), new Class[] { Connection.class },
					new ConnectionProxy(o, pool));
			return (Connection) proxed;
		}
 
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName().equals("close")) {
				synchronized (pool) {
					pool.add((Connection) proxy);
					pool.notify();
				}
				return null;
			} else {
				return method.invoke(obj, args);
			}
		}
 
	}

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#getParentLogger()
	 */
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	

	public static void main(String[] args) throws SQLException {
		JdbcDataSource dataSource = new JdbcDataSource(
				"com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.40:3306/cas", "root", "123456");
		JdbcServiceImpl jdbc = new JdbcServiceImpl(dataSource);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) jdbc.queryForBean("select * from t_user", new RowMapper<User>() {
			User user = null;
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				return user;
			}
		});
		for (User user : list) {
			System.out.println(user.getId() + "---" + user.getUsername());
		}

	}
}
