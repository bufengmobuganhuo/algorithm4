package com.mengyu.algs4.exercise.bytedance.jan12th;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/12 上午9:26
 * TODO
 */
public class Ex1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums1.length; i++) {
            int idx = map.get(nums1[i]);
            for (int j = idx + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
    }
}
