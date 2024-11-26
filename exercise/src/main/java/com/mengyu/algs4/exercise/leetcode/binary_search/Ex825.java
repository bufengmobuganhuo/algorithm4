package com.mengyu.algs4.exercise.leetcode.binary_search;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex825 {

    public static void main(String[] args) {
        int[] ages = {54,23,102,90,40,74,112,74,76,21};
        System.out.println(new Ex825().numFriendRequests(ages));
    }

    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length;
        int i = 0, equalsCnt = 1;
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            int age = ages[j];
            while (j < n - 1 && ages[j + 1] == age) {
                j++;
                equalsCnt++;
            }

            while (i < j && ages[i] <= 0.5 * age + 7) {
                i++;
            }
            //cnt += ages[i] == age ? j - i : 0;
            cnt += (j - equalsCnt + 1 - i) * equalsCnt + equalsCnt * (equalsCnt - 1);
            equalsCnt = 1;
        }
        return cnt;
    }
}
