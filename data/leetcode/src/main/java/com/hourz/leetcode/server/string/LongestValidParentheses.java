package com.hourz.leetcode.server.string;

import java.util.Stack;

/**
 * <p>给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度</p>
 * @author hourz
 * @since 2018-10-07
 */
public class LongestValidParentheses {
	public int longestValidParentheses(String s) {        
		int max = 0, start = 0;        
		if(null == s) return 0;         
		int len = s.length();         
		Stack<Integer> stack = new Stack<>();        
		for(int index = 0; index < len; index++){            
			// 遇左括号(，压栈(栈中元素为当前位置所处的下标)            
			if('(' == s.charAt(index)){                
				stack.push(index);                
				continue;            
			} else {                
				if(stack.isEmpty()){                    
					start = index+1;                    
					continue;                
				} else {                    
					stack.pop();                    
					if(stack.isEmpty()){                        
						max = Math.max(max, index-start+1);                    
					} else {                        
						max = Math.max(max, index-stack.peek());                    
					}               
				}            
			}        
		}         
		return max;
	}
	
	public int longestValidParentheses1(String s) {        
		if(null == s) return 0;         
		int len = s.length(), max = 0;        
		int[] d = new int[len];         
		for(int index = len-2; index >= 0; index--){            
			int symIndex = index+1+d[index+1];            
			if('(' == s.charAt(index) && symIndex < len && ')' == s.charAt(symIndex)){                
				d[index] = d[index+1]+2;                
				if(symIndex+1 < len){                    
					d[index] += d[symIndex+1];                
				}           
			}             
			max = Math.max(max, d[index]);        
			}        
		return max;    
	}
	
	public static void main(String[] args) {
		LongestValidParentheses longestVP = new LongestValidParentheses();
		System.out.println(longestVP.longestValidParentheses("()()()((((((((((((((((()))))))))))))))))"));
		System.out.println(longestVP.longestValidParentheses1("()()()((((((((((((((((()))))))))))))))))"));
	}
}
