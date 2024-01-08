package com.mengyu.algs4.exercise.leetcode.simulation;

/**
 * @author yu zhang
 */
public class Ex1599 {

    public static void main(String[] args) {
        int[] customers = {10, 10, 1, 0, 0};
        System.out.println(new Ex1599().minOperationsMaxProfit(customers, 4, 4));
    }

    /**
     * 不需要考虑把所有乘客都送下去
     * @param customers
     * @param boardingCost
     * @param runningCost
     * @return
     */
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length;
        int ans = -1;
        // 轮转次数
        int operations = 0;
        int totalProfit = 0, maxProfit = 0;
        // 当前正在等待的乘客数量
        int customerCnt = 0;

        for (int i = 0; i < n; i++) {
            operations++;
            int customer = customers[i];
            customerCnt += customer;
            // 当前能上车的乘客数量
            int curCustomer = Math.min(4, customerCnt);
            customerCnt -= curCustomer;
            // 本次获取的利润
            totalProfit += curCustomer * boardingCost - runningCost;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations;
            }
        }

        // 没有乘客等待
        if (customerCnt == 0) {
            return ans;
        }

        // 满载时，一次的利润
        int eachTimeProfit = boardingCost * 4 - runningCost;
        // 满载时的利润都是<= 0 ，为了获取最大利润，不再拉人
        if (eachTimeProfit <= 0) {
            return ans;
        }

        if (customerCnt > 0) {
            // 满载拉人的次数
            int fullTimes = customerCnt / 4;
            totalProfit += eachTimeProfit * fullTimes;
            operations += fullTimes;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations;
            }

            // 满载拉完人后，可能还有剩余乘客
            int remainingCustomer = customerCnt % 4;
            int remainingProfit = remainingCustomer * boardingCost - runningCost;
            totalProfit += remainingProfit;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations + 1;
            }
        }
        return ans;
    }
}
