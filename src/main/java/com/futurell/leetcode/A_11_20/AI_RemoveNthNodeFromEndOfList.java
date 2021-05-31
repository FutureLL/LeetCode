package com.futurell.leetcode.A_11_20;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description: 19. 删除链表的倒数第 N 个结点
 * @Author: lilei58
 * @Date: Created in 2021/5/3 11:05 上午
 *
 * 给你一个链表,删除链表的倒数第 n 个结点,并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 示例 1：
 *  输入：head = [1,2,3,4,5], n = 2
 *   输出：[1,2,3,5]
 *
 * 示例 2：
 *  输入：head = [1], n = 1
 *  输出：[]
 *
 * 示例 3：
 *  输入：head = [1,2], n = 1
 *  输出：[1]
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

public class AI_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head1 = new ListNode(1);
        ListNode listNode = removeNthFromEnd2(head, 2);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 计算链表长度     removeNthFromEnd
     * 2. 栈             removeNthFromEnd2
     * 3. 双指针          removeNthFromEnd3
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0, head);
        // 获取链表长度
        int length = 0;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            length ++;
        }

        ListNode cur = dummy;
        // 计算链表截止位置
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

}
