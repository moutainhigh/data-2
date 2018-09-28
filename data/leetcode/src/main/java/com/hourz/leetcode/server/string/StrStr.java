/**
 * 
 */
package com.hourz.leetcode.server.string;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-27
 */
public class StrStr {

	public int strStr(String haystack, String needle) {
		// 直接获取下标
        return haystack.indexOf(needle);
    }
	
	public int strStr1(String haystack, String needle) {
		// 获取字符长度
		int needleLen = needle.length();
		// 判断字符长度是否为0
	    if (needleLen == 0) {
	        return 0;
	    }
	    // 循环对比
	    for (int i = 0; i < haystack.length(); i++) {
	    	// 截取字段进行对比
	        if ((i + needleLen) <= haystack.length() && haystack.substring(i, i + needleLen).equals(needle)) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public static void main(String[] args) {
		StrStr strStr= new StrStr();
		System.out.println(strStr.strStr("Hello", "l"));
		System.out.println(strStr.strStr1("Hello", "l"));
	}
}
