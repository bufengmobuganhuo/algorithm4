package leetcode.prefix_sum;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex238 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        Ex238 ex238 = new Ex238();
        System.out.println(Arrays.toString(ex238.productExceptSelf(nums)));
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length, post = 1;
        int[] answer = new int[len];
        // 初始条件
        answer[0] = nums[0];
        // 让answer临时变成一个前缀数组
        for (int i = 1; i < len; i++) {
            answer[i] = answer[i - 1] * nums[i];
        }
        for (int i = len - 1; i > 0; i--) {
            answer[i] = post * answer[i - 1];
            post *= nums[i];
        }
        // 循环结束时，post就是answer[0]的值
        answer[0] = post;
        return answer;
    }
}
