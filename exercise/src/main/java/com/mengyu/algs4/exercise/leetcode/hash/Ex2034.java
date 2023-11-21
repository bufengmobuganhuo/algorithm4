package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yu zhang
 */
public class Ex2034 {

    private int maxTimestamp;

    private TreeMap<Integer, Integer> priceCntMap;

    private Map<Integer, Integer> time2PriceMap;

    public Ex2034() {
        priceCntMap = new TreeMap<>();
        time2PriceMap = new HashMap<>();
        maxTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        int prePrice = time2PriceMap.getOrDefault(timestamp, 0);
        time2PriceMap.put(timestamp, price);
        maxTimestamp = Math.max(maxTimestamp, timestamp);
        if (prePrice > 0) {
            int cnt = priceCntMap.get(prePrice);
            if (cnt == 1) {
                priceCntMap.remove(prePrice);
            } else {
                priceCntMap.put(prePrice, cnt - 1);
            }
        }
        priceCntMap.put(price, priceCntMap.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return time2PriceMap.get(maxTimestamp);
    }

    public int maximum() {
        return priceCntMap.lastEntry().getKey();
    }

    public int minimum() {
        return priceCntMap.firstEntry().getKey();
    }
}
