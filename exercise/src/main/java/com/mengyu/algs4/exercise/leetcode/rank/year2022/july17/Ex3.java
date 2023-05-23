package com.mengyu.algs4.exercise.leetcode.rank.year2022.july17;

import java.util.*;

/**
 * @author yuzhang
 * @date 2022/7/17 10:50
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        String[] nums = {"24","37","96","04"};
        int[][] queries = {{2, 1}, {2, 2}};
        System.out.println(Arrays.toString(new Ex3().smallestTrimmedNumbers(nums, queries)));
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        Map<Integer, List<Info>> cache = new HashMap<>();
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int k = queries[i][0], trim = queries[i][1];
            List<Info> trimed;
            if (cache.containsKey(trim)) {
                trimed = cache.get(trim);
            } else {
                trimed = new ArrayList<>();
                int startIdx = nums[0].length() - trim;
                for (int j = 0; j < nums.length; j++) {
                    trimed.add(new Info(nums[j].substring(startIdx), j));
                }
                cache.put(trim, trimed);
            }
            trimed.sort(new Comparator<Info>() {
                @Override
                public int compare(Info o1, Info o2) {
                    for (int j = 0; j < o1.num.length(); j++) {
                        if (o1.num.charAt(j) > o2.num.charAt(j)) {
                            return 1;
                        } else if (o1.num.charAt(j) < o2.num.charAt(j)) {
                            return -1;
                        }
                    }
                    return o1.idx - o2.idx;
                }
            });
            ans[i] = trimed.get(k - 1).idx;
        }
        return ans;
    }

    private static class Info {
        private String num;
        private int idx;

        public Info(String num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}
