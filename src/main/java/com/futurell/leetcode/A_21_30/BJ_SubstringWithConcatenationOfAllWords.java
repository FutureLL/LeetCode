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

        List<Integer> list = findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 滑动窗口 + 哈希表 --- findSubstring
     * 2. 判断每个子串是否符合,每次移动一个下标位置 --- findSubstring2
     * 3. 判断每个子串是否符合,每次移动一个字符串 --- findSubstring3
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> allWords = new HashMap<>();
        for (String word : words) {
            allWords.put(word, allWords.getOrDefault(word, 0) + 1);
        }
        int wordNum = words.length;
        int wordLen = words[0].length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            Map<String, Integer> subWords = new HashMap<>();
            int index = i;
            while (index < i + wordNum * wordLen) {
                String curWord = s.substring(index, index + wordLen);
                if (!allWords.containsKey(curWord) || subWords.get(curWord).equals(allWords.get(curWord))) {
                    break;
                }
                subWords.put(curWord, subWords.getOrDefault(curWord, 0) + 1);
                index += wordLen;
            }
            if (index == i + wordNum * wordLen) {
                res.add(i);
            }
        }
        return res;
    }

    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }

        // HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
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

    public static List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) {
            return res;
        }

        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<>();

        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        // 将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<>();
            // 记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            int num = 0;
            // 每次移动一个单词长度
            for (int i = j; i < s.length() - words.length * wordLen + 1; i = i + wordLen) {
                // 防止情况三移除后,情况一继续移除
                boolean hasRemoved = false;
                while (num < words.length) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        // 出现情况三,遇到了符合的单词,但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            hasRemoved = true;
                            int removeNum = 0;
                            // 一直移除单词,直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            // 加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            num = num - removeNum + 1;
                            //这里依旧是考虑到了最外层的 for 循环,看情况二的解释
                            i = i + (removeNum - 1) * wordLen;
                            break;
                        }
                        // 出现情况二,遇到了不匹配的单词,直接将 i 移动到该单词的后边（但其实这里
                        // 只是移动到了出现问题单词的地方,因为最外层有 for 循环,i 还会移动一个单词
                        // 然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == words.length) {
                    res.add(i);

                }
                // 出现情况一,子串完全匹配,我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }

            }

        }
        return res;
    }
}
