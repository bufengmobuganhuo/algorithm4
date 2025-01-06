package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex1366 {
    public String rankTeams(String[] votes) {
        int[][] cnt = new int[26][28];
        for (int i = 0; i < 26; i++) {
            cnt[i][0] = i;
        }
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                char chr = vote.charAt(i);
                cnt[chr - 'A'][i + 1]++;
                cnt[chr - 'A'][27] = 1;
            }
        }
        Arrays.sort(cnt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 1; i < 27; i++) {
                    if (o1[i] != o2[i]) {
                        return o2[i] - o1[i];
                    }
                }
                return o1[0] - o2[0];
            }
        });
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i][27] == 0) {
                continue;
            }
            ans.append((char) (cnt[i][0] + 'A'));
        }
        return ans.toString();
    }
}
