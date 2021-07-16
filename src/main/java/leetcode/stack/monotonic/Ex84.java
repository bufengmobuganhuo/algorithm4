package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex84 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        Ex84 ex84 = new Ex84();
        System.out.println(ex84.largestRectangleArea(heights));
    }

    /**
     * 1. 对于heights[i]，只需要找到左边第一个heights[left]>heights[i]和右边第一个heights[right]>heights[i]，就可以找到以heights[i]为高度组成的矩形面积
     * 2. 定义一个从栈底到栈顶严格递增的单调栈，对于一个栈中的元素i，可以知道其左边第一个<heights[i]的就是栈中向栈底找的下一个元素
     * 3. 当要放入的元素heights[i] <= stack.peek()，则可以知道右边第一个<heights[stack.peek()]是heights[i]
     * 4. 可以将上述位置保存到两个数组中（left,right），则s = heights[i] * (right - left - 1)，因为向两边多取了一个
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int[] left = new int[len], right = new int[len];
        // 如果heights单调递增，则最右边的是n，多取一个
        Arrays.fill(right, len);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
                right[stack.pop()] = i;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            int width = right[i] - left[i] - 1;
            int height = heights[i];
            res = Math.max(res, width * height);
        }
        return res;
    }
}
