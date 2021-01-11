package leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/1/8 上午11:10
 * TODO
 */
public class Ex75 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 1};
        Ex75 ex75 = new Ex75();
        ex75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int leftPtr = 0, rightPtr = leftPtr, color = 0;
        while (leftPtr < nums.length - 1 && color < 2) {
            if (nums[rightPtr] == color) {
                exch(nums, leftPtr++, rightPtr);
            }
            rightPtr++;
            if (rightPtr >= nums.length) {
                rightPtr = leftPtr;
                color++;
            }
        }
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
