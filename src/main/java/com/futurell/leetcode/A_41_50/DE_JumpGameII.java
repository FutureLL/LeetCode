package com.futurell.leetcode.A_41_50;

/**
 * @Description: 45. 跳跃游戏 II
 * @Author: lilei58
 * @Date: Created in 2021/8/13 3:55 下午
 * 给你一个非负整数数组 nums，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 示例 1:
 *  输入: nums = [2,3,1,1,4]
 *  输出: 2
 *  解释: 跳到最后一个位置的最小跳跃数是 2。
 *       从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 示例 2:
 *  入: nums = [2,3,0,1,4]
 *  输出: 2
 */
public class DE_JumpGameII {

    public static void main(String[] args) {
        int jump = jump3(new int[]{2, 3, 0, 1, 4});
        int i = 0;
    }

    /**
     *  思路:
     * 1. 双循环检测 --- jump 超时
     * 2. 反向查找出发位置 --- jump2
     * 3. 正向查找可到达的最大位置 --- jump3
     */
    public static int jump(int[] nums) {
        // 跳的总格数
        int jumpTotalCount = nums.length - 1;
        // 跳的总次数
        int count = 1;
        // 记录跳的格数
        int jumpCount = nums[0];
        // 记录规定范围内下标值最大值
        int maxValue = 0;

        // 每跳一次都要得到所能到达的最大值
        for (int i = 0; i < nums.length - 1;) {
            int num = nums[i];
            // 获取 i - i+num 下标中的最大值
            for (int j = i + 1; j < i + num; j++) {
                if (nums[j] > maxValue && jumpCount + maxValue < jumpTotalCount) {
                    i = j;
                    maxValue = nums[j];
                    count++;
                }
            }
            if (jumpCount + maxValue >= jumpTotalCount) {
                return count;
            }
        }
        return 0;
    }

    public static int jump2(int[] nums) {
        // 结尾开始向前查找,也相当于跳的总格数
        int position = nums.length - 1;
        // 记录跳的次数
        int steps = 0;

        // 总次数大于0那么继续循环
        while (position > 0) {
            // 从头开始找到第一个可以达到指定位置的下标
            for (int i = 0; i < position; i++) {
                // 如果可以达到
                if (i + nums[i] >= position) {
                    // 更改 position 的位置,也就是下标
                    position = i;
                    // 跳的次数+1
                    steps++;
                    // 结束当前循环
                    break;
                }
            }
        }
        return steps;
    }

    public static int jump3(int[] nums) {
        // 记录下一个位置的下标
        int end = 0;
        // 每次跳的最大步数
        int maxPosition = 0;
        // 记录跳的次数
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 得到可跳步数内的下一次可以跳跃的最大步数
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 到达下一次跳跃的位置
            if (i == end) {
                // 到达的下一个位置
                end = maxPosition;
                // 跳的次数+1
                steps++;
            }
        }
        return steps;
    }
}
