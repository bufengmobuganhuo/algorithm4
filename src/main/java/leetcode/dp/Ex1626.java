package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex1626 {
    /**
     * 1. 将数组按照[分数,年龄]升序排序
     * 2. dp[i]：当组建的球队中最大球员序号为i时，能获取的最大分数
     * 3. dp[i] = max{dp[j]}+排序后第i个球员的分数， j < i && 排序后第j个球员的年龄<=第i个球员的年龄
     * (因为排序的关系，第i个球员的分数肯定不小于他的前一位球员的分数(即第j位球员), 我们只需要保证前一个球员的年龄不大于最后的第i个球员的年龄即可)
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] :  o1[1] - o2[1]);
        int[] dp = new int[n];
        dp[0] = people[0][0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (people[j][1] <= people[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += people[i][0];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
