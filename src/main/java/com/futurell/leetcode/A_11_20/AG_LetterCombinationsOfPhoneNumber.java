package com.futurell.leetcode.A_11_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 17. 电话号码的字母组合
 * @Author: lilei58
 * @Date: Created in 2021/4/27 7:59 下午
 *
 * 示例 1：
 *  输入：digits = "23"
 *  输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 *  输入：digits = ""
 *  输出：[]
 *
 * 示例 3：
 *  输入：digits = "2"
 *  输出：["a","b","c"]
 */
public class AG_LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        List<String> list = letterCombinations("23");
        int i = 0;
    }

    /**
     *  思路: 回溯法
     * 1. 每次都循环
     */
    public static List<String> letterCombinations(String digits) {
        // 返回数据
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }

        // 数据对应
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        // 回溯方法
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    /**
     * 回溯方法
     * @param combinations 返回数据
     * @param phoneMap     号码对应字母
     * @param digits       查询字符串
     * @param index        下标开始
     * @param sb           组装字符串
     * @return
     */
    private static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer sb) {
        if (index == digits.length()) {
            combinations.add(sb.toString());
        } else {
            // 分离单独字符
            char ch = digits.charAt(index);
            String letters = phoneMap.get(ch);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                sb.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, sb);
                sb.deleteCharAt(index);
            }
        }
    }
}
