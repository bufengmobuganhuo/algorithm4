package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex134 {
    /**
     * 假设从i出发最远能到达j，则gas[i1] - cost[i1] + gas[i2] - cost[i2] ... + gas[j] - cost[j] + gas[j + 1] - cost[j + 1]  <= 0
     * 因为能到达j，则对于所有的x -> [i, j)，gas[x] - cost[x]的和都是正数，则gas[j] - cost[j] < gas[x] - cost[x] + gas[j] - cost[j]
     * gas[x] - cost[x] + gas[j] - cost[j] + gas[j + 1] - cost[j + 1] < 0，那gas[j] - cost[j] + gas[j + 1] - cost[j +
     * 1] < 0。同样的对于所有的x也有如上规律。则[i, j]内的所有节点都无法到达j+1。
     * 因此可以从0开始，如果无法到达j，则从j开始继续判断是否可以到达
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int idx = 0, n = gas.length;
        while (idx < n) {
            int cnt = 0;
            int sumCost = 0;
            int sumGas = 0;
            while (cnt < n) {
                int j = (idx + cnt) % n;
                sumGas += gas[j];
                sumCost += cost[j];
                if (sumGas < sumCost) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return idx;
            } else {
                idx = idx + cnt + 1;
            }
        }
        return -1;
    }
}
