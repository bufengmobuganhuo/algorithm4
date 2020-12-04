package leetcode.array;

/**
 * @author yuzhang
 * @date 2020/12/2 上午8:56
 * TODO
 */
public class Ex34 {
    public static void main(String[] args) {

    }
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums[0] > target) {
            return new int[]{-1, -1};
        }
        if (nums[nums.length - 1] < target) {
            return new int[]{-1, -1};
        }
        int idx = binarySearch(nums, target);
        if (idx == -1) {
            return new int[]{-1, -1};
        }
        int leftPtr = idx, rightPtr = idx;
        while (leftPtr >= 0 && nums[leftPtr] == target) {
            leftPtr--;
        }
        while (rightPtr < nums.length && nums[rightPtr] == target) {
            rightPtr++;
        }
        return new int[]{leftPtr+1, rightPtr-1};
    }

    private int binarySearch(int[] nums, int target) {
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr <= rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid + 1;
            }
        }
        return -1;
    }
}
