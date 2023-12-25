package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex2866 {

    public static void main(String[] args) {
        List<Integer> maxHeights = Arrays.asList(5, 3, 4, 1, 1);
        System.out.println(new Ex2866().maximumSumOfHeights(maxHeights));
    }

    /**
     * 实际上是要找到一个‘峰顶i’，峰顶左边([0, i])是非递减的，峰顶右边([i, n - 1])是非递增的(实际上从[n-1, i]也是非递减的)。
     * 对于左边：
     *  定义prefix[i]为以heights[i]为峰顶时，它的左边的高度和。
     *  此时使用一个非递减的单调栈，如果遇到stack.peek > heights[j]，即[stack.peek, j]的高度只能是heights[j]，
     *  所以prefix[j] = prefix[stack.peek] + (j - stack.peek) * heights[j]
     * 对于右边:
     *  定义suffix[i]为以heights[i]为峰顶，它右边的高度和。
     *  此时使用一个非递减的单调栈，如果遇到stack.peek > heights[j]，即[j, stack.peek]的高度只能是heights[j]，
     *  所以suffix[j] = suffix[stack.peek] + (stack.peek - j) * heights[j]
     * 以i为峰顶的高度和 = prefix[i] + suffix[i] - maxHeights[i]，heights[i]最大能取到maxHeights[i]，并且两个数组加了2遍
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        Stack<Integer> left = new Stack<>();
        long[] prefix = new long[n];
        for (int i = 0; i < n; i++) {
            while (!left.isEmpty() && maxHeights.get(left.peek()) > maxHeights.get(i)) {
                left.pop();
            }
            if (left.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = prefix[left.peek()] + (long)(i - left.peek()) * maxHeights.get(i);
            }
            left.push(i);
        }
        Stack<Integer> right = new Stack<>();
        long[] suffix = new long[n];
        long res = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (!right.isEmpty() && maxHeights.get(right.peek()) > maxHeights.get(i)) {
                right.pop();
            }
            if (right.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[right.peek()] + (long) (right.peek() - i) * maxHeights.get(i);
            }
            right.push(i);
            res = Math.max(res, suffix[i] + prefix[i] - maxHeights.get(i));
        }
        return res;
    }
}
