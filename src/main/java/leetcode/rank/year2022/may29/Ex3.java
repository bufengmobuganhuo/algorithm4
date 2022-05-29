package leetcode.rank.year2022.may29;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yuzhang
 * @date 2022/5/29 10:45
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {9,1,2,3,4,1,5};
        System.out.println(new Ex3().totalSteps(nums));
    }

    public int totalSteps(int[] nums) {
        int ret = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int num : nums) {
            // step表示在第几步删除
            int step = 0;
            // 如果当前元素比栈顶元素大，则可能需要删除（如果左侧有更大的元素则需要删除）。
            // 即使需要删除，也需要在下一轮删除，所以此时需要记录前一个元素在第几步删除，当前元素需要删除的话，加1即可。
            while (stack.size() > 0 && stack.peek()[0] <= num) {
                step = Math.max(step, stack.peek()[1]);
                stack.pop();
            }
            // 如果栈不为空，说明当前元素左侧有更大的值，当前元素需要删除，则更新step
            if (stack.size() != 0) {
                ++step;
            } else { // 如果栈为空，说明当前元素是已扫描元素中的最大值，它不需要被删除，将step置为0。这一步骤不操作也可，反正后续用不到。
                step = 0;
            }
            ret = Math.max(ret, step);
            stack.push(new int[]{num, step});
        }
        return ret;
    }
}
