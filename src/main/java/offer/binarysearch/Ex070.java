package offer.binarysearch;

/**
 * @author yu zhang
 */
public class Ex070 {
    public static void main(String[] args) {
        int[] nums = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(new Ex070().singleNonDuplicate(nums));
    }

    public int singleNonDuplicate(int[] nums) {
        int leftPtr = 0, len = nums.length, rightPtr = len - 1;
        while (leftPtr <= rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            int left = mid > 0 ? nums[mid - 1] : Integer.MIN_VALUE;
            int right = mid < len - 1 ? nums[mid + 1] : Integer.MIN_VALUE;
            if (nums[mid] != left && nums[mid] != right) {
                return nums[mid];
            } else if (nums[mid] == left) {
                if ((mid + 1) % 2 == 0) {
                    leftPtr = mid + 1;
                } else {
                    rightPtr = mid;
                }
            } else {
                if (mid % 2 == 0) {
                    leftPtr = mid;
                } else {
                    rightPtr = mid - 1;
                }
            }
        }
        return -1;
    }
}
