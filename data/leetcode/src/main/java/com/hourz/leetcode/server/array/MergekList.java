package com.hourz.leetcode.server.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>合并k个排序链表,返回合并后的排序链表.请分析和描述算法的复杂度.</p>
 * @author hourz
 * @since 2018-09-19
 */
public class MergekList {

	public static ListNode mergeKLists(ListNode[] lists) {
		ListNode result = new ListNode(0);
        ListNode point = result;
        List<Integer> list = new ArrayList<Integer>();
        for (ListNode node : lists) {
            while (node != null) {
                list.add(node.val);
                node = node.next;
            }
        }
        Collections.sort(list);
        for (int x : list) {
            point.next = new ListNode(x);
            point = point.next;
            System.out.println(x);
        }
        return result.next;
	}
	
	public static void main(String[] args) {
		ListNode node = new ListNode(0);
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(6);
		ListNode[] lists = {node, node1, node2, node3, node4, node5};
		System.out.println(mergeKLists(lists).val);
	}
}
