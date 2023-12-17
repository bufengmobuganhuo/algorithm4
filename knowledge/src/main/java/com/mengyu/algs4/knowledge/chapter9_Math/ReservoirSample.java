package com.mengyu.algs4.knowledge.chapter9_Math;

import java.util.List;
import java.util.Random;

/**
 * @author yu zhang
 * 水塘抽样
 */
public class ReservoirSample {
    /**
     * 从list中随机抽样k个元素，保证list.size() > k
     */
    public static int[] getRandom(List<Integer> list, int k) {
        Random random = new Random();
        int[] ans = new int[k];
        // 前k个先保留
        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i);
        }
        for (int i = k; i < list.size(); i++) {
            int rand = random.nextInt(i + 1);
            if (rand < k) {
                // rand < k就是k/i的概率保留第i个数
                // 同时rand在k内是随机的，所以是以1/k的概率替换
                ans[rand] = list.get(i);
            }
        }
        return ans;
    }
}
