package com.hourz.common.object;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * <p>关于对象的工具类</p>
 * @author hourz
 * @since 2016-06-23
 */
public class ObjectUtils {

	/**
	 * <p>对象是否为基础类型对象</p>
	 * @param obj 需要判断的对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isBaseType(Object obj) {
		if (obj == null) return false;
		Class[] baseTypes = new Class[]{Integer.class, Long.class, Float.class, Double.class, Byte.class, Date.class, String.class, Enum.class,  BigDecimal.class, Timestamp.class};
		for (Class type : baseTypes) {
			if (obj.getClass() == type) return true;
		}
		return false;
	}
	
	
	/**
	 * <p>对一个对象属性进行描述</p>
	 * @param obj 对象
	 * @param splitter 各个属性之间的分隔符号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String description(Object obj, String splitter) {
		try {
			// 获取所有的属性值
			Map fieldMap = BeanUtils.describe(obj);
			Iterator iter = fieldMap.keySet().iterator();
			// 构建描述字符串
			StringBuffer descStr = new StringBuffer();
			while(iter.hasNext()) {
				Object key = iter.next();
				Object value = fieldMap.get(key);
				descStr.append(key+":"+value+splitter);
			}
			// 返回描述字符串
			return descStr.toString();
		} catch(Exception ex) {
			return null;
		}		
	}
}
