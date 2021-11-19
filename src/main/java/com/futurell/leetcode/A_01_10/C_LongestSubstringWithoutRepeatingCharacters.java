package com.futurell.leetcode.A_01_10;

import java.util.*;

/**
 * @Description: 3. 无重复字符的最长子串
 * @Author: lilei58
 * @Date: Created in 2021/2/23 4:57 下午
 *
 * 给定一个字符串,请你找出其中不含有重复字符的'最长子串'的长度。
 *
 * 示例 1:
 *  输入: s = "abcabcbb"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "abc",所以其长度为 3。
 *
 * 示例 2:
 *  输入: s = "bbbbb"
 *  输出: 1
 *  解释: 因为无重复字符的最长子串是 "b",所以其长度为 1。
 *
 * 示例 3:
 *  输入: s = "pwwkew"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "wke",所以其长度为 3。
 *       请注意,你的答案必须是'子串'的长度,"pwke" 是一个子序列,不是子串。
 *
 * 示例 4:
 *  输入: s = ""
 *  输出: 0
 */

public class C_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        // 优秀的思路
        int length = lengthOfLongestSubstring("abcabcbb");
        // 自己的思路1
        int lengthMy1 = lengthOfLongestSubstringMyself1("abba");
        // 自己的思路2
        int lengthMy2 = lengthOfLongestSubstringMyself2("abba");
        int i = 0;
    }

    /**
     *  思路: 滑动窗口
     * 1. 暴力解法时间复杂度较高,会达到 O(n^2),故而采取滑动窗口的方法降低时间复杂度
     * 2. 定义一个 Map 数据结构存储 (k,v),其中 key 值为字符,value 值为字符位置 +1,+1 表示从字符位置后一个才开始不重复
     * 3. 我们定义不重复子串的开始位置为 start,结束位置为 end
     * 4. 随着 end 不断遍历向后,会遇到与 [start,end] 区间内字符相同的情况,此时将字符作为 key 值,获取其 value 值,并更新 start,此时 [start,end] 区间内不存在重复字符
     * 5. 无论是否更新 start,都会更新其 Map 数据结构和结果 ans。
     * 6. 时间复杂度: O(n)
     */
    public static int lengthOfLongestSubstring(String s) {
        // 记录最大不重复子串
        int ans = 0;
        // 定义一个 Map 存储数据
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                // 重新计算起始位置
                start = Math.max(map.get(ch), start);
            }
            // 计算当前最长子串
            ans = Math.max(ans, end - start + 1);
            // 存入 Map 中,有数据则更新 value,无数据则插入
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    /**
     *  我的思路:
     * 1. 遍历过程中如果不一样,那么存到 List 中,并计算最大子串长度
     * 2. 遇到一样的删除相同元素之前的所有元素(包括相同的那个元素)(这里用了两次反转),添加重复的那个元素到 List 中,计算最大子串长度
     */
    public static int lengthOfLongestSubstringMyself1(String s) {
        // 记录最大值
        int ans = 0;
        // 存储子串
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!list.contains(ch)) {
                list.add(ch);
            } else {
                // 反转 list
                Collections.reverse(list);
                List<Character> temp = new ArrayList<>();
                for (Character character : list) {
                    if (character != s.charAt(i)) {
                        temp.add(character);
                    } else {
                        // 反转 temp
                        Collections.reverse(temp);
                        temp.add(character);
                        list.clear();
                        list.addAll(temp);
                        break;
                    }
                }
            }
            ans = Math.max(list.size(), ans);
        }
        return ans;
    }

    public static int lengthOfLongestSubstringMyself2(String s) {
        // 记录最大不重复子串
        int ans = 0;
        // 定义一个 Map 存储数据
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            // 如果存在重新计算起始位置
            if (map.containsKey(ch)) {
                // 重新计算start起始位置
                start = Math.max(map.get(ch), start);
            }
            map.put(ch, end + 1);
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}
