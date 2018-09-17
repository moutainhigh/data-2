package com.hourz.common.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



/**
 * 加解密工具类
 * @author hourz
 * @since 2016-05-24
 */
public class EncryptUtils {
	// 十六进制字符集合
	private static String hexStr =  "0123456789ABCDEF";
	
	/**
	 * 对字符串进行MD5加密
	 * @param source 原始字符串
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5Encode(String source) throws NoSuchAlgorithmException {
		String resultString = null;
		resultString = new String(source);
		MessageDigest md = MessageDigest.getInstance("MD5");
		resultString = byte2HexStr(md.digest(resultString.getBytes()));
		return resultString;
	}
	
	/** 
     * 3DES加密 
     * @param source  待加密字符串
     * @param key  密码
     * @return 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
     */  
    public static String threeDesEncoder(String source, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(build3DesKey(key), "DESede");  
	    Cipher cipher = Cipher.getInstance("DESede");  
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);  
	    byte[] b = cipher.doFinal(source.getBytes("UTF8"));  
	    return byte2HexStr(b);
    }  
      
    /** 
     * 3DES解密 
     * @param dest  待解密字符串
     * @param key  密码
     * @return 
     * @throws Exception 
     */  
    public static String threeDesDecoder(String dest, String key) throws Exception {
    	SecretKey secretKey = new SecretKeySpec(build3DesKey(key), "DESede");  
		Cipher cipher = Cipher.getInstance("DESede");  
		cipher.init(Cipher.DECRYPT_MODE, secretKey);  
		byte[] b = cipher.doFinal(str2ByteArray(dest));
		return new String(b, "UTF8");
    }  
      
    /** 
     *  二进制转换为十六进制
     * @param bytes 
     * @return 
     */  
    public static String byte2HexStr(byte[] bytes){  
          
        String result = "";  
        String hex = "";  
        for(int i=0;i<bytes.length;i++){  
            //字节高4位  
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));  
            //字节低4位  
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));  
            result +=hex;  
        }  
        return result;  
    }  
    
    /** 
     * 十六进制转换为二进制  
     * @param hexString 
     * @return 
     */  
    public static byte[] hexStringToByte(String hexString){  
        //hexString的长度对2取整，作为bytes的长度  
        int len = hexString.length()/2;  
        byte[] bytes = new byte[len];  
        byte high = 0;//字节高四位  
        byte low = 0;//字节低四位  
        for(int i=0;i<len;i++){  
             //右移四位得到高位  
             high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);  
             low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));  
             bytes[i] = (byte) (high|low);//高地位做或运算  
        }  
        return bytes;  
    }  	 
      
    /** 
     * 字符串转字节数组 
     * @param str 
     * @return 
     */  
    private static byte[] str2ByteArray(String str) {  
        int byteArrayLength = str.length()/2;  
        byte[] b = new byte[byteArrayLength];  
        for (int i = 0; i < byteArrayLength; i++) {  
            byte b0 = (byte) Integer.valueOf(str.substring(i*2, i*2+2), 16).intValue();  
            b[i] = b0;  
        }
        return b;  
    }  
      
    /** 
     * 构造3DES加解密方法key 
     * @param keyStr 
     * @return 
     * @throws Exception 
     */  
    private static byte[] build3DesKey(String keyStr) throws Exception {  
        byte[] key = new byte[24];  
        byte[] temp = keyStr.getBytes("UTF8");  
        if (key.length > temp.length) {  
            System.arraycopy(temp, 0, key, 0, temp.length);  
        } else {  
            System.arraycopy(temp, 0, key, 0, key.length);  
        }
        return key;  
    }  
}
