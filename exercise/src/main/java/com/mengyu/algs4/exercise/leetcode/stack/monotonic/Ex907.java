package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/10/20 9:09 上午
 * TODO
 */
public class Ex907 {
    /**
     * 1. 对于A中的某个元素A[i]，如果要求包含A[i]且以A[i]为最小元素的所有子数组的个数n[i]，
     * 则元素对于答案ans的贡献是n[i]*A[i]
     * 2. 先求出包含A[i]并以A[i]为最小元素的最长子数组，如果A[i]左边第一个小于A[i]的元素为A[left]
     * 右边第一个小于A[i]的元素为A[right]，则可以组成的子数组的个数为n[i]=(i-left)*(right-i)
     * （排列组合，i左边有i-left种选择，i右边有right-i种选择）
     * 3. 此时可以使用从栈底 -> 栈顶 递增的单调栈，例如[3,1,2,4]，首先入栈3
     * ｜   ｜
     * -----，然后入栈1，此时不符合单调栈条件，则可以知道3的左边第一个小于其的元素不存在（栈空），右边第一个小于其的元素为1
     * ｜ 3 ｜
     * ------
     *
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {
        long ans = 0;
        int mod = 1000000007;
        Stack<Integer> stack = new Stack<>();
        // 哨兵
        stack.push(-1);
        for (int i = 0; i < A.length; i++) {
            // 找到了当前栈顶元素的右边第一个比他小的元素A[i]
            while (stack.peek() != -1 && A[i] <= A[stack.peek()]) {
                int index = stack.pop();
                // 左边比他小的第一个元素的索引
                int left = index - stack.peek();
                int right = i - index;
                // 用的取余的性质
                ans = (ans + (long) A[index] * left % mod * right % mod) % mod;
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int index = stack.pop();
            int left = index - stack.peek();
            int right = A.length - index;
            ans = (ans + (long) A[index] * left % mod * right % mod) % mod;
        }
        return (int) ans;
    }
}
