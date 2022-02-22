package leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex540 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 3, 3, 4, 4, 8, 8};
        System.out.println(new Ex540().singleNonDuplicate(nums));
    }
    public int singleNonDuplicate(int[] nums) {
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr <= rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            int mid = nums[midPtr];
            int before = midPtr > 0 ? nums[midPtr - 1] : -1;
            int after = midPtr < nums.length - 1 ? nums[midPtr + 1] : -1;
            if (mid != before && mid != after) {
                return mid;
            }
            if (mid == before) {
                if ((midPtr + 1) % 2 != 0) {
                    rightPtr = midPtr;
                } else {
                    leftPtr = midPtr + 1;
                }
            } else {
                if ((nums.length - midPtr) % 2 != 0) {
                    leftPtr = midPtr;
                } else {
                    rightPtr = midPtr - 1;
                }
            }
        }
        return -1;
    }
}
