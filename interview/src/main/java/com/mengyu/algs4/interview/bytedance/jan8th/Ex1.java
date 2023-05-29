package com.mengyu.algs4.interview.bytedance.jan8th;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/1/8 上午9:26
 * TODO
 */
public class Ex1 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        for (int i = 0; i < Math.min(len1, len2); i++) {
            set1.add(nums1[i]);
            set2.add(nums2[i]);
        }
        if (len1<len2){
            for (int i = len1; i < len2; i++) {
                set2.add(nums2[i]);
            }
        } else if (len2<len1){
            for (int i = len2; i < len1; i++) {
                set1.add(nums1[i]);
            }
        }
        Set<Integer> set = new HashSet<>(set1);
        set.retainAll(set2);
        int[] res = new int[set.size()];
        int idx = 0;
        for (int num : set) {
            res[idx++]=num;
        }
        return res;
    }
}
