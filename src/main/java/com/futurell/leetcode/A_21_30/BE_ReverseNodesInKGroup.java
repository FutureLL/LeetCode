package com.futurell.leetcode.A_21_30;

/**
 * @Description: 25. K 个一组翻转链表
 * @Author: lilei58
 * @Date: Created in 2021/6/16 12:23 下午
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 1：
 *  输入：head = [1,2,3,4,5], k = 2
 *  输出：[2,1,4,3,5]
 *
 * 示例 2：
 *  输入：head = [1,2,3,4,5], k = 3
 *  输出：[3,2,1,4,5]
 *
 * 示例 3：
 *  输入：head = [1,2,3,4,5], k = 1
 *  输出：[1,2,3,4,5]
 *
 * 示例 4：
 *  输入：head = [1], k = 1
 *  输出：[1]
 */
public class BE_ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

        ListNode listNode = reverseKGroup(head, 2);
        int i = 0;
    }

    /**
     *  思路:
     * 1. 求出链表长度,根据链表长度求翻转次数
     * 2. 循环翻转次数
     * 3. 循环的方式移动end节点,用以选定翻转的链表 start-end
     * 4. 对选出的节点进行翻转
     * 5. 翻转之后重新连接链表即可
     * 6. 重新定义 start end tempStart 以及 tempEnd 指向
     * 7. 进入下一轮循环
     */
    public static ListNode reverseKGroup(ListNode head, int k) {

        // 特殊情况
        if (k == 1) {
            return head;
        }

        // 获取链表长度
        int length = 0;
        for (ListNode tempLength = head; tempLength != null; tempLength = tempLength.next) {
            length ++;
        }
        // 计算翻转次数
        int rolloverTotal = length / k;

        // 定义变量
        ListNode temp = new ListNode(0);
        temp.next = head;
        // start前一个链表节点标记
        ListNode tempStart = temp;
        // 翻转链表起始节点
        ListNode start = head;
        // end前一个链表节点标记
        ListNode tempEnd = null;
        // 翻转链表结束节点
        ListNode end = head;

        for (int i = 0; i < rolloverTotal; i++) {
            // 确定end
            for (int j = 1; j < k; j++) {
                end = end.next;
            }
            tempEnd = end.next;
            end.next = null;
            tempStart.next = null;

            ListNode pre = null, next = null;
            // 翻转后的尾节点
            ListNode reverseEnd = start;
            // 翻转固定链表
            while (start != null) {
                next = start.next;
                start.next = pre;
                pre = start;
                start = next;
            }

            // 连接翻转的链表
            tempStart.next = pre;
            reverseEnd.next = tempEnd;

            // 初始化
            start = tempEnd;
            end = tempEnd;
            tempStart = reverseEnd;
        }
        return temp.next;
    }
}
