package com.hourz.leetcode.server.node;

/**
 * <p>给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表</p>
 * @author hourz
 * @since 2018-09-26
 */
public class ReverseKGroup {
	
	/**
	 * <p>翻转链表</p>
	 * @param head 链表
	 * @param k 节点
	 * @return 一个链表
	 */
	public ListNode reverse(ListNode head, int k){
        int count = 0;
        // 当前列表
        ListNode currentNode = head;
        while (currentNode != null && count < k){
            currentNode = currentNode.next;
            count++;
        }
        while (count-- > 0) {
            ListNode tmp = head.next;
            head.next = currentNode;
            currentNode = head;
            head = tmp;
            ListNode out = currentNode;
            while (out != null){
                System.out.print(out.val + " ");
                out = out.next;
            }
            System.out.println();
        }
        return currentNode;
    }
	// 测试
    public static void main(String[] args){
    	ReverseKGroup reverseKGroup = new ReverseKGroup();
        // 初始化5个链表节点
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        // 添加节点间的关联关系
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        // 对前四个节点进行翻转
        ListNode reverse = reverseKGroup.reverse(head, 4);
    }
}
