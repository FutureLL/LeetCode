package com.futurell.leetcode.A_21_30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 30. 串联所有单词的子串
 * @Author: lilei58
 * @Date: Created in 2021/6/29 4:59 下午
 * 给定一个字符串 s 和一些 长度相同 的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 *  输入：s = "barfoothefoobarman", words = ["foo","bar"]
 *  输出：[0,9]
 *  解释：从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar"。
 *       输出的顺序不重要,[9,0] 也是有效答案。
 *
 * 示例 2：
 *  输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *  输出：[]
 *
 * 示例 3：
 *  输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *  输出：[6,9,12]
 */
public class BJ_SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {

        List<Integer> list = findSubstring("barfoothefoobarman", new String[]{"foo","bar"});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 滑动窗口 + 哈希表 --- findSubstring
     * 2. 判断每个子串是否符合,每次移动一个下标位置 --- findSubstring2
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        // 存所有单词
        Map<String, Integer> allWords = new HashMap<>();
        for (String word : words) {
            allWords.put(word, allWords.getOrDefault(word, 0) + 1);
        }

        // 存储满足条件的下标
        List<Integer> res = new ArrayList<>();
        // 循环给定字符串
        for (int i = 0; i < s.length() - words.length * words[0].length() + 1; i++) {
            Map<String, Integer> subWords = new HashMap<>();
            int index = i;
            // 相当于窗口,每次滑动一个单词的长度 index += words[0].length()
            while (index < i + words.length * words[0].length()) {
                // 截取单个单词
                String curWord = s.substring(index, index + words[0].length());
                // 两中情况结束循环:
                // 1. 截取的字符串在allWords中不存在
                // 2. 截取的字符串在allWords中存在 且 子串集合中截取字符串的量等于allWords中该截取字符串中的量
                if (!allWords.containsKey(curWord) || subWords.get(curWord) == allWords.get(curWord)) {
                    break;
                }
                subWords.put(curWord, subWords.getOrDefault(curWord, 0) + 1);
                index += words[0].length();
            }
            if (index == i + words.length * words[0].length()) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 移动一个位置下标
     */
    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }

        // HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            // 获取对应 key 的 value,没有默认为 0
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }

        // 遍历所有子串
        for (int i = 0; i < s.length() - words.length * words[0].length() + 1; i++) {
            // HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            // 判断该子串是否符合
            while (num < words.length) {
                // 截取对应的字符串
                String word = s.substring(i + num * words[0].length(), i + (num + 1) * words[0].length());
                // 判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    // 判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            // 判断是不是所有的单词都符合条件
            if (num == words.length) {
                res.add(i);
            }
        }
        return res;
    }
}
