/**
 * 
 */
package com.hourz.leetcode.server.digit;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-27
 */
public class Divide {
	public int divide(int dividend, int divisor) {
		// 排除余数为-1
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		// 
		long a = Math.abs((long)dividend);
		long b = Math.abs((long)divisor);
		int num = 0;
		long sum;
		while (b <= a) {
			sum = b;
			int count = 1;
			while (sum + sum <= a) {
	        	count += count;
	        	sum += sum;
			}
			a = a - sum;
			num = num + count;
		}
		if ((dividend < 0 && divisor > 0) || dividend > 0 && divisor < 0) {
			num = -num;
		}
		return num;
	}
	public static void main(String[] args) {
		Divide divide = new Divide();
		//System.out.println(divide(Integer.MIN_VALUE, -1));
		//System.out.println(divide(8, 1));
		//System.out.println(divide(24,3));
		System.out.println(divide.divide(234, -67));
	}

}
