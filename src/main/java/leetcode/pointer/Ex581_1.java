package leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex581_1 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 2};
        Ex581_1 ex581 = new Ex581_1();
        System.out.println(ex581.findUnsortedSubarray(nums));
    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // 储存
        int max = nums[0], maxIdx = 0;
        int start = -1, end = -1;
        int pointer = 1;
        while (pointer < nums.length) {
            if (start < 0 && nums[pointer] < nums[pointer - 1]) {
                start = pointer - 1;
                while (start > 0 && nums[start - 1] > nums[pointer]) {
                    start--;
                }
                end = pointer;
            } else if (start >= 0 && nums[pointer] < nums[start]) {
                while (start > 0 && nums[start - 1] > nums[pointer]) {
                    start--;
                }
                end = pointer;
            } else if (nums[pointer] < nums[pointer - 1]) {
                end = pointer;
            } else if (nums[pointer] < max) {
                start = Math.min(start, maxIdx);
                end = pointer;
            }
            if (nums[pointer] > max) {
                max = Math.max(max, nums[pointer]);
                maxIdx = pointer;
            }
            pointer++;
        }
        if (start == -1) {
            return 0;
        }
        return end - start + 1;
    }
}
