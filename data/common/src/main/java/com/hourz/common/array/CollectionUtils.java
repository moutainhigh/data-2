package com.hourz.common.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hourz.common.random.RandomUtil;

/**
 * <p>集合工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class CollectionUtils {

	/**
	 * <p>将T类型的List转换为T类型的数组</p>
	 * @param tList T类型的List
	 * @return T类型的数组
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T[] changeListToArray(List<T> tList) {
		return (T[])tList.toArray();
	}
	
	/**
	 * <p>将Set集合转换为List集合</p>
	 * @param <T> 泛型
	 * @param set set集合
	 * @return list集合
	 */
	public static <T extends Object> List<T> changeSetToList(Set<T> set) {
		if (set == null) return null;
		List<T> retList = new ArrayList<T>();
		Iterator<T> iter = set.iterator();
		while(iter.hasNext()) retList.add(iter.next());
		return retList;
	}
	
	/**
	 * <p>将List集合转换为Set集合</p>
	 * @param <T> 泛型
	 * @param list list集合
	 * @return set集合
	 */
	public static <T extends Object> Set<T> changeListToSet(List<T> list) {
		if (list == null) return null;
		Set<T> retSet = new HashSet<T>();
		Iterator<T> iter = list.iterator();
		while(iter.hasNext()) retSet.add(iter.next());
		return retSet;
	}
	
	/**
	 * <p>将给定列表中元素打乱做随机</p>
	 * @param objList 对象列表
	 */
	public static <T> void randomList(List<T> objList) {
		Collections.sort(objList, new Comparator<T>(){
			@Override
			public int compare(T o1, T o2) {
				return RandomUtil.getRandomContent(new int[]{-1, 0, 1});
			}
		});
	}	
	
	
	/**
	 * <p>将枚举数组转换为字符串数组，转换方法为：调用每个枚举对象的toString()方法</p>
	 * @param enumArray 枚举数组
	 * @return 字符串数组
	 */
	@SuppressWarnings("rawtypes")
	public static String[] enumArrayToStringArray(Enum[] enumArray) {
		String[] stringArray = new String[enumArray.length];
		for (int i=0; i< enumArray.length; i++) stringArray[i] =  enumArray[i].toString();
		return stringArray;
	}
	
	/**
	 * <p>从内容列表中随机获取指定数量的对象</p>
	 * @param <T>
	 * @param contentList 内容列表
	 * @param count 指定数量的值
	 * @return
	 */
	public static <T extends Object> List<T> getRandomList(List<T> contentList, int count){
		if (contentList.size()<count) return contentList;
		List<T> resultList = new ArrayList<T>();
		if (contentList.size()==0) return resultList;
		int[] indexes = RandomUtil.getRandomCount(0, contentList.size()-1, count);
		for (int i : indexes) resultList.add(contentList.get(i));
		return resultList;
	}
	
	/**
	 * <p>将列表转换为字符串</p>
	 * @param list 列表
	 * @param seperator 分隔符
	 * @param surround list中值的包围符号，如果不需要，请传null
	 * @return
	 */
	public static <T extends Object> String listToString3(List<T> list, String seperator, String surround) {
		// 结果字符串
		StringBuffer retString = new StringBuffer();
		// 遍历元素，拼合字符串
		for (int i=0; i<list.size(); i++) {
			if (i > 0) retString.append(seperator);
			if (surround != null) retString.append(surround).append(list.get(i)).append(surround);
			else retString.append(list.get(i));
		}
		return retString.toString();
	}	
}
