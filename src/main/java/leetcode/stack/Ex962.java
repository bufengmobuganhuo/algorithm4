package leetcode.stack;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/1/11 上午9:51
 * TODO
 */
public class Ex962 {
    public static void main(String[] args) {
        Ex962 ex962 = new Ex962();
        int[] A = {6, 0, 8, 2, 1, 5};
        System.out.println(ex962.maxWidthRamp(A));
    }

    public int maxWidthRamp(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        // 构建递减的单调栈
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty() || A[stack.peek()] >= A[i]) {
                stack.push(i);
            }
        }
        // 从右边开始查找,如果遇到符合条件的，则从栈中弹出，然后计算宽度
        for (int i = A.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                int idx = stack.pop();
                res = Math.max(res, i - idx);
            }
        }
        return res;
    }
}
