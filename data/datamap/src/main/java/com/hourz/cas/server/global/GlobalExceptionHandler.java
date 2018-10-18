package com.hourz.cas.server.global;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>整体异常处理</p>
 * @author hourz
 * @since 2018-10-10
 */
public class GlobalExceptionHandler  {

	/**
	 * <p>普通异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity exceptionHandle(Exception e){ 
		// TODO处理方法参数的异常类型
		return null;
	}
	
	/**
	 * <p>运行时异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity handle(RuntimeException e){
		// TODO处理方法参数的异常类型
		return null;
	}
	
	
	/**
	 * <p>数据库异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public ResponseEntity handle(SQLException e){
		// TODO处理方法参数的异常类型
		return null; 
	}
	
	/**
	 * <p>端口被占用异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseEntity handle(BindException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>空指针异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException .class)
	@ResponseBody
	public ResponseEntity handle(NullPointerException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>算术异常类</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArithmeticException .class)
	@ResponseBody
	public ResponseEntity handle(ArithmeticException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>对象转换异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public ResponseEntity handle(ClassCastException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>文件找不到异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseBody
	public ResponseEntity handle(FileNotFoundException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>字符串转换为数字异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public ResponseEntity handle(NumberFormatException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>数组下标越界异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseBody
	public ResponseEntity handle(ArrayIndexOutOfBoundsException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>方法的参数错误</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalAccessException.class)
	@ResponseBody
	public ResponseEntity handle(IllegalAccessException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>没有访问权限</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity handle(IllegalArgumentException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
	/**
	 * <p>输入输出异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public ResponseEntity handle(IOException e){
		// TODO处理方法参数的异常类型 
		return null; 
	}
	
}
