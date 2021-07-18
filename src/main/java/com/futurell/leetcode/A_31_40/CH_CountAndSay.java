package com.futurell.leetcode.A_31_40;

/**
 * @Description: 38. 外观数列
 * @Author: lilei58
 * @Date: Created in 2021/7/18 上午10:16
 *
 * 给定一个正整数 n，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 *
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *  countAndSay(1) = "1"
 *  countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * 前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 *
 * 示例 1：
 *  输入：n = 1
 *  输出："1"
 *  解释：这是一个基本样例。
 *
 * 示例 2：
 *  输入：n = 4
 *  输出："1211"
 *  解释：
 *   countAndSay(1) = "1"
 *   countAndSay(2) = 读 "1" = 一 个 1 = "11"
 *   countAndSay(3) = 读 "11" = 二 个 1 = "21"
 *   countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
 */
public class CH_CountAndSay {

    public static void main(String[] args) {
        String say = countAndSay(4);
        int i = 0;
    }

    /**
     *  思路：双指针
     * 1. 找规律,从 1 怎么到 11,从 11 怎么到 21,从 21 怎么到 1211
     * 2. 只要找到规律代码其实不难写
     */
    public static String countAndSay(int n) {

        // 设置默认值,从 1 开始计算
        StringBuffer cur = new StringBuffer("1");
        // 从 1 开始到 n - 1
        for (int i = 1; i < n; i++) {
            StringBuffer pre = cur;
            cur = new StringBuffer();
            int start = 0, end = 0;
            // 开始遍历前一项，开始描述
            while (end < pre.length()) {
                // 统计重复元素的次数,出现不同元素时,停止计算
                while (end < pre.length() && pre.charAt(start) == pre.charAt(end)) {
                    // 记录出现的次数
                    end++;
                }
                // 元素出现次数与元素进行拼接
                cur = cur.append(end - start).append(pre.charAt(start));
                // 这里更新 start,开始记录下一个元素
                start = end;
            }

        }
        return cur.toString();
    }
}
