package com.futurell.leetcode;

/**
 * @Description: 2. 两数相加
 * @Author: lilei58
 * @Date: Created in 2021/2/23 3:28 下午
 *
 * 给你两个'非空'的链表,表示两个非负的整数。它们每位数字都是按照'逆序'的方式存储的,并且每个节点只能存储'一位'数字。
 * 请你将两个数相加,并以相同形式返回一个表示和的链表。
 *
 * 示例 1：
 *  输入：l1 = [2,4,3], l2 = [5,6,4]
 *  输出：[7,0,8]
 *  解释：342 + 465 = 807.
 *
 * 示例 2：
 *  输入：l1 = [0], l2 = [0]
 *  输出：[0]
 *
 * 示例 3：
 *  输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 *  输出：[8,9,9,9,0,0,0,1]
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

public class B_AddTwoNumbers {

    public static void main(String[] args) {
        // l1
        ListNode listNode1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        // l2
        ListNode listNode2 = new ListNode(9, new ListNode(9));

        ListNode listNode = addTwoNumbers(listNode1, listNode2);
        int i = 0;
    }

    /**
     *  思路: 链表
     * 1. 将两个链表看成是相同长度的进行遍历,如果一个链表较短则在前面补 00,比如 987 + 23 = 987 + 023 = 1010
     * 2. 每一位计算的同时需要考虑上一位的进位问题,而当前位计算结束后同样需要更新进位值
     * 3. 如果两个链表全部遍历完毕后,进位值为 11，则在新链表最前方添加节点 11
     * 4. 小技巧: 对于链表问题,返回结果为头结点时,通常需要先初始化一个预先指针 pre,该指针的下一个节点指向真正的头结点head。
     *    使用预先指针的目的在于链表初始化时无可用节点值,而且链表构造过程需要指针移动,进而会导致头指针丢失,无法返回结果。
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 保证了 pre 和 cur 开始时指向的是同一位置
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        // 存储进位值
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1Value = l1 == null ? 0 : l1.val;
            int l2Value = l2 == null ? 0 : l2.val;
            int sum = l1Value + l2Value + carry;

            // 计算进位
            carry = sum / 10;
            // 计算当前位
            sum = sum % 10;

            cur.next = new ListNode(sum);
            // 移动 cur 指针
            cur = cur.next;

            // 移动 l1 和 l2 指针
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        // 最后一一次计算结果如果需要进位,则进位
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}
