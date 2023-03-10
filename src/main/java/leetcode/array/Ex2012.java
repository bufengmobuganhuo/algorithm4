package leetcode.array;

/**
 * @author yu zhang
 */
public class Ex2012 {
    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 4};
        System.out.println(new Ex2012().sumOfBeauties(nums));
    }
    public int sumOfBeauties(int[] nums) {
        int len = nums.length;
        int[] leftMax = new int[len], rightMin = new int[len];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            leftMax[i] = max;
            if (nums[len - i - 1] < min) {
                min = nums[len - i - 1];
            }
            rightMin[len - i - 1] = min;
        }
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            int num = nums[i];
            if (leftMax[i - 1] < num && rightMin[i + 1] > num) {
                ans += 2;
            } else if (nums[i - 1] < num && nums[i + 1] > num) {
                ans += 1;
            }
        }
        return ans;
    }
}
