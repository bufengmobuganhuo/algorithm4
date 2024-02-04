package com.mengyu.algs4.exercise.leetcode.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2808 {
    /**
     * 1. nums的最终结果一定是之前nums中出现过的数，假设这个数是x
     * 2. 在最初始时，x将nums分成一段一段的。每段两头的元素分别开始感染，类似2个人相向而行
     * 则它们把这一段感染完的时间=floor(dist/2)
     * 3. 又因为是同时操作，则最终的时间取决于最长的那一段所需的时间
     * 4. 我们可以枚举每个x，得到最小的答案
     * @param nums
     * @return
     */
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums.get(i), key -> new ArrayList<>()).add(i);
        }
        int ans = Integer.MAX_VALUE;
        for (List<Integer> idxs : mp.values()) {
            int maxDist = idxs.get(0) + n - idxs.get(idxs.size() - 1);
            for (int i = 1; i < idxs.size(); i++) {
                maxDist = Math.max(maxDist, idxs.get(i) - idxs.get(i- 1));
            }
            ans = Math.min(ans, maxDist / 2);
        }
        return ans;
    }
}
