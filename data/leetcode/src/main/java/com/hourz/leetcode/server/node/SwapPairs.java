package com.hourz.leetcode.server.node;

/**
 * <p>description</p>
 * @author hourz
 * @since 2018-09-26
 */
public class SwapPairs {

	public ListNode swapPairs(ListNode head) {
		// 判断链表是否为空
        if(head==null || head.next==null) {
        	return head;
        }
        // 设置一个空链表只能存在一个节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pivot = dummy;
        ListNode tail = dummy;
        pivot = pivot.next;
        while(pivot != null){
        	// 相邻节点进行交换
        	ListNode temp = pivot.next;
        	if(pivot.next == null){
        		break;
        	}
        	pivot.next = pivot.next.next;
        	temp.next = null;
        	temp.next = pivot;
        	tail.next = temp;
        	tail = tail.next.next;
        	pivot = pivot.next;
        }
        return dummy.next;
   }
}
