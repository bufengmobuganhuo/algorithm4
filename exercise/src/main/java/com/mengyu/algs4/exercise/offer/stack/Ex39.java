package com.mengyu.algs4.exercise.offer.stack;

import java.util.Stack;

public class Ex39 {
    public static void main(String[] args) {

    }
    /**
     * 单调栈 + 双向遍历
     * 1. 对于一个高度height，我们可以分别向左右找第一个低于height的高度的下标，从而知道以height组成的矩形面积为：
     *  height * (right - left - 1)
     * 2. 从左到右遍历，使用一个栈底到栈顶递增的单调栈，那么对于一个要入栈的元素height（以要入栈的元素高度为准找）：
     *   (1)height > stack.peek: 左边比他低的第一个元素就是栈顶元素，记录到 left数组中
     *   (2)height <= stack.peek：一直出栈，直到满足条件为止
     * 3. 同理，从右到左遍历，也可以找到left和right
     * 4. 最后使用height[i] * (right[i] - left[i])可以求出最大面积
     */
    public int largestRectangleArea1(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        // 哨兵，最左边有一个高度为0的柱子
        stack.push(-1);
        int[] left = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.peek();
            stack.push(i);
        }

        stack.clear();
        // 哨兵，最右边又一个高度为0的柱子
        stack.push(heights.length);
        int[] right = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            while (stack.peek() != heights.length && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.peek();
            stack.push(i);
        }

        // 筛选最大高度
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] - 1));
        }
        return maxArea;
    }

    /**
     * 单调栈 + 单向遍历
     * 1. 从左到右遍历，维护一个从栈底到栈顶递增的单调栈，当需要入栈一个高度为height的元素时：
     *  (1) height > stack.peek：直接入栈
     *  (2) height <= stack.peek：可以知道对于栈顶元素（假设高度为height1），
     *      左边第一个低于height1的为栈顶元素的下一个栈顶元素，右边第一个低于height1的是要入栈的元素，
     *      此时可以计算出以height1为高度的矩形面积=heights[stack.peek] * (i - stack.peek(peek) - 1)
     * 2. 遍历完成后，栈中可能还剩下递增的元素，则对于栈顶元素（设其高度为height）：
     * 可知右边没有低于height的柱子，左边第一个低于height的柱子是栈顶的下一个元素，则面积为：
     * height * (heights.length - stack.peek(peek) - 1)
     *
     */
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        // 假设左边又一个高度为0的柱子
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        // 处理栈中递增的元素
        while (stack.peek() != -1) {
            int height = heights[stack.pop()];
            int width = heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }

    /**
     * 也是单调栈 + 单向遍历，不同的是为heights左右两边加入两个高度为0的哨兵柱子，
     * 这样就避免了处理栈中递增元素的过程（因为是递增的，所以到达最后一个柱子的时候，一定会全部清空栈）
     */
    public int largestRectangleArea3(int[] heights) {
        int[] dummy = new int[heights.length + 2];
        System.arraycopy(heights, 0, dummy, 1, heights.length);
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        stack.push(0);
        for (int i = 1; i < dummy.length; i++) {
            // 因为第一个元素高度=0，则栈一定不会为空
            // 因为首尾加入了高度为0的柱子，则不能有"="
            while (dummy[stack.peek()] > dummy[i]) {
                int height = dummy[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, width * height);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
