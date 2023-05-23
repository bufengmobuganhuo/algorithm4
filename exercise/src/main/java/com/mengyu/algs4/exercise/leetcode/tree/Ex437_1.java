package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex437_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        /*root.left = new TreeNode(0);
        root.left.left = new TreeNode(-1);
        root.left.right = new TreeNode(-6);
        root.left.left.right = new TreeNode(1);
        root.left.left.right.left = new TreeNode(-7);*/
        Ex437_1 ex437_1 = new Ex437_1();
        System.out.println(ex437_1.pathSum(root, 0));
    }
    private int count = 0;

    private int targetSum = 0;

    public int pathSum(TreeNode root, int targetSum) {
        this.count = 0;
        this.targetSum = targetSum;
        HashMap<Integer, Integer> track = new HashMap<>();
        track.put(0, 1);
        dfs(root, track, 0);
        return count;
    }

    private void dfs(TreeNode root, Map<Integer, Integer> track, int preSum) {
        if (root == null) {
            return;
        }
        preSum += root.val;
        int preSumCount = track.getOrDefault(preSum, 0);
        track.put(preSum, preSumCount + 1);
        if (preSum - targetSum == preSum) {
            count += preSumCount;
        } else {
            count += track.getOrDefault(preSum - targetSum, 0);
        }

        if (root.left != null) {
            dfs(root.left, track, preSum);
        }
        if (root.right != null) {
            dfs(root.right, track, preSum);
        }
        if (preSumCount == 0) {
            track.remove(preSum);
        } else {
            track.put(preSum, preSumCount);
        }
    }
}
