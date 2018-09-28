package com.hourz.leetcode.server.array;

/**
 * <p>给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。 
 * <br>不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。</p>
 * @author hourz
 * @since 2018-09-26
 */
public class RemoveDuplicates {

	/**
	 * <p>数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。只要nums[i]=nums[j]，
	 * <br>我们就增加 j 以跳过重复项。当我们遇到 nums[j]≠nums[i] 时，跳过重复项的运行已经结束。
	 * <br>因此我们必须把它（nums[j]）的值复制到 nums[i+1]。
	 * <br>然后递增 i，接着我们将再次重复相同的过程，直到 j 到达数组的末尾为止。</p>
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;	       
        }	        
        int j=0;	        
        for(int i=0;i<nums.length;i++){	        	
            if(nums[i]!=nums[j]){	        		
                nums[++j]=nums[i];	        	
            }	        
        }	        		 
        return ++j;
    }
	
	public static void main(String[] args) {
		RemoveDuplicates removeDuplicates =  new RemoveDuplicates();
        int[] nums = {0,0,1,1,1,2,2,3,3,};
        System.out.println(removeDuplicates.removeDuplicates(nums));
    }
}
