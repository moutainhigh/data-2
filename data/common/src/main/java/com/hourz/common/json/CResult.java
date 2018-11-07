package com.hourz.common.json;

import java.util.List;

/**
 * <p>结果包装类</p>
 * @author hourz
 * @since 2016-05-26
 */
public class CResult<T> {
	// 是否执行成功
	private boolean success;
	// 结果代码
	private String code;
	// 结果对象
	private T result_obj;
	// 结果列表
	private List<T> result_list;
	// 结果总量(当使用结果列表时，会使用此字段)
	private Long result_total;
	// 执行信息
	private String message;
	
	public CResult() {
		super();
	}
	
	/**
	 * 
	 * @param success
	 */
	public CResult(boolean success) {
		super();
		this.success = success;
	}
	
	/**
	 * 连接成功
	 * @param success
	 * @param code
	 * @param result_obj
	 * @param result_list
	 * @param result_total
	 * @param message
	 */
	public CResult(boolean success, String code, T result_obj, List<T> result_list, Long result_total, String message) {
		super();
		this.success = success;
		this.code = code;
		this.result_obj = result_obj;
		this.result_list = result_list;
		this.result_total = result_total;
		this.message = message;
	}
	
	public CResult(boolean success, String code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		String strText = "\r\n";
		strText += "- 执行："+ success +"\r\n";
		strText += "- 结果代码："+ code + "\r\n";
		strText += "- 消息：" + message;
		return strText;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getResult_obj() {
		return result_obj;
	}
	public void setResult_obj(T result_obj) {
		this.result_obj = result_obj;
	}
	public List<T> getResult_list() {
		return result_list;
	}
	public void setResult_list(List<T> result_list) {
		this.result_list = result_list;
	}
	public Long getResult_total() {
		return result_total;
	}
	public void setResult_total(Long result_total) {
		this.result_total = result_total;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
