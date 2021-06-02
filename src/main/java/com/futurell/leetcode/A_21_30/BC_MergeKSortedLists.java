package com.futurell.leetcode.A_21_30;

import java.util.Collections;

/**
 * @description: 23. 合并K个升序链表
 * @author: Mr.Li
 * @date: Created in 2021/6/2 7:03
 * @version: 1.0
 * @modified By:
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *  输入：lists = [[1,4,5],[1,3,4],[2,6]]
 *  输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 *  输入：lists = []
 *  输出：[]
 *
 * 示例 3：
 *  输入：lists = [[]]
 *  输出：[]
 */
public class BC_MergeKSortedLists {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode listNode2 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode listNode3 = new ListNode(2, new ListNode(6));
        ListNode[] list = {listNode1, listNode2, listNode3};

        ListNode listNode4 = null;
        ListNode[] list2 = {listNode4};

        ListNode listNode = mergeKLists2(list);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 顺序合并 mergeKLists
     * 2. 分治合并 mergeKLists2
     */
    public static ListNode mergeKLists(ListNode[] list) {

        ListNode listOne = null;

        for (int i = 0; i < list.length; i ++) {
            // 将两个链表合并到一个listOne中
            listOne = mergeList(listOne, list[i]);
        }

        return listOne;
    }

    /**
     * 将两个链表合并到一个listOne中
     *
     * @param l1
     * @param l2
     */
    private static ListNode mergeList(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }

        // 定义返回合并后的链表
        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        // 循环 l1 l2 链表
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            cur = cur.next;
        }

        // 处理剩余数据
        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        cur.next = l1 == null ? l2 : l1;

        return pre.next;
    }

    /**
     * 分治合并
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeList(l1, l2);
    }

}
