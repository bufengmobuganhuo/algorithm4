package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex706 {

    private static final int MOD = 1000001;

    private LinkedList<MapNode>[] map;

    public Ex706() {
        map = new LinkedList[MOD];
    }

    public void put(int key, int value) {
        int idx = ((Integer)key).hashCode() % MOD;
        if (map[idx] == null) {
            map[idx] = new LinkedList<>();
        }
        MapNode node = null;
        for (MapNode mapNode : map[idx]) {
            if (mapNode.key == key) {
                node = mapNode;
                break;
            }
        }
        if (node != null) {
            node.val = value;
        } else {
            map[idx].add(new MapNode(key, value));
        }
    }

    public int get(int key) {
        int idx = ((Integer)key).hashCode() % MOD;
        if (map[idx] == null) {
            return -1;
        }
        int i = map[idx].indexOf(new MapNode(key));
        return i >= 0 ? map[idx].get(i).val : -1;
    }

    public void remove(int key) {
        int idx = ((Integer)key).hashCode() % MOD;
        if (map[idx] == null) {
            return;
        }
        map[idx].remove(new MapNode(key));
    }

    private static class MapNode {
        private int key;

        private int val;

        public MapNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public MapNode(int key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapNode mapNode = (MapNode) o;

            return key == mapNode.key;
        }

        @Override
        public int hashCode() {
            return ((Integer)key).hashCode();
        }
    }
}
