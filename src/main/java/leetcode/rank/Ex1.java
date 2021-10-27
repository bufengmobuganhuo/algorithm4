package leetcode.rank;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/9/4 下午10:33
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums = {1, -1, 4};
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.findMiddleIndex(nums));
    }

    public int findMiddleIndex(int[] nums) {
        int[] prefixSum = new int[nums.length + 2];
        for (int i = 1; i < nums.length + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        prefixSum[nums.length + 1] = prefixSum[nums.length];
        int idx = -1;
        for (int i = 1; i < nums.length + 1; i++) {
            if (prefixSum[i - 1] == prefixSum[nums.length + 1] - prefixSum[i]) {
                idx = i - 1;
                break;
            }
        }
        return idx;
    }
}
