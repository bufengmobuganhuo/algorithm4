package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex889 {

    public static void main(String[] args) {
        System.out.println(new Ex889().constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1}));
    }

    private int[] preorder;

    Map<Integer, Integer> postMap;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = postorder.length;
        this.preorder = preorder;
        this.postMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            this.postMap.put(postorder[i], i);
        }
        return dfs(0, n - 1, 0, n - 1);
    }

    /**
     * 从给定范围内构建树
     * @param preStart 前序遍历的开始索引
     * @param preEnd 前序遍历的结束索引
     * @param postStart 后序遍历的开始索引
     * @param postEnd 后序遍历的结束索引
     * @return
     */
    public TreeNode dfs(int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd) {
            return null;
        }
        // 当前范围内的左子树的节点个数
        int leftCnt = 0;
        if (preStart < preEnd) {
            // 如果有左子树，当前范围的下一个节点就是左子树的根节点
            leftCnt = postMap.get(preorder[preStart + 1]) - postStart + 1;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        // 继续构建左子树，
        root.left = dfs(preStart + 1, preStart + leftCnt, postStart, postStart + leftCnt - 1);
        root.right = dfs(preStart + leftCnt + 1, preEnd, postStart + leftCnt, postEnd - 1);
        return root;
    }
}
