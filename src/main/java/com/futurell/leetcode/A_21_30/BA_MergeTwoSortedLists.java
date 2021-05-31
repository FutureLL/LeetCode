package com.futurell.leetcode.A_21_30;


/**
 * @Description: 21. 合并两个有序链表
 * @Author: lilei58
 * @Date: Created in 2021/5/18 8:04 上午
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 *  输入：l1 = [1,2,4], l2 = [1,3,4]
 *  输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 *  输入：l1 = [], l2 = []
 *  输出：[]
 *
 * 示例 3：
 *  输入：l1 = [], l2 = [0]
 *  输出：[0]
 */

/**
 * 链表节点
 */
class ListNode {
    int val;

    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class BA_MergeTwoSortedLists {

    public static void main(String[] args) {

        // l1
        ListNode listNode1 = new ListNode(1, new ListNode(3));
        // l2
        ListNode listNode2 = new ListNode(1, new ListNode(4));

        ListNode listNode = mergeTwoLists2(listNode1, listNode2);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 循环迭代 mergeTwoLists
     * 2. 递归 mergeTwoLists2
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
