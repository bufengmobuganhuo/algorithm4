package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex42_1 {

    public int trap3(int[] height) {
        int n = height.length;
        int ans = 0;
        int lMax = 0, rMax = 0;
        for (int l = 0, r = n - 1; l < r;) {
            // 截止到l，左边的最大值
            lMax = Math.max(lMax, height[l]);
            // 截止到r, 右边的最大值
            rMax = Math.max(rMax, height[r]);
            if (lMax < rMax) {
                // 左边的比右边的小，那能达到的最高水位就是lMax
                ans += lMax - height[l];
                l++;
            } else {
                ans += rMax - height[r];
                r--;
            }
        }
        return ans;
    }

    public int trap2(int[] height) {
        int n = height.length;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int midHeight = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int leftHeight = height[stack.peek()];
                int rightHeight = height[i];
                int width = i - stack.peek() - 1;
                ans += (Math.min(leftHeight, rightHeight) - midHeight) * width;
            }
            stack.push(i);
        }
        return ans;
    }

    public int trap1(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
