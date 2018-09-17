package com.hourz.common.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <p>文本工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class TextUtils {

	// 判断局域网地址的正则表达式
	private static final Pattern P_LAN_IP = Pattern.compile("(127[.]0[.]0[.]1)|(localhost)|(10[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})|(172[.]((1[6-9])|(2\\d)|(3[01]))[.]\\d{1,3}[.]\\d{1,3})|(192[.]168[.]\\d{1,3}[.]\\d{1,3})");
	// 获取domain的表达式
	private static final Pattern P_DOMAIN = Pattern.compile("http://([\\w|\\d|\\.]+)[:|/]");
	// 获取port的表达式
	private static final Pattern P_PORT = Pattern.compile("http://[\\w|\\d|\\.]+:(\\d+)"); 
	// 获取dmoain和port的表达式
	private static final Pattern P_DOMAIN_PORT = Pattern.compile("http://([\\w|\\d|\\.]+[:|\\d]*)/"); 
	// 获取服务名称的表达式
	private static final Pattern P_SERV_NAME = Pattern.compile("http://[\\w|\\d|\\.]+[:|\\d]*/(\\w+)/"); 
	// 判断手机号码的表达式
	private static final Pattern P_MOBILE = Pattern.compile("^\\d+{11}$");
	// 判断数字的表达式
	private static final Pattern P_NUMERIC = Pattern.compile("^\\d+(\\.\\d+)*$");
	// 判断IP地址的表达式
	private static final Pattern P_IP = Pattern.compile("(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))");
	// 判断邮箱的表达式
	private static final Pattern P_EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
	
	/**
	 * <p>判断字符串是否为数字</p>
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    return P_NUMERIC.matcher(str).matches();    
	} 	
	
	/**
	 * <p>判断字符串是否为手机号码</p>
	 * @param str 字符串
	 * @return
	 */
	public static boolean isMobile(String str) {
		return P_MOBILE.matcher(str).matches();
	}

	/**
     * <p>ip校验</ip>
     * @param str
     * @return
     */
    public static boolean isIpAddress(String str){
        return P_IP.matcher(str).matches();
    }	
    
    /**
     * <p>是否为Email</p>
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
    	return P_EMAIL.matcher(str).matches();
    }
	
	
	/**
	 * <p>获取URL的Domain部分(可能是域名或IP地址)</p>
	 * @param url Url
	 * @return
	 */
	public static String getDomainPartOfURL(String url){
		Matcher m = P_DOMAIN.matcher(url);
		m.find();
		return m.group(1);
	}
	
	/**
	 * <p>获取URL的Port部分</p>
	 * @param url Url
	 * @return
	 */
	public static String getPortPartOfURL(String url){
		Matcher m = P_PORT.matcher(url);
		m.find();
		return m.group(1);		
	}
	
	/**
	 * <p>获取URL的Domain和Port部分</p>
	 * @param url Url
	 * @return
	 */
	public static String getDomainPortOfURL(String url){
		Matcher m = P_DOMAIN_PORT.matcher(url);
		m.find();
		return m.group(1);			
	}

	/**
	 * 获取URL的服务名称部分
	 * @param url
	 * @return
	 */
	public static String getServNameOfURL(String url) {
		Matcher m = P_SERV_NAME.matcher(url);
		m.find();
		return m.group(1);	
	}	
	
	/**
	 * 判断地址是否为局域网地址(包括localhost)
	 * @param address 有效的网络地址(包括localhost)
	 * @return
	 */
	public static boolean isLanAddress(String address) {
		return P_LAN_IP.matcher(address).matches();
	}

	/**
	 * 移除XML文档中的注释
	 * @param strXML XML文档字符串
	 */
	public static String removeXMLComment(String strXML) {
		String tmpStrXML = strXML;
		int commentStart = -1;
		while((commentStart = tmpStrXML.indexOf("<!--")) != -1) {
			int commentEnd = tmpStrXML.indexOf("-->");
			tmpStrXML = tmpStrXML.substring(0, commentStart) + tmpStrXML.substring(commentEnd+3);
		}
		return tmpStrXML;
	}	
	
	/**
	 * 获取汉字串拼音
	 * @param strCN
	 * @return 汉字串拼音
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getHanyuPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
		if(null == strCN) return null;
		StringBuffer spell = new StringBuffer();
		char[] charOfCN = strCN.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charOfCN.length; i++) {
			// 是否为中文字符
			if (charOfCN[i] > 128) {
				String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], defaultFormat);
				if (null != spellArray) {
					spell.append(spellArray[0]);
				}else{
					spell.append(charOfCN[i]);
				}
			} else {
				spell.append(charOfCN[i]);
			}
		}
		return spell.toString();
	}
	
	/**
	 * 获取汉字串 拼音首字母
	 * @param strCN
	 * @return  拼音首字母
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getFirstHanyuPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
		if(null == strCN) return null;
		StringBuffer firstSpell = new StringBuffer();
		char[] charOfCN = strCN.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charOfCN.length; i++) {
			// 是否为中文字符
			if (charOfCN[i] > 128) {
				String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], format);
				// 取拼音首字母
				if (null != spellArray) {
					firstSpell.append(spellArray[0].charAt(0));
				}else{
					firstSpell.append(charOfCN[i]);
				}
			} else {
				firstSpell.append(charOfCN[i]);
			}
		}
		return firstSpell.toString();
	}
	
	/**
	 * <p>获取 汉字串拼音首字母 & 汉字串拼音</p>
	 * @param strCN
	 * @return 下标0：汉字串拼音首字母；下标1：汉字串拼音
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String[] getFirstAndHanyuPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
		if(null == strCN) return null;
		StringBuffer firstSpell = new StringBuffer();
		StringBuffer spell = new StringBuffer();
		char[] charOfCN = strCN.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charOfCN.length; i++) {
			// 是否为中文字符
			if (charOfCN[i] > 128) {
				String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], defaultFormat);
				if (null != spellArray) {
					firstSpell.append(spellArray[0].charAt(0));
					spell.append(spellArray[0]);
				}else{
					firstSpell.append(charOfCN[i]);
					spell.append(charOfCN[i]);
				}
			} else {
				firstSpell.append(charOfCN[i]);
				spell.append(charOfCN[i]);
			}
		}
		return new String[] { firstSpell.toString(), spell.toString() };
	}
}
