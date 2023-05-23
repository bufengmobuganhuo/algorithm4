package com.mengyu.algs4.exercise.leetcode.rank.year2022.october16;

/**
 * @author yuzhang
 * @date 2022/10/16 11:05
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1};
        System.out.println(new Ex4().countSubarrays(nums, 1, 1));
    }
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int len = nums.length;
        long count = 0, lastAdd = 0;
        // 记录截止到位置i，符合条件的数字有几个（minK <= num <= maxK），不包含当前数字
        int[] records = new int[len];
        // 分别记录当前最右边最小值的位置和最大值的位置
        int minIdx = -1, maxIdx = -1;
        // 初始化数据
        records[0] = nums[0] >= minK && nums[0] <= maxK ? 0 : -1;
        minIdx = nums[0] == minK ? 0 : -1;
        maxIdx = nums[0] ==maxK ? 0 : -1;
        count = minK == maxK ? 1 : 0;
        for (int i = 1; i < len; i++) {
            // 如果遍历过程中遇到最小值
            if (nums[i] == minK) {
                // 更新最小值索引
                minIdx = i;
                // 更新records个数
                records[i] = records[i - 1] + 1;
                // 如果最大最小值相等，则也应该更新maxIdx
                if (minK == maxK) {
                    maxIdx = i;
                }
                // 可以组成的子数组数 = 截止到当前最右边最大值符合条件的数 + 当前最右边最大值和当前最小值组成的子数组 = records[maxIdx] + 1
                lastAdd = maxIdx == -1 ? 0 : records[maxIdx] + 1;
                count += lastAdd;
            } else if (nums[i] == maxK) {
                maxIdx = i;
                lastAdd = minIdx == -1 ? 0 :  records[minIdx] + 1;
                count += lastAdd;
                records[i] = records[i - 1] + 1;
            } else if (nums[i] > maxK || nums[i] < minK) {
                minIdx = -1;
                maxIdx = -1;
                lastAdd = 0;
                records[i] = -1;
            } else {
                // 如果当前数字符合条件，则也可以组成子数组
                records[i] = records[i - 1] + 1;
                // 个数=上一次遇到的最大/最小值时组成的子数组数
                count += lastAdd;
            }
        }

        return count;
    }
}
