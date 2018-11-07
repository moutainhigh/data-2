package com.hourz.common.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * <p>流处理公用类</p>
 * @author hourz
 * @since 2018-10-21
 */
public class StreamUtils {
	// 
	private static StreamUtils singleton;
	// 
	private StreamUtils(){
		
	}
	// 双重锁模式实现
	public static StreamUtils getInstance(){
		if(singleton == null){
			synchronized(StreamUtils.class){
				if(singleton == null){
					singleton = new StreamUtils();
				}
			}
		}
		return singleton;
	}
	
	// 设置流大小
	public int BUFFER_SIZE = 4096;
	/**
	 * 将InputStream转换成String,无编码的转换
	 * @param inStream InputStream类型输入内容
	 * @return 转换结果String字符
	 */
	public String InputStreamTOString(InputStream inStream){
		// 
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		String string = null;
		int count = 0;
		try {
			while ((count = inStream.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = null;
		try {
			string = new String(outStream.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}
	/**
	 * 将InputStream转换成String, 带编码的转换
	 * @param inStream InputStream类型输入内容
	 * @param encoding 编码格式
	 * @return 转换结果String字符
	 */
	public String InputStreamTOString(InputStream inStream, String encoding) {
		String string = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = inStream.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = null;
		try {
			string = new String(outStream.toByteArray(), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}
	/**
	 * 将String转为InputStream
	 * @param in String输入内容
	 * @return 转换结果InputStream输入流
	 */
	public InputStream StringTOInputStream(String in) {
		ByteArrayInputStream is = null;
		try {
			is = new ByteArrayInputStream(in.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return is;
	}
	/**
	 * 将InputStream转换成byte数组
	 * @param in 输入流InputStream
	 * @return 转换结果Byte数组
	 */
	public byte[] InputStreamTOByte(InputStream in) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = null;
		return outStream.toByteArray();
	}
	/**
	 * 将String转为Byte数组
	 * @param in 输入String内容
	 * @return 转换结果Byte数组
	 */
	public byte[] StringTObyte(String in) {
		byte[] bytes = null;
		bytes = InputStreamTOByte(StringTOInputStream(in));
		return bytes;
	}
	/**
	 * 将Byte数组转换成InputStream
	 * @param in Byte数组
	 * @return 转换结果InputStream
	 */
	public static InputStream byteTOInputStream(byte[] in) {
		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}
	/**
	 * 将Byte数组转换成String
	 * @param in Byte数组
	 * @return 转换结果String
	 */
	public String byteTOString(byte[] in) {
		InputStream is = null;
		try {
			is = byteTOInputStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return InputStreamTOString(is, "UTF-8");
	}
	/**
	 * 将String输入内容转换成Byte数组，转换成String输出内容
	 * @param in 输入内容
	 * @return 转换结果String
	 */
	public String getString(String in) {
		String result = null;
		try {
			result = byteTOString(StringTObyte(in));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * InputStream 转换成byte[]
	 * @param is 输入流
	 * @return 转换结果Byte数组
	 * @throws IOException
	 */
	public byte[] getBytes(InputStream is){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[BUFFER_SIZE];
		int len = 0;
		try {
			while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
				baos.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();
		System.out.println(new String(bytes));
		return bytes;
	}
	/**
	 * 根据文件路径创建文件输入流处理, 以字节为单位（非 unicode）
	 * @param filepath 文件路径
	 * @return 转换结果FileInputStream输出流
	 */
	public FileInputStream getFileInputStream(String filepath) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileInputStream;
	}
	/**
	 * 根据文件对象创建文件输出流处理, 以字节为单位（非 unicode ）
	 * @param file 文件
	 * @param append true:文件以追加方式打开, false:则覆盖原文件的内容
	 * @return 转换结果FileOutputStream输出流
	 */
	public FileOutputStream getFileOutputStream(File file, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file, append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}
	/**
	 * 根据文件路径创建文件输出流处理, 以字节为单位（非 unicode ）
	 * @param filepath 文件路径
	 * @param append true:文件以追加方式打开, false:则覆盖原文件的内容
	 * @return 转换结果FileOutputStream输出流
	 */
	public static FileOutputStream getFileOutputStream(String filepath,boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filepath,append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}
	/**
	 * 根据文件路径得到文件
	 * @param filepath 文件路径
	 * @return 结果File文件对象
	 */
	public File getFile(String filepath) {
		return new File(filepath);
	}
	/**
	 * 字节数组输出流
	 * @return 结果ByteArrayOutputStream字节数组输出流
	 */
	public ByteArrayOutputStream getByteArrayOutputStream() {
		return new ByteArrayOutputStream();
	}
}
