package com.hourz.common.random;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * <p>随机工具类</p>
 * @author hourz
 * @since 2016-05-24
 */
public class RandomUtil {
	
	/**
	 * <p>获取指定数量的随机数</p>
	 * @param from 开始数值(包含)
	 * @param to 结束数值(包含)
	 * @param count 获取数量
	 * @return 随机整数数组
	 */
	public static int[] getRandomCount(int from, int to, int count) {
		// 种子你可以随意生成，但不能重复
		int[] seed = new int[to-from];
		for (int i=from; i<to; i++) seed[i-from]=i;
		int[] ranArr = new int[count];
		Random ran = new Random();
		for (int i = 0; i < count; i++) {
			// 得到一个位置
			int j = ran.nextInt(seed.length-i);
			// 得到那个位置的数值
			ranArr[i] = seed[j];
			// 将最后一个未用的数字放到这里
			seed[j] = seed[seed.length-i-1];
		}
		return ranArr;
	}
	
	/**
	 * <p>从给定整数数组中随机获取一个整数</p>
	 * @param contents 整数数组
	 * @return 整数
	 */
	public static int getRandomContent(int[] contents) {
		Random ran = new Random();
		// 得到一个位置
		int j = ran.nextInt(contents.length);
		// 得到那个位置的数值
		return contents[j];
	}	
	
	/**
	 * <p>获取随机图片</p>
	 * @return
	 * @throws IOException 
	 */
	public static RandomImage getRandomImage() throws IOException{
		// 随机图像对象
		RandomImage randomImage = new RandomImage();
        // 在内存中创建图象   
        int width=60, height=20;   
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
        // 获取图形上下文   
        Graphics g = image.getGraphics();   
        // 生成随机类   
        Random random = new Random();   
        // 设定背景色   
        g.setColor(getRandColor(150,250));   
        g.fillRect(0, 0, width, height);   
        // 设定字体   
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));   
        // 随机产生30条干扰线，使图象中的认证码不易被其它程序探测到   
        g.setColor(getRandColor(160,200));   
        for (int i=0;i<30;i++) {   
	        int x = random.nextInt(width);   
	        int y = random.nextInt(height);   
	        int xl = random.nextInt(12);   
	        int yl = random.nextInt(12);   
	        g.drawLine(x,y,x+xl,y+yl);   
        }
        // 取随机产生的认证码(4位数字)   
        for (int i=0;i<4;i++){   
            String rand=String.valueOf(random.nextInt(10));   
            // 将认证码显示到图象中   
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));   
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成   
            g.drawString(rand,13*i+6,16);   
            randomImage.code+=rand;/*   赋值验证码   */  
        }   
        // 图象生效   
        g.dispose();   
        ByteArrayInputStream input=null;   
        ByteArrayOutputStream output = new ByteArrayOutputStream();   
        ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);   
        ImageIO.write(image, "JPEG", imageOut);   
        imageOut.close();   
        input = new ByteArrayInputStream(output.toByteArray());   
        randomImage.image=input;	/* 赋值图像 */
        return randomImage;
	}
	
	/**
	 * <p>获取随机的颜色</p>
	 * @param fc
	 * @param bc
	 * @return
	 */
    private static Color getRandColor(int fc,int bc){   
        Random random = new Random();   
        if(fc>255) fc=255;   
        if(bc>255) bc=255;   
        int r=fc+random.nextInt(bc-fc);   
        int g=fc+random.nextInt(bc-fc);   
        int b=fc+random.nextInt(bc-fc);   
        return new Color(r,g,b);   
    }
	
	/**
	 * <p>随机验证码图片类</p>
	 */
	public static class RandomImage {
		// 验证码图片
		public ByteArrayInputStream image;
		// 验证码
		public String code;
	}
}
