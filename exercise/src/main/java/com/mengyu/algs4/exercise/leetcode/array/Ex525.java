package com.mengyu.algs4.exercise.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/6/5 上午10:09
 * TODO
 */
public class Ex525 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1};
        Ex525 ex525 = new Ex525();
        System.out.println(ex525.findMaxLength(nums));
    }

    /**
     * 1. 由于求1和0的数量相同，则1的数量-0的数量=0，将数组中的0看作-1，则转化为求最长的连续子数组，其和为0
     * 2. 使用一个前缀数组prefixSum，preSum[i]表示从nums[0]到nums[i]的和，则从i+1~j的和=prefixSum[j]-prefixSum[i]，目的就是求这个和为0
     * 3. 在遍历过程中，可以使用一个Map存储（前缀和 -> 第一次出现该和的下标），在遍历过程中可以从Map中查找前缀和=当前前缀和的下标j,则此时符合条件的连续子数组的长度=i-j
     * 因为此时prefixSum[j]-prefixSum[i]=0，
     * 4. 为了节省空间，可以使用两个变量来替代prefixSum，counter表示从0到当前位置的前缀和，初始情况下counter=0时，其下标=-1
     */
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                counter--;
            } else {
                counter++;
            }
            if (map.containsKey(counter)) {
                int preIdx = map.get(counter);
                res = Math.max(res, i - preIdx);
            } else {
                map.put(counter, i);
            }
        }
        return res;
    }
}
