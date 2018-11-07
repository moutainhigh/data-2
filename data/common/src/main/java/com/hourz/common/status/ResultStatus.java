package com.hourz.common.status;

/**
 * <p>结果代码</p>
 * @author hourz
 * @since 2018-11-07
 */
public class ResultStatus {
	
	// 1xx Informational
	public static final String CONTINUE = "100";						// 继续
	public static final String SWITCHING_PROTOCOLS = "101";				// 切换协议
	public static final String PROCESSING = "102";						// 处理
	public static final String CHECKPOINT= "103";						// 检查节点
	
	// 2xx Success
	public static final String OK = "200";								// 成功
	public static final String CREATED = "201";							// 创建
	public static final String ACCEPTED = "202";						// 接收
	public static final String NON_AUTHORITATIVE_INFORMATION = "203";	// 未经授权信息
	public static final String NO_CONTENT = "204";						// 无内容
	public static final String RESET_CONTENT = "205";					// 内容重置
	public static final String PARTIAL_CONTENT = "206";					// 局部内容
	public static final String MULTI_STATUS = "207";					// 多状态
	public static final String ALREADY_REPORTED = "208";				// 已报告
	
	public static final String IM_USED = "226";							// 已占用
	
	// 3xx Redirection
    public static final String MULTIPLE_CHOICES = "300";				// 选择适配
    public static final String MOVED_PERMANENTLY = "301";				// 永久移动
    public static final String MOVED_TEMPORARILY = "302";				// 临时移动
    public static final String SEE_OTHER = "303";						// 查询其它
    public static final String NOT_MODIFIED = "304";					// 未修改
    public static final String USE_PROXY = "305";						// 过去使用中
    public static final String TEMPORARY_REDIRECT = "307";				// 临时重定向
    public static final String PERMANENT_REDIRECT = "308";				// 永久重定向
	
    // 4xx Client Error
    public static final String BAD_REQUEST = "400";						// 请求错误
    public static final String UNAUTHORIZED = "401";					// 非法操作
    public static final String PAYMENT_REQUIRED = "402";				// 信息不完整
    public static final String FORBIDDEN = "403";						// 禁止操作
    public static final String NOT_FOUND = "404";						// 未定义
    public static final String METHOD_NOT_ALLOWED = "405";				// 方法不允许使用
    public static final String NOT_ACCEPTABLE = "406";					// 无法接受
    public static final String PROXY_AUTHENTICATION_REQUIRED = "407";	// 代理验证异常
    public static final String REQUEST_TIMEOUT = "408";					// 请求超时
    public static final String CONFLICT = "409";						// 请求冲突
    public static final String GONE = "410";							// 请求已不适用
    public static final String LENGTH_REQUIRED = "411";					// 长度不符
    public static final String PRECONDITION_FAILED = "412";				// 前提条件验证失败
    public static final String REQUEST_TOO_LONG = "413";				// 请求过长
    public static final String REQUEST_ENTITY_TOO_LARGE ="413";			// 请求实体过长(已过期)
    public static final String URI_TOO_LONG = "414";					// 请求URL过长
    public static final String REQUEST_URI_TOO_LONG = "414";			// 请求URL过长(已过期)
    public static final String UNSUPPORTED_MEDIA_TYPE = "415";			// 不支持媒体类型
    public static final String REQUESTED_RANGE_NOT_SATISFIABLE = "416";	// 请求范围不符合越界
    public static final String EXPECTATION_FAILED = "417";				// 预期结果错误
    public static final String I_AM_A_TEAPOT = "418";					// 协议错误
    public static final String INSUFFICIENT_SPACE_ON_RESOURCE = "419";	// 资源空间不足(已过期)
    public static final String METHOD_FAILURE = "420";					// 方法错误(已过期)
    public static final String DESTINATION_LOCKED = "421";				// 目标锁定
    public static final String UNPROCESSABLE_ENTITY = "422";			// 错误实体
    public static final String LOCKED = "423";							// 已上锁
    public static final String FAILED_DEPENDENCY = "424";				// 错误依赖
    
    public static final String UPGRADE_REQUIRED = "426";				// 需求更新
    
    public static final String PRECONDITION_REQUIRED = "428";			// 先决条件不符
    public static final String TOO_MANY_REQUESTS = "429";				// 请求数量过多
    
    public static final String REQUEST_HEADER_FIELDS_TOO_LARGE = "431";	// 请求头字段过大
    
    public static final String UNAVAILABLE_FOR_LEGAL_REASONS = "451";	// 非法请求
        
    // 5xx Server Error
    public static final String INTERNAL_SERVER_ERROR = "500";			// 网络异常
    public static final String NOT_IMPLEMENTED = "501";					// 没有实现方法
    public static final String BAD_GATEWAY = "502";						// 网关错误
    public static final String SERVICE_UNAVAILABLE = "503";				// 服务无效
    public static final String GATEWAY_TIMEOUT = "504";					// 网关超时
    public static final String HTTP_VERSION_NOT_SUPPORTED = "505";		// 请求版本不支持
    public static final String INSUFFICIENT_STORAGE = "507";			// 临时存储不足
    public static final String LOOP_DETECTED = "508";					// 陷入循环
    public static final String BANDWIDTH_LIMIT_EXCEEDED = "509";		// 超出限制
    public static final String NOT_EXTENDED = "510";					// 扩展错误
    public static final String NETWORK_AUTHENTICATION_REQUIRED = "511";	// 网络认证错误
}
