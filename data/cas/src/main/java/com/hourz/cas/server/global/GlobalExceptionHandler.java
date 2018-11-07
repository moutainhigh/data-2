package com.hourz.cas.server.global;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hourz.common.constant.Constant;
import com.hourz.common.json.CResult;

/**
 * <p>整体异常处理</p>
 * @author hourz
 * @since 2018-10-10
 */
public class GlobalExceptionHandler  {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * <p>系统异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public CResult<Exception> exceptionHandle(Exception e){
		logger.error("系统异常{" + e.getMessage() + "}");
		return new CResult<Exception>(false, "500", Constant.SYSTEM_ERROR);
	}
	
	/**
	 * <p>运行时异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public CResult<RuntimeException> handle(RuntimeException e){
		logger.error("运行代码异常{" + e.getMessage() + "}");
		return new CResult<RuntimeException>(false, "504", Constant.RUNTIME_EXCEPTION);
	}
	
	/**
	 * <p>数据库异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public CResult<SQLException> handle(SQLException e){
		logger.error("数据库异常{" + e.getMessage() + "}");
		return new CResult<SQLException>(false, "504", Constant.RUNTIME_EXCEPTION);
	}
	
	/**
	 * <p>端口被占用异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public CResult<BindException> handle(BindException e){
		logger.error("数据库异常{" + e.getMessage() + "}");
		return new CResult<BindException>(false, "504", Constant.RUNTIME_EXCEPTION);
	}
	
	/**
	 * <p>空指针异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException .class)
	@ResponseBody
	public CResult<NullPointerException> handle(NullPointerException e){
		logger.error("数据库异常{" + e.getMessage() + "}");
		return new CResult<NullPointerException>(false, "504", Constant.RUNTIME_EXCEPTION);
	}
	
	/**
	 * <p>算术异常类</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArithmeticException .class)
	@ResponseBody
	public CResult<ArithmeticException> handle(ArithmeticException e){
		logger.error("算数异常{" + e.getMessage() + "}");
		return new CResult<ArithmeticException>(false, "504", Constant.RUNTIME_EXCEPTION);
	}
	
	/**
	 * <p>对象转换异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public CResult<ClassCastException> handle(ClassCastException e){
		logger.error("对象转换异常{" + e.getMessage() + "}");
		return new CResult<ClassCastException>(false, "504", Constant.RUNTIME_EXCEPTION);
	}
	
	/**
	 * <p>文件找不到异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseBody
	public CResult<FileNotFoundException> handle(FileNotFoundException e){
		logger.error("文件找不到异常{" + e.getMessage() + "}");
		return new CResult<FileNotFoundException>(false, "504", Constant.RUNTIME_EXCEPTION); 
	}
	
	/**
	 * <p>数字转换异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public CResult<NumberFormatException> handle(NumberFormatException e){
		logger.error("数字转换异常{" + e.getMessage() + "}");
		return new CResult<NumberFormatException>(false, "504", Constant.RUNTIME_EXCEPTION); 
	}
	
	/**
	 * <p>数组下标越界异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseBody
	public CResult<ArrayIndexOutOfBoundsException> handle(ArrayIndexOutOfBoundsException e){
		logger.error("数组下标越界{" + e.getMessage() + "}");
		return new CResult<ArrayIndexOutOfBoundsException>(false, "504", Constant.RUNTIME_EXCEPTION); 
	}
	
	/**
	 * <p>方法的参数错误</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalAccessException.class)
	@ResponseBody
	public CResult<IllegalAccessException> handle(IllegalAccessException e){
		logger.error("方法的参数错误{" + e.getMessage() + "}");
		return new CResult<IllegalAccessException>(false, "504", Constant.RUNTIME_EXCEPTION); 
	}
	
	/**
	 * <p>没有访问权限</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public CResult<IllegalArgumentException> handle(IllegalArgumentException e){
		logger.error("访问权限异常{" + e.getMessage() + "}");
		return new CResult<IllegalArgumentException>(false, "504", Constant.RUNTIME_EXCEPTION); 
	}
	
	/**
	 * <p>输入输出异常</p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public CResult<IOException> handle(IOException e){
		logger.error("输入输出异常{" + e.getMessage() + "}");
		return new CResult<IOException>(false, "504", Constant.RUNTIME_EXCEPTION); 
	}
	
}
