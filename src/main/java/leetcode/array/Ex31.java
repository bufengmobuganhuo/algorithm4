package leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/7/15 10:17 上午
 * TODO
 */
public class Ex31 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        Ex31 ex31 = new Ex31();
        ex31.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int i = nums.length - 1;
        // 从右开始找，如果后面的元素>前面的元素，那么这个数可以变得更大
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i > 0) {
            // 从右边开始找到>nums[i-1]的最小数
            int j = nums.length - 1;
            while (j > 0 && nums[j] <= nums[i - 1]) {
                j--;
            }
            exch(nums, i - 1, j);
        }
        // 交换之后，nums[i-1]的位置比nums[j]位置高一些,
        // 此时只要保证nums[i-1]>右边的值就是更大值，
        // 则可以将i-1右边的值反转，让更小的元素在更高位，则是下一个更大值
        reverse(nums, i);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            exch(nums, i, j);
            i++;
            j--;
        }
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
