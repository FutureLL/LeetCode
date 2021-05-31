package com.futurell.leetcode.A_11_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 20. 有效的括号
 * @author: Mr.Li
 * @date: Created in 2021/5/12 8:06
 * @version: 1.0
 * @modified By:
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 *  左括号必须用相同类型的右括号闭合。
 *  左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 *  输入：s = "()"
 *  输出：true
 *
 * 示例 2：
 *  输入：s = "()[]{}"
 *  输出：true
 *
 * 示例 3：
 *  输入：s = "(]"
 *  输出：false
 *
 * 示例 4：
 *  输入：s = "([)]"
 *  输出：false
 *
 * 示例 5：
 *  输入：s = "{[]}"
 *  输出：true
 */
public class AJ_ValidParentheses {

    public static void main(String[] args) {
        boolean valid = isValid("(){}}{");
        int i = 0;
    }

    /**
     *  思路:
     * 1. 栈
     * 2. 去除成对括号法
     */
    public static boolean isValid(String s) {
        List<Character> list = new ArrayList<>();

        if (s.length() == 1 || s.length() == 0) {
            return false;
        }

        list.add(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            char chI = s.charAt(i);
            if (!list.isEmpty() && (chI == ')' && list.get(list.size() - 1).equals('(')
                    || chI == '}' && list.get(list.size() - 1).equals('{')
                    || chI == ']' && list.get(list.size() - 1).equals('['))) {
                list.remove(list.size() - 1);
            } else {
                list.add(chI);
            }
        }
        return list.isEmpty();
    }

    public static boolean isValid2(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            if (s.contains("()")) {
                s = s.replace("()", "");
            }
            if (s.contains("{}")) {
                s = s.replace("{}", "");
            }
            if (s.contains("[]")) {
                s = s.replace("[]", "");
            }
        }
        return s.length() == 0;
    }
}
