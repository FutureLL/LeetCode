package com.futurell.leetcode.A_21_30;

/**
 * @Description: 28. 实现 strStr()
 * @Author: lilei58
 * @Date: Created in 2021/6/22 8:38 上午
 *
 * 实现 strStr() 函数。
 * 给你两个字符串 haystack 和 needle，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回 -1。
 *
 * 示例 1：
 *  输入：haystack = "hello", needle = "ll"
 *  输出：2
 *
 * 示例 2：
 *  输入：haystack = "aaaaa", needle = "bba"
 *  输出：-1
 *
 * 示例 3：
 *  输入：haystack = "", needle = ""
 *  输出：0
 */
public class BH_ImplementStrstr {

    public static void main(String[] args) {

        int index = strStr3("bcbcbcbcbea", "bcbcbea");
        int i = 0;
    }

    /**
     *  思路:
     * 1. 使用 contains() 方法 --- strStr
     * 2. 暴力解法(BF算法 Brute Force) --- strStr2
     * 3. KMP算法 Knuth-Morris-Pratt --- strStr3
     */
    public static int strStr(String haystack, String needle) {

        // 判断是否存在
        boolean contains = haystack.contains(needle);
        // 不存在直接返回-1
        if (!contains) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int start = i;
            int end = i + needle.length();

            String substring = haystack.substring(start, end);
            if (substring.contains(needle)) {
                return i;
            }
        }

        // haystack为空字符串""
        return 0;
    }

    /**
     * 暴力解法(BF算法 Brute Force) --- strStr2
     */
    public static int strStr2(String haystack, String needle) {
        // 对于 hello 来说 i 最多可以到达 第二个 l
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                // 对应位置如果不想等,那么结束循环
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * KMP算法 Knuth-Morris-Pratt --- strStr3
     * 最长公共前后缀
     */
    public static int strStr3(String haystack, String needle) {
        // 两种特殊情况
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0) {
            return -1;
        }
        // char 数组
        char[] haystackChar = haystack.toCharArray();
        char[] needleChar = needle.toCharArray();

        //返回下标
        return kmp(haystackChar, haystackChar.length, needleChar, needleChar.length);

    }

    public static int kmp(char[] haystackChar, int haystackLen, char[] needleChar, int needleLen) {
        // 获取 next 数组
        int[] next = next(needleChar, needleLen);
        int j = 0;
        for (int i = 0; i < haystackLen; ++i) {
            // 发现不匹配的字符,然后根据 next 数组移动指针,移动到最大公共前后缀的
            // * 直接移动模式串,将前缀移动到后缀 *
            // 前缀的后一位,和咱们移动模式串的含义相同
            while (j > 0 && haystackChar[i] != needleChar[j]) {
                // 获取到最长公共前后缀中,后缀的开头下标
                // +1 表示从下一个位置开始计算
                j = next[j - 1] + 1;
                //超出匹配范围时,可以直接返回不存在
                if (needleLen - j + i > haystackLen) {
                    return -1;
                }
            }
            // 如果相同就将指针同时后移一下,比较下个字符
            if (haystackChar[i] == needleChar[j]) {
                ++j;
            }
            // 遍历完整个模式串,返回模式串的起点下标
            if (j == needleLen) {
                return i - needleLen + 1;
            }
        }
        return -1;
    }

    /**
     * next 数组存的我们最长公共前后缀中,前缀的结尾字符下标,或后缀的开头字符这样好理解一些。
     * 前缀: 不包含最后一个字符的所有以第一个字符开头的连续子串
     * 后缀: 不包含第一个字符的所有以最后一个字符结尾的连续子串
     *
     * b   c   b   c   b   e   a
     * 前缀子串    前缀结尾下标    最长公共前后缀,前后缀结尾字符下标    next[]
     * b          0            0        -1(无公共前后缀)       next[0] = -1
     * bc         1            0        -1(无公共前后缀)       next[1] = -1
     * bcb        2            1 b      0                    next[2] = 0
     * bcbc       3            2 bc     1                    next[3] = 1
     * bcbcb      4            3 bcb    2                    next[4] = 2
     * bcbcbe     5            0        -1(无公共前后缀)       next[5] = -1
     * bcbcbea    6            0        -1(无公共前后缀)       next[6] = -1
     */
    public static int[] next(char[] needleChar, int len) {
        //定义 next 数组
        int[] next = new int[len];
        // 初始化
        next[0] = -1;
        // k 表示最长公共前后缀,前后缀结尾字符下标
        int k = -1;
        for (int i = 1; i < len; ++i) {
            // 我们此时知道了 [0,i-1] 的最长前后缀,但是 k+1 的指向的值和 i 不相同时,我们则需要回溯
            // 因为 next[k] 就时用来记录子串的最长公共前后缀的尾坐标（即长度）
            // 就要找 k+1 前一个元素在 next 数组里的值,即 next[k+1]
            while (k != -1 && needleChar[k + 1] != needleChar[i]) {
                k = next[k];
            }
            // 相同情况，就是 k的下一位,和 i 相同时,此时我们已经知道 [0,i-1] 的最长前后缀
            // 然后 k - 1 又和 i 相同,最长前后缀加1,即可
            if (needleChar[k + 1] == needleChar[i]) {
                ++k;
            }
            next[i] = k;

        }
        return next;
    }
}
