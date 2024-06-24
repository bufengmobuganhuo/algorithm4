package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2952 {
    /**
     * 1.将coins升序排序，并且从左到右遍历
     * 2.对于一个正整数x，如果[1, x-1]内的整数都可以取得，那如果此时多了一个面值为coin的硬币，并且coin <= x，
     * 则[1, coin + x - 1]的区间都可以取得，这个时候我们就可以继续遍历下一个元素
     * 3. 但是如果coin > x，则[x, coin - 1]，一定取不到，因为一开始能取到[1, x-1]，则加入coin后，能取到的区间为[coin, x + coin - 1]，
     * 那[1, x - 1]，[coin, x + coin - 1]都可以取到，此时就需要再加入一个面值为x的硬币, 那能保证[1, 2x - 1]的都被取到，
     * 但是此时还是无法保证能取到[x, coin - 1]，这取决于coin和2x的大小，因此不能继续遍历下一个元素
     */
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int ans = 0, x = 1;
        int n = coins.length, idx = 0;
        while (x <= target) {
            if (idx < n && coins[idx] <= x) {
                x += coins[idx];
                idx++;
            } else {
                x *= 2;
                ans++;
            }

        }
        return ans;
    }
}
