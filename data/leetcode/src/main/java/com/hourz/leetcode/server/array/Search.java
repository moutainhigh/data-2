/**
 * 
 */
package com.hourz.leetcode.server.array;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-10-07
 */
public class Search {

	public int search(int[] nums, int target) {
		int start = 0, end = nums.length-1;
		while(start <= end){
			int middle = (start + end) >> 1;
			if(target == nums[middle]) return middle;
			if(rightVal(target, nums[0]) > rightVal(nums[middle], nums[0]))
				start = middle + 1;
			else
				end = middle - 1;
		}
		return -1;
	}
	int rightVal(int x, int start) {
		return x < start ? x + 0x3f3f3f3f - start : x;    
	}

	public static void main(String[] args) {
		Search search = new Search();
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(search.search(nums, 1));
	}
}
