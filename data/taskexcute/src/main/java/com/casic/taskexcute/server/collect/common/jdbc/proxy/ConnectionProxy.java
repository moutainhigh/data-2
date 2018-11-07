package com.casic.taskexcute.server.collect.common.jdbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;

/**
 * <p> 设置连接代理 </p>
 * @author hourz
 * @since 2018-10-24
 */
public class ConnectionProxy implements InvocationHandler {

	private Object obj;
	
	private LinkedList<Connection> pool = new LinkedList<Connection>();

	private ConnectionProxy(Object obj, LinkedList<Connection> pool) {
		this.obj = obj;
		this.pool = pool;
	}
	/**
	 * 
	 * @param o
	 * @param pool
	 * @return
	 */
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
