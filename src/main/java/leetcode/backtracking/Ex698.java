package leetcode.backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/9/11 10:58 上午
 */
public class Ex698 {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        Ex698 ex698 = new Ex698();
        System.out.println(ex698.canPartitionKSubsets1(nums, 4));
    }


    /**
     * 解法一：使用回溯算法
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        int rightPtr = nums.length - 1;
        // 如果最大元素>target，则无法组成k个相等子集
        if (nums[rightPtr] > target) {
            return false;
        }
        while (rightPtr >= 0 && nums[rightPtr] == target) {
            rightPtr--;
            k--;
        }
        return backtracking(new int[k], nums, rightPtr, target);
    }

    /**
     * @param groups：k个子集
     * @param nums
     * @param rightPtr
     * @param target
     * @return
     */
    private boolean backtracking(int[] groups, int[] nums, int rightPtr, int target) {
        if (rightPtr < 0) {
            return true;
        }
        int value = nums[rightPtr--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + value <= target) {
                groups[i] += value;
                if (backtracking(groups, nums, rightPtr, target)) {
                    return true;
                }
                groups[i] -= value;
            }
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }

}
