package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex222_2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        Ex222_2 ex222_2 = new Ex222_2();
        System.out.println(ex222_2.countNodes(root));
    }
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = getDepth(root);
        // 0 ~ (depth -1)的节点数
        int pow = pow(2, depth);
        int count = pow - 1;
        // 将最后一层编号为0 ～ 2^depth
        int leftIdx = 0, rightIdx = pow - 1;
        // 从中间开始找idx，直到找不到为止
        while (leftIdx <= rightIdx) {
            int midIdx = leftIdx + (rightIdx - leftIdx) / 2;
            if (exist(root, midIdx, depth)) {
                leftIdx = midIdx + 1;
            } else {
                rightIdx = midIdx - 1;
            }
        }
        return count + leftIdx;
    }

    // 查找编号为idx的节点是否存在
    private boolean exist(TreeNode root, int idx, int depth) {
        int pow = pow(2, depth);
        int leftIdx = 0, rightIdx = pow - 1;
        for (int i = 0; i < depth; i++) {
            int midIdx = leftIdx + (rightIdx - leftIdx) / 2;
            if (midIdx >= idx) {
                root = root.left;
                rightIdx = midIdx;
            } else {
                root = root.right;
                leftIdx = midIdx + 1;
            }
        }
        return root != null;
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int res = 1;
        while (b != 0) {
            if ((b & 1) != 0) {
                res *= a;
            }
            a *= a;
            b >>= 1;
        }
        return res;
    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root.left != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
}
