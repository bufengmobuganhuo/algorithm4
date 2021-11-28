package leetcode.rank.november28;

/**
 * @author yuzhang
 * @date 2021/11/28 10:35 上午
 * TODO
 */
public class Ex2 {
    public int[] getAverages(int[] nums, int k) {
        long[] prefixSum = new long[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i - k < 0 || i + k >= nums.length) {
                res[i] = -1;
                continue;
            }
            long sum = prefixSum[i + k] - (i - k > 0 ? prefixSum[i - k - 1] : 0);
            res[i] = (int) (sum / (2  * k + 1));
        }
        return res;
    }
}
