package com.hourz.common.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>压缩/解压缩工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class Zip { 
	
    /**
     * <p>压缩给定字符串</p>
     * @param ziplessContent : 需要压缩的内容
     * @return
     * @throws IOException 
     */
    public static String doZip(String ziplessContent) throws IOException {
    	// 字符输出流
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry("zip");
        zos.putNextEntry(entry);
        zos.write(ziplessContent.getBytes());
        zos.finish();
        zos.close();
        baos.close();
        return new String(baos.toByteArray(), "ISO8859-1");
    }

    /**
     * <p>压缩给定字符串</p>
     * @param ziplessContent : 需要压缩的内容
     * @return
     * @throws IOException 
     */
    public static byte[] doZip(byte[] ziplessContent) throws IOException{ 
    	// 字符输出流
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry("zip");
        zos.putNextEntry(entry);
        zos.write(ziplessContent);
        zos.finish();
        zos.close();
        baos.close();
        return baos.toByteArray();
    }
    
    /**
     * <p>解压给定的压缩字符串</p>
     * @param zippedContent : 需要解压的内容
     * @return
     * @throws IOException 
     */
    public static String unZip(String zippedContent) throws IOException{ 
    	// 字符输出流
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int readedBytes = 0;
        byte[] buf = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zippedContent.getBytes("ISO8859-1")));
        while(zis.getNextEntry() != null) {
	        while((readedBytes = zis.read(buf, 0, buf.length)) > 0){ 
	        	baos.write(buf, 0, readedBytes); 
	        }
        }
        return baos.toString();
    } 
    
    /**
     * <p>解压给定的字节数组</p>
     * @param zippedContent : 需要解压的内容
     * @return
     * @throws IOException 
     */
    public static String unZip(byte[] zippedContent) throws IOException{
    	if (zippedContent == null) return null;
    	// 字符输出流
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	int readedBytes = 0;
        byte[] buf = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zippedContent));
        while(zis.getNextEntry() != null) {
	        while((readedBytes = zis.read(buf, 0, buf.length)) > 0){ 
	        	baos.write(buf, 0, readedBytes); 
	        }
        }
        return baos.toString();
    } 
}