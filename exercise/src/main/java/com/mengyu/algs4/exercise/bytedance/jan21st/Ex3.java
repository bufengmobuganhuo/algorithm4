package com.mengyu.algs4.exercise.bytedance.jan21st;

/**
 * @author yuzhang
 * @date 2021/1/21 上午10:11
 * TODO
 */
public class Ex3 {
    /**
     * 1. dp[0]: 当前节点被覆盖到，并且安装了相机，则左右子节点可装可不装
     * dp[0]=min(left[0],min(left[1],left[2]))+min(right[0],min(right[1],right[2]))+1
     * 2. dp[1]: 当前节点被覆盖到，并且没有安装相机，则左右子节点必须得有一个安装相机
     * dp[1]=min(left[0]+min(right[0],right[1]),right[0]+min(left[0],left[1]))
     * 3. dp[2]: 当前节点没有被覆盖到，则说明左右子节点肯定没装摄像头
     * dp[2]=left[1]+right[1]
     */
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int[] dp = minCamera(root);
        return Math.min(dp[0], dp[1]);
    }

    private int[] minCamera(TreeNode node) {
        int[] dp = new int[3];
        if (node == null) {
            dp[0] = Integer.MAX_VALUE / 2;
            dp[2] = Integer.MAX_VALUE / 2;
            return dp;
        }
        int[] left = minCamera(node.left);
        int[] right = minCamera(node.right);
        dp[0] = Math.min(left[0], Math.min(left[1], left[2])) + Math.min(right[0], Math.min(right[1], right[2])) + 1;
        dp[1] = Math.min(left[0] + Math.min(right[0], right[1]), right[0] + Math.min(left[0], left[1]));
        dp[2] = left[1] + right[1];
        return dp;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
