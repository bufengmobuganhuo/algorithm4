package com.mengyu.algs4.exercise.leetcode.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2261 {
    private TrieNode root = new TrieNode(0);

    public int countDistinct(int[] nums, int k, int p) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += insert(nums, i, k, p);
        }
        return ans;
    }

    private int insert(int[] nums, int i, int k, int p) {
        int cnt = 0, ans = 0;
        TrieNode curNode = root;
        for (int j = i; j < nums.length; j++) {
            if (nums[j] % p == 0) {
                cnt++;
            }
            if (cnt > k) {
                break;
            }
            if (!curNode.children.containsKey(nums[j])) {
                ans++;
                curNode.children.put(nums[j], new TrieNode(nums[j]));
            }
            curNode = curNode.children.get(nums[j]);
        }
        return ans;
    }

    static class TrieNode {
        private int val;
        private Map<Integer, TrieNode> children = new HashMap<>();

        public TrieNode(int val) {
            this.val = val;
        }
    }
}
