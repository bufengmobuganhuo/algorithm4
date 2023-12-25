package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yu zhang
 */
public class Ex1333 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> ans = new ArrayList<>();
        for (int[] restaurant : restaurants) {
            if ((veganFriendly == 1 && restaurant[2] != veganFriendly) || restaurant[3] > maxPrice || restaurant[4] > maxDistance) {
                continue;
            }
            ans.add(new int[]{restaurant[0], restaurant[1]});
        }
        ans.sort((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        return ans.stream().map(ints -> ints[0]).collect(Collectors.toList());
    }
}
