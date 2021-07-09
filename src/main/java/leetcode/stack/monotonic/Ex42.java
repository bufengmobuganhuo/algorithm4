package leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex42 {
    public static void main(String[] args) {
        Ex42 ex42 = new Ex42();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        ex42.trap(height);
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 栈底 -> 栈顶：大 -> 小
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                // 栈顶元素
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 栈顶的下一个元素left，则[left,top,i]可以组成一个容器, 最低的是top
                int left = stack.peek();
                int width = i - left - 1;
                int h = Math.min(height[left], height[i]) - height[top];
                ans += h * width;
            }
            stack.push(i);
        }
        return ans;
    }
}
