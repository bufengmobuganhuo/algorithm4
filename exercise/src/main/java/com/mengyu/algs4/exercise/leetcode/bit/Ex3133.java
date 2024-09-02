package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex3133 {

    public static void main(String[] args) {
        System.out.println(new Ex3133().minEnd(3, 4));
    }

    /**
     * https://leetcode.cn/problems/minimum-array-end/solutions/2759113/wei-yun-suan-jian-ji-xie-fa-pythonjavacg-nw8t
     */
    public long minEnd(int n, int x) {
        // n = n -1
        n--;
        long ans = x;
        // 把n-1的第j个比特位插入到x的第i个比特位上
        int i = 0, j = 0;
        // 还没有插完
        while ((n >> j) > 0) {
            // x的0才可以插入，因为要保证 nums[i] & nums[i - 1] = x，则x上为1的位不能变。
            if (((ans >> i) & 1) == 0) {
                // 把n-1的第j个比特位插入到x的第i个比特位上
                ans |= (long) ((n >> j) & 1) << i;
                j++;
            }
            i++;
        }
        return ans;
    }
}
