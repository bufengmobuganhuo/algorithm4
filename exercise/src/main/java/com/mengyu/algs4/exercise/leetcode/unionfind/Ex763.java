package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/10/22 9:57 上午
 * TODO
 */
public class Ex763 {
    /**
     * 1. 对于一个字母而言，其第一次出现的下标位置和最后一次出现的下标位置一定在同一个片段
     * 2. 在得到每个字母最后一次出现的下标后，使用贪心算法和双指针的方式划分片段
     * 1⃣️ 从左到右遍历字符串，遍历的同时维护当前片段的开始下标start和结束下标end，一开始start=end=0
     * 2⃣️ 对于每个访问到的字符chr，找到chr最后一次出现的下标位置lastIdx，则当前片段的结束位置一定不会小于lastIdx
     * 故另end=max(end,lastIdx)
     * 3⃣️ 当遍历到下标end时，则表明一个片段结束，另start=end+1开始下一个片段的查找
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        // 每个字符最后出现的位置
        int[] lastIdxes = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIdxes[S.charAt(i) - 'a'] = i;
        }
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, lastIdxes[S.charAt(i) - 'a']);
            // 遍历到当前片段结束
            if (i == end) {
                ans.add(end - start + 1);
                start = end + 1;
            }
        }
        return ans;
    }
}
