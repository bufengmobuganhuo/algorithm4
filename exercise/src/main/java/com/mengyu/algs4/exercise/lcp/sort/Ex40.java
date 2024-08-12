package com.mengyu.algs4.exercise.lcp.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex40 {
    public static void main(String[] args) {
        System.out.println(new Ex40().maximumScore(new int[]{3,3,1}, 1));
    }
    public int maximumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int n = cards.length, sum = 0;
        int odd1 = Integer.MAX_VALUE, even1 = Integer.MAX_VALUE;
        for (int i = 1; i < cnt + 1; i++) {
            sum += cards[n - i];
            if (cards[n - i] % 2 == 0) {
                odd1 = Math.min(odd1, cards[n - i]);
            } else {
                even1 = Math.min(even1, cards[n - i]);
            }
        }
        if (sum % 2 == 0) {
            return sum;
        }
        int odd2 = Integer.MIN_VALUE, even2 = Integer.MIN_VALUE;
        for (int i = n - cnt - 1; i >= 0; i--) {
            if (cards[i] % 2 == 0) {
                odd2 = Math.max(odd2, cards[i]);
            } else {
                even2 = Math.max(even2, cards[i]);
            }
            if (odd2 != Integer.MIN_VALUE && even2 != Integer.MIN_VALUE) {
                break;
            }
        }
        if (odd2 != Integer.MIN_VALUE && even2 != Integer.MIN_VALUE && odd1 != Integer.MAX_VALUE && even1 != Integer.MAX_VALUE) {
            return Math.max(sum - even1 + odd2, sum - odd1 + even2);
        } else if (odd2 != Integer.MIN_VALUE && even1 != Integer.MAX_VALUE) {
            return sum - even1 + odd2;
        } else if (even2 != Integer.MIN_VALUE && odd1 != Integer.MAX_VALUE) {
            return sum - odd1 + even2;
        }
        return 0;
    }
}
