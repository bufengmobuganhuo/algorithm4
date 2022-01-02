package leetcode.rank.year2021.may29;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/5/29 下午10:57
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] nums = {4, 1, 5, 1, 2, 5, 1, 5, 5, 4};
        System.out.println(ex2.minPairSum(nums));
    }

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n / 2; i++) {
            res = Math.max(nums[i] + nums[n - i - 1], res);
        }
        return res;
    }

}
