package leetcode.rank.year2022.feb13;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/2/13 11:12 上午
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {2, 10, 3, 2};
        System.out.println(new Ex3().minimumRemoval(nums));
    }
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        int len = beans.length;
        long[] preSum = new long[len];
        preSum[0] = beans[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + beans[i];
        }
        long ans = preSum[len - 1] - preSum[0] - (long) beans[0] * (len - 1);
        for (int i = 1; i < beans.length; i++) {
            if (beans[i] == beans[i - 1]) {
                continue;
            }
            long sum = preSum[i - 1] + preSum[len - 1] - preSum[i] - (long) beans[i] * (len - i - 1);
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
