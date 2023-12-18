package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex1457 {

    public static void main(String[] args) {
    }

    private int[] arr = {0, 1, 2, 4, 8, 16, 32, 64, 128, 256};

    private int ans = 0;

    public int pseudoPalindromicPaths (TreeNode root) {
        ans = 0;
        dfs(root, 0, 1);
        return ans;
    }

    private void dfs(TreeNode node, int track, int nodeCnt) {
        if (node == null) {
            return;
        }
        track = track ^ arr[node.val];
        if (node.left != null) {
            dfs(node.left, track, nodeCnt + 1);
        }
        if (node.right != null) {
            dfs(node.right, track, nodeCnt + 1);
        }
        if (node.left == null && node.right == null) {
            if (nodeCnt % 2 == 0) {
                ans = track == 0 ? ans + 1 : ans;
            } else {
                int cnt = 0;
                while (track != 0) {
                    int digit = track & 1;
                    if (digit == 1) {
                        cnt++;
                        if (cnt > 1) {
                            break;
                        }
                    }
                    track = track >> 1;
                }
                ans = cnt == 1 ? ans + 1 : ans;
            }
        }
    }
}
