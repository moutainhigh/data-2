/**
 * 
 */
package com.hourz.leetcode.server.array;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-10-07
 */
public class SearchRange {
	public int[] searchRange(int[] nums, int target) {
		int n =nums.length;        
		int left=0;        
		int right=n-1;        
		int mid=0;        
		int i;        
		int j;        
		int [] result={-1,-1};        
		if(nums==null||n==0){            
			return result;                    
		}        
		while(left<=right){            
			mid=(right+left)/2;                        
			if(nums[mid]==target) {
				break;                            
			} else if(nums[mid]>target) {                
				right=mid-1;                            
			}else {                
				left=mid+1;            
			}                    
		}        
		if(left>right){            
			return result;        
		}        
		for(j=mid+1;j<=right;j++){
			if(nums[j]!=target){                       
				break;          
			}               
		}               
		for(i=mid-1;i>=left;i--){            
			if(nums[i]!=target){                
				break;            
			}        
		}                
		result[0]=i+1;        
		result[1]=j-1;        
		return result;
	}
}
