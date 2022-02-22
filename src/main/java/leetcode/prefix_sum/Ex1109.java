package leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex1109 {

    public int[] corpFlightBookings2(int[][] bookings, int n) {
        // 差分数组一开始都是0
        int[] nums = new int[n];
        // 计算差分数组
        for (int[] booking : bookings) {
            int start = booking[0], end = booking[1], num = booking[2];
            nums[start - 1] += num;
            if (end < nums.length) {
                nums[end] -= num;
            }
        }
        // 求差分数组前缀和
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int[] booking : bookings) {
            int start = booking[0], end = booking[1], num = booking[2];
            for (int i = start; i < end + 1; i++) {
                ans[i - 1] += num;
            }
        }
        return ans;
    }
}
