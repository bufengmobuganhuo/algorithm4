package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex134_1 {

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5}, cost = {3,4,5,1,2};
        System.out.println(new Ex134_1().canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n;) {
            int gasLeft = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                gasLeft += gas[j] - cost[j];
                if (gasLeft < 0) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            }
            i = i + cnt + 1;
        }
        return -1;
    }
}
