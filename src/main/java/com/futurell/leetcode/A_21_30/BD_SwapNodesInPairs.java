package com.futurell.leetcode.A_21_30;

import jdk.nashorn.internal.objects.annotations.Where;

/**
 * @description: 24. 两两交换链表中的节点
 * @author: Mr.Li
 * @date: Created in 2021/6/8 22:06
 * @version: 1.0
 * @modified By:
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 *
 * 示例 1：
 *  输入：head = [1,2,3,4]
 *  输出：[2,1,4,3]
 *
 * 示例 2：
 *  输入：head = []
 *  输出：[]
 *
 * 示例 3：
 *  输入：head = [1]
 *  输出：[1]
 */
public class BD_SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode listNode = swapPairs2(head);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 迭代 swapPairs
     * 2. 递归 swapPairs2
     */
    public static ListNode swapPairs(ListNode head) {

        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode copyTemp = temp;

        while (temp.next != null && temp.next.next != null) {
            ListNode l1 = temp.next;
            ListNode l2 = temp.next.next;

            temp.next = l2;
            l1.next = l2.next;
            l2.next = l1;
            temp = l1;
        }
        return copyTemp.next;
    }

    public static ListNode swapPairs2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs2(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
