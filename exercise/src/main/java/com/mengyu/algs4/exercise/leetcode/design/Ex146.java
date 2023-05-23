package com.mengyu.algs4.exercise.leetcode.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/5/25 上午8:28
 * TODO
 */
public class Ex146 extends LinkedHashMap<Integer, Integer> {
    private final int MAX_SIZE;

    public Ex146(int capacity) {
        super(capacity,0.75F,true);
        MAX_SIZE = capacity;
    }

    public int get(int key) {
        Integer value = super.get(key);
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > MAX_SIZE;
    }
}
