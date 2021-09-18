package leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex162 {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            int mid = nums[midPtr];
            int left = midPtr > 0 ? nums[midPtr - 1] : Integer.MIN_VALUE;
            int right = midPtr < nums.length - 1 ? nums[midPtr + 1] : Integer.MIN_VALUE;
            if (mid > left && mid > right) {
                return midPtr;
            } else if (mid < right) {
                leftPtr = midPtr + 1;
            } else if (mid > right) {
                rightPtr = midPtr - 1;
            }
        }
        return -1;
    }
}
