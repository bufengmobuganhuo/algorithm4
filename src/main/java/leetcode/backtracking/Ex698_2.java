package leetcode.backtracking;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/8/7 上午10:44
 * TODO
 */
public class Ex698_2 {
    public static void main(String[] args) {
        Ex698_2 ex698_2 = new Ex698_2();
        int[] nums = {4, 4, 6, 2, 3, 8, 10, 2, 10, 7};
        System.out.println(ex698_2.canPartitionKSubsets(nums, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int targetSum = sum / k;
        Arrays.sort(nums);
        int rightPtr = nums.length - 1;
        if (nums[rightPtr] > targetSum) {
            return false;
        }
        while (rightPtr >= 0 && nums[rightPtr] == targetSum) {
            rightPtr--;
            k--;
        }
        return backtracking(new int[k], nums, rightPtr, targetSum);
    }

    private boolean backtracking(int[] group, int[] nums, int rightPtr, int targetSum) {
        if (rightPtr < 0) {
            return true;
        }
        int value = nums[rightPtr--];
        for (int i = 0; i < group.length; i++) {
            if (group[i] + value <= targetSum) {
                group[i] += value;
                if (backtracking(group, nums, rightPtr, targetSum)) {
                    return true;
                }
                group[i] -= value;
            }
            if (group[i] == 0) {
                break;
            }
        }
        return false;
    }
}
