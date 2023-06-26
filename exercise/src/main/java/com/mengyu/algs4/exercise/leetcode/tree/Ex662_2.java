package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex662_2 {

    public static void main(String[] args) {
    }

    private Map<Integer, Integer> map;

    private int ans;

    public int widthOfBinaryTree(TreeNode root) {
        ans = 0;
        map = new HashMap<>();
        find(root, 1, 0);
        return ans;
    }

    private void find(TreeNode node, int count, int depth) {
        if (node == null) {
            return;
        }
        int leftCount = map.getOrDefault(depth, count);
        ans = Math.max(ans, count - leftCount + 1);
        if (leftCount >= count) {
            map.put(depth, count);
        }
        if (node.left != null) {
            find(node.left, 2 * count, depth + 1);
        }
        if (node.right != null) {
            find(node.right, 2 * count + 1, depth + 1);
        }
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
            }
            a *= a;
            b >>= 1;
        }
        return res;
    }
}
