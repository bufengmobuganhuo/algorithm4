package leetcode.rank.year2022.august28;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/8/28 10:20
 * TODO
 */
public class Ex1 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int m = nums.length, n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int query = queries[i];
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (query < nums[j]) {
                    break;
                }
                query -= nums[j];
                count++;
            }
            ans[i] = count;
        }
        return ans;
    }
}
