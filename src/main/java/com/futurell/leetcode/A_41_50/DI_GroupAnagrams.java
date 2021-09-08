package com.futurell.leetcode.A_41_50;import java.util.*;/** * @Description: 49. 字母异位词分组 * @Author: lilei58 * @Date: Created in 2021/9/8 上午8:01 * 给你一个字符串数组，请你将"字母异位词"组合在一起。可以按任意顺序返回结果列表。 * "字母异位词"是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。 * * 示例 1: *  输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"] *  输出: [["bat"],["nat","tan"],["ate","eat","tea"]] * * 示例 2: *  输入: strs = [""] *  输出: [[""]] * * 示例 3: *  输入: strs = ["a"] *  输出: [["a"]] */public class DI_GroupAnagrams {    public static void main(String[] args) {        List<List<String>> lists = groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});        int i = 0;    }    /**     *  思路: 判断字母异位词,字符串按照字符排序,排序后的字符串相等,那么就是字母异位词     * 1. 排序 --- groupAnagrams     * 2. 计数 --- groupAnagrams2     */    public static List<List<String>> groupAnagrams(String[] strs) {        // 记录结果        Map<String, List<String>> map = new HashMap<>();        for (String str : strs) {            // 将字符变成字符数组            char[] array = str.toCharArray();            // 排序            Arrays.sort(array);            // 将排序后的字符数据变成字符串            String key = new String(array);            // 获取相关key的value值,如果不存在则默认返回空集合            List<String> list = map.getOrDefault(key, new ArrayList<>());            // 添加            list.add(str);            map.put(key, list);        }        // 返回数据        return new ArrayList<>(map.values());    }    public static List<List<String>> groupAnagrams2(String[] strs) {        // 记录结果        Map<String, List<String>> map = new HashMap<>();        for (String str : strs) {            // 表示26个英文字母            int[] counts = new int[26];            // 记录当前字符串的每个字符出现在字数组的位置            for (int i = 0; i < str.length(); i++) {                counts[str.charAt(i) - 'a']++;            }            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键            StringBuffer sb = new StringBuffer();            for (int i = 0; i < 26; i++) {                if (counts[i] != 0) {                    sb.append((char) ('a' + i));                    sb.append(counts[i]);                }            }            // 将key变成字符串            String key = sb.toString();            // 获取相关key的value值,如果不存在则默认返回空集合            List<String> list = map.getOrDefault(key, new ArrayList<>());            // 添加            list.add(str);            map.put(key, list);        }        // 返回数据        return new ArrayList<>(map.values());    }}