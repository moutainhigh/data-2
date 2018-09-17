package com.hourz.common.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>IO工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class IOUtils {

	/**
	 * <p>复制文件</p>
	 * @param from 本地读取的文件路径
	 * @param to   保存的文件路径
	 */
	public static void copy(File from,File to){
		try {
			copy(new FileInputStream(from), new FileOutputStream(to));
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * <p>流从一个文件读入到另一个文件里面</p>
	 * @param from
	 * @param to
	 */
	public static void copy(InputStream from, OutputStream to) {
		InputStream in = null;
		OutputStream out = null;
		int len = 0;
		byte[] bytes = new byte[1024 * 3];
		try {
			in = new BufferedInputStream(from);
			out = new BufferedOutputStream(to);
			while((len = in.read(bytes)) > 0){
				out.write(bytes, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( in != null){
					in.close();
				}
				if( out != null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 * <p>将二进制数据保存到文件</p>
	 * @param bytes 字节数组
	 * @param to 文件
	 */
	public static void saveBytes2File(byte[] bytes, File to) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to));
		out.write(bytes);
		out.flush();
		out.close();
	}
	
	/**
	 * <p>创建文件</p>
	 * @param fileName
	 */
	public static void mkDirs(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * <p>删除某个文件夹下的文件</p>
	 * @param fileName
	 */
	public static void deleteFile(File fileName) {
		File[] listFile = fileName.listFiles();
		if(listFile.length > 0) {
			for(int i = 0; i < listFile.length; i++) {
				listFile[i].delete();
			}
		}
	}	
	
	/**
	 * <p>从文件中读取文本</p>
	 * @param file 文件路径
	 * @param charset 字符集, 方法会使用此字符集格式化文本, 如果为空, 方法默认使用
	 * @return 文件中的文本
	 * @throws FileNotFoundException
	 * @throws IOException 
	 */
	public static String readTextFromFile(String file, String charset) throws FileNotFoundException, IOException {
		return readTextFromInputStream(new FileInputStream(file), charset);
	}

	/**
	 * <p>从输入流中读取文本</p>
	 * @param inputstream 输入流
	 * @param charset 字符集, 方法会使用此字符集格式化文本, 如果为空, 方法默认使用
	 * @return 输入流中的文本
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readTextFromInputStream(InputStream inputstream, String charset) throws IOException {
		// 默认字符集
		if (charset == null) charset = "UTF-8";
		// 读取字节数组
		byte[] textbytes = readBytesFromInputStream(inputstream);
		// 转换为文本并返回
		return new String(textbytes, charset);		
	}
	
	/**
	 * <p>从文件中读取字节</p>
	 * @param file 文件路径
	 * @return 字节数组
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] readBytesFromFile(String file) throws IOException {
		return readBytesFromInputStream(new FileInputStream(file));
	}
	
	/**
	 * <p>从输入流中读取字节数组</p>
	 * @param inputstream 输入流
	 * @return 字节数组
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] readBytesFromInputStream(InputStream inputstream) throws IOException {
		// 构建缓冲输入流
		BufferedInputStream bis = new BufferedInputStream(inputstream);
		// 构建字节数组输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		// 从缓冲输入流中读取字节，并将字节保存到字节数组输出流中
		byte[] buffer = new byte[1024];
		int read_length;
		while((read_length=bis.read(buffer)) > 0){
			baos.write(buffer, 0, read_length);
		}
		// 从字节数组输出流中获取字节数组
		byte[] retBytes = baos.toByteArray();
		baos.close();
		bis.close();
		return retBytes;
	}
}
