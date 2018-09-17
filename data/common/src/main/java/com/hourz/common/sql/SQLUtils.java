package com.hourz.common.sql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>SQL语句工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class SQLUtils {
	
	/**
	 * <p>生成排序SQL</p>
	 * @param domainClassName 域类名
	 * @param sortFields 需要排序的字段数组(例如：['name', ...])
	 * @param sortOrders sortFields指定的字段的排序方式数组(例如['DESC', 'ASC', ...])
	 * @return
	 */
	public static String createOrderBySql(String domainClassName, String[] sortFields, String[] sortOrders){
    	// 固定的SQL部分
    	StringBuffer sql = new StringBuffer("SELECT dn FROM ");
    	sql.append(domainClassName);
    	sql.append(" dn");
    	// 根据提供的字段和排序方式补全SQL
    	if (sortFields != null && sortOrders != null && sortFields.length == sortOrders.length && sortFields.length>0) {
    		sql.append(" ORDER BY ");
    		for (int i=0; i<sortFields.length; i++) {
    			sql.append("dn.");
    			sql.append(sortFields[i]);
    			sql.append(" ");
    			sql.append(sortOrders[i]);
    			if (i == sortFields.length)
    				sql.append(" ");
    			else
    				sql.append(",");
    		}
    	}
    	return sql.toString();
	}
	
	/**
	 * <p>为SQL语句补全排序条件</p>
	 * @param sql sql语句
	 * @param alias 域对象的别名
	 * @param sortFields 需要排序的字段数组(例如：['name', ...])
	 * @param sortOrders sortFields指定的字段的排序方式数组(例如['DESC', 'ASC', ...])
	 * @return
	 */
	public static String patchOrderBySql(String sql, String alias, String[] sortFields, String[] sortOrders){
		StringBuffer sbSql = new StringBuffer(sql);
    	// 根据提供的字段和排序方式补全SQL
    	if (sortFields != null && sortOrders != null && sortFields.length == sortOrders.length && sortFields.length>0) {
    		sbSql.append(" ORDER BY ");
    		for (int i=0; i<sortFields.length; i++) {
    			if (alias != null && !alias.trim().equals("")) {
	    			sbSql.append(alias);
	    			sbSql.append(".");
    			}
    			sbSql.append(sortFields[i]);
    			sbSql.append(" ");
    			sbSql.append(sortOrders[i]);
    			if (i == sortFields.length-1)
    				sbSql.append(" ");
    			else
    				sbSql.append(",");
    		}
    	}
    	return sbSql.toString();
	}
	
	/**
	 * <p>为Sql补充时间区间条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段(使用JPQL形式)
	 * @param from 起始时间
	 * @param to 结束时间
	 * @return
	 */
	public static String patchDateBySql(String sql, String objField, Date from, Date to){
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
		// 判断from和to是否为null
		if (from==null && to==null) return sql;
		// 日期条件
		StringBuffer dateCondition = new StringBuffer();
		if (from!=null && to==null) { // 起始日期不为null, 结束日期为null
			dateCondition.append(objField).append(">='").append(dateFormat.format(from)).append("'");
		} else if(from==null && to!=null) { // 起始日期为null, 结束日期不为null
			dateCondition.append(objField).append("<='").append(dateFormat.format(to)).append("'");
		} else {
			dateCondition.append(objField).append(">='").append(dateFormat.format(from)).append("'")
			.append(" AND ").append(objField).append("<='").append(dateFormat.format(to)).append("'");
		}
		// 判断sql是否有where
		if (Pattern.compile("where", Pattern.CASE_INSENSITIVE).matcher(sql).find()) { 
			// 找到where条件
			return sql.trim()+" AND "+dateCondition.toString();
		} else { 
			// 没有找到where条件
			return sql.trim()+" WHERE "+dateCondition.toString();
		}
	}
	
	/**
	 * sql，数值在一区间内
	 * @author yibin.jiang
	 * @date 2012-8-15
	 * @param sql
	 * @param objField
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String patchIntegerBySql(String sql, String objField,
			Integer begin, Integer end) {
		if (begin == null && end == null) return sql;
		StringBuffer patchSql = new StringBuffer();
		if (begin != null && end == null) {
			patchSql.append(objField).append(">=").append(begin);
		} else if (begin == null && end != null) {
			patchSql.append(objField).append("<=").append(end);
		} else {
			patchSql.append(objField).append(">=").append(begin).append(" AND ").append(objField).append("<=").append(end);
		}
		// 判断sql是否有where
		if (Pattern.compile("where", Pattern.CASE_INSENSITIVE).matcher(sql).find()) { // 找到where条件
			return sql.trim() + " AND " + patchSql.toString();
		} else { // 没有找到where条件
			return sql.trim() + " WHERE " + patchSql.toString();
		}
	}
	
	/**
	 * 补充枚举数组条件
	 * @param sql sql语句
	 * @param objField 状态字段
	 * @param enums 枚举数组
	 * @return 拼接好的sql语句
	 */
	@SuppressWarnings("rawtypes")
	public static String patchEnumConditionBySql(String sql, String objField, Enum[] enums) {
		if (enums==null || enums.length == 0) return sql;
		String sql2 = null;
		// 判断sql是否有where
		if (Pattern.compile("where", Pattern.CASE_INSENSITIVE).matcher(sql).find()) { 
			// 找到where条件
			sql2 = sql + " AND "+objField+" IN (";
		} else { 
			// 没有找到where条件
			sql2 = sql + " WHERE "+objField+" IN (";
		}
		// 添加状态条件
		Boolean isFirst = true;
		for (Enum enu : enums) {
			if (isFirst) {
				sql2 +="'"+enu+"'";
				isFirst = false;
			} else sql2 += ",'"+enu+"'";
		}
		sql2 += ") ";
		return sql2;
	}	
	
	/**
	 * <p>补充枚举数组条件</p>
	 * @param sql sql语句
	 * @param objField 状态字段
	 * @param enums 枚举数组
	 */
	@SuppressWarnings("rawtypes")
	public static void patchEnumConditionBySql(StringBuffer sql, String objField, Enum[] enums) {
		if (enums.length == 0) return;
		// 判断sql是否有where
		if (Pattern.compile("where", Pattern.CASE_INSENSITIVE).matcher(sql).find()) { 
			// 找到where条件
			sql.append(" AND "+objField+" IN (");
		} else { 
			// 没有找到where条件
			sql.append(" WHERE "+objField+" IN (");
		}
		// 添加状态条件
		Boolean isFirst = true;
		for (Enum enu : enums) {
			if (isFirst) {
				sql.append("'"+enu+"'");
				isFirst = false;
			} else sql.append(",'"+enu+"'");
		}
		sql.append(")");
	}		
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param strings 字符串数组
	 * @return
	 */
	public static String patchArrayConditionBySql(String sql, String objField, String[] strings) {
		if (strings==null||strings.length == 0) return sql;
		String sql2 = null;
		// 判断sql是否有where
		if (Pattern.compile("where", Pattern.CASE_INSENSITIVE).matcher(sql).find()) { 
			// 找到where条件
			sql2 = sql + " AND "+objField+" IN (";
		} else { 
			// 没有找到where条件
			sql2 = sql + " WHERE "+objField+" IN (";
		}
		// 添加状态条件
		Boolean isFirst = true;
		for (String str : strings) {
			if (isFirst) {
				sql2 +="'"+str+"'";
				isFirst = false;
			} else sql2 += ",'"+str+"'";
		}
		sql2 += ") ";
		
		return sql2;
	}
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param strings 字符串数组
	 * @return
	 */
	public static String patchNotInArrayConditionBySql(String sql, String objField, String[] strings) {
		if (strings==null || strings.length == 0) return sql;
		String sql2 = null;
		// 判断sql是否有where
		if (Pattern.compile("where", Pattern.CASE_INSENSITIVE).matcher(sql).find()) { 
			// 找到where条件
			sql2 = sql + " AND "+objField+" NOT IN (";
		} else { 
			// 没有找到where条件
			sql2 = sql + " WHERE "+objField+" NOT IN (";
		}
		// 添加状态条件
		Boolean isFirst = true;
		for (String str : strings) {
			if (isFirst) {
				sql2 +="'"+str+"'";
				isFirst = false;
			} else sql2 += ",'"+str+"'";
		}
		sql2 += ") ";
		return sql2;
	}	
	
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param longs 整数数组
	 * @return
	 */
	public static String patchArrayConditionBySql(String sql, String objField, long[] longs) {
		if (longs.length == 0) return sql;
		String[] strings = new String[longs.length];
		for (int i=0; i<longs.length; i++) strings[i]=longs[i]+"";
		return patchArrayConditionBySql(sql, objField, strings);
	}
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param longs 整数数组
	 * @return
	 */
	public static String patchNotInArrayConditionBySql(String sql, String objField, long[] longs) {
		if (longs.length == 0) return sql;
		String[] strings = new String[longs.length];
		for (int i=0; i<longs.length; i++) strings[i]=longs[i]+"";
		return patchNotInArrayConditionBySql(sql, objField, strings);
	}	
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param longs 整数数组
	 * @return
	 */
	public static String patchNotInArrayConditionBySql(String sql, String objField, Long[] longs) {
		if (longs==null || longs.length == 0) return sql;
		String[] strings = new String[longs.length];
		for (int i=0; i<longs.length; i++) strings[i]=longs[i]+"";
		return patchNotInArrayConditionBySql(sql, objField, strings);
	}	
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param longs 整数数组
	 * @return
	 */
	public static String patchArrayConditionBySql(String sql, String objField, Long[] longs) {
		if (longs.length==0) return sql;
		String[] strings = new String[longs.length];
		for (int i=0; i<longs.length; i++) strings[i]=longs[i]+"";
		return patchArrayConditionBySql(sql, objField, strings);
	}
	
	/**
	 * <p>补充数组条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param longs 布尔数组
	 * @return
	 */
	public static String patchArrayConditionBySql(String sql, String objField, boolean[] bools) {
		if (bools.length == 0) return sql;
		String[] strings = new String[bools.length];
		for (int i=0; i<bools.length; i++) {
			if (bools[i] == true) strings[i] = 1 + "";
			else strings[i]= 0+"";
		}
		return patchArrayConditionBySql(sql, objField, strings);
	}
	
	/**
	 * <p>补充时间比较条件</p>
	 * @param sql sql语句
	 * @param objField 时间字段
	 * @param compares 比较类型数组
	 * @param specDate 指定的日期
	 * @return 拼接好的sql语句
	 */
	public static String patchDateCompareTypesBySql(String sql, String objField, String[] compares, Date specDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");		
		String sql2 = sql + " AND (";
		// 添加时间条件
		boolean isFirst = true;
		for (String compare : compares) {
			if (isFirst) {
				sql2 += objField+compare+"'"+dateFormat.format(specDate)+"'";
				isFirst = false;
			} else sql2 += " OR "+objField+compare+"'"+dateFormat.format(specDate)+"'";
		}
		sql2 += ")";
		return sql2;
	}
	
	/**
	 * <p>获取结果集的记录</p>
	 * @param <T> 返回类型
	 * @param resultList 结果集
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T getSingleResult(List resultList, Class<T> tclass) {
		if (resultList.size() == 0) return null;
		else return (T)resultList.get(0);
	}
}
