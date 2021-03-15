package chapter8_dp.pkg;

import javafx.util.Pair;

/**
 * @author yuzhang
 * @date 2020/9/10 10:42 上午
 * TODO
 */
public class Question7 {
    public static void main(String[] args) {
        Pair<Integer, int[]>[] weights = new Pair[3];
        weights[0] = new Pair<>(4, new int[]{2, 5});
        weights[1] = new Pair<>(3, new int[]{6, 8});
        weights[2] = new Pair<>(1, new int[]{7, 9});
        Pair<Integer, int[]>[] values = new Pair[3];
        values[0] = new Pair<>(3000, new int[]{1500, 2000});
        values[1] = new Pair<>(2500, new int[]{3000, 5000});
        values[2] = new Pair<>(1500, new int[]{4500, 6000});
        System.out.println(solution(weights, values, 10));
    }

    /**
     * @param weights : Pair<Integer,int[]>:<主件的重量，附件集合的重量>
     * @param values  : Pair<Integer,int[]>:<主件价值，附件集合的价值>
     * @param cap
     * @return
     */
    public static int solution(Pair<Integer, int[]>[] weights, Pair<Integer, int[]>[] values, int cap) {
        int[][] maxValueInAttachPrd = new int[weights.length][];
        // 对每一个"主件-附件集合"对做01背包
        for (int i = 0; i < weights.length; i++) {
            maxValueInAttachPrd[i] = pkg01(weights[i].getValue(), values[i].getValue(), cap);
        }
        // 执行完上述操作后，转化为分组背包问题
        int[] dp = new int[cap + 1];
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i].getKey();
            int value = values[i].getKey();
            for (int j = cap; j >= weights[i].getKey(); j--) {
                for (int k = weight; k <= j; k++) {
                    // 主件：要么选，要么不选，即在一个分组中能获取的价值，要么是0，要么>= value
                    dp[j] = Math.max(dp[j], dp[j - k] + maxValueInAttachPrd[i][k - weight] + value);
                }
            }
        }
        return dp[cap];
    }

    /**
     * 01背包
     *
     * @param weights
     * @param values
     * @param cap
     * @return
     */
    private static int[] pkg01(int[] weights, int[] values, int cap) {
        int[] dp = new int[cap + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = cap; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp;
    }
}
