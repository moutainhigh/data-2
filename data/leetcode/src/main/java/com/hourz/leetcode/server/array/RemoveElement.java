package com.hourz.leetcode.server.array;

/**
 * <p> 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <br> 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <br> 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。</p>
 * @author hourz
 * @since 2018-09-27
 */
public class RemoveElement {

	/**
	 * <p>删除数组</p>
	 * @param nums 数组
	 * @param val 数值
	 * @return 剩余数组大小
	 */
	public int removeElement(int[] nums, int val) {
		// 判断数组是否为空
        if (nums.length == 0) return 0;
        // 冒泡循环
        int i = 0, j = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                for (j = i + 1; j < nums.length; j++) {
                    if (nums[i] != nums[j]) {
                        nums[i] = nums[j];
                        nums[j] = val;
                        break;
                    }
                }
                // 如果j大小和nums长度相等，返回i未数组长度
                if (j == nums.length) {
                    return i;
                }
            }
        }
        return nums.length;
    }
	
	public static void main(String[] args) {
		RemoveElement rElement = new RemoveElement();
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(rElement.removeElement(nums, 1));
	}
}
