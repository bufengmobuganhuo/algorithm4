package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/7/27 8:44 上午
 * TODO
 */
public class Ex662 {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<AnnotationNode> queue = new LinkedList<>();
        int ans = 0, curDepth = 0, leftPos = 0;
        queue.offer(new AnnotationNode(0, 0, root));
        while (!queue.isEmpty()) {
            AnnotationNode annotationNode = queue.poll();
            if (annotationNode.node != null) {
                // 从0开始为结点编号，子结点.pos=父结点.pos*2 or 父结点.pos*2+1
                queue.offer(new AnnotationNode(annotationNode.depth + 1, annotationNode.postion * 2, annotationNode.node.left));
                queue.offer(new AnnotationNode(annotationNode.depth + 1, annotationNode.postion * 2 + 1, annotationNode.node.right));
                //说明当前访问到的是下一层结点
                if (curDepth != annotationNode.depth) {
                    curDepth = annotationNode.depth;
                    leftPos = annotationNode.postion;
                }
                // 当前层的宽度=右边pos-最左边结点的pos+1
                ans = Math.max(ans, annotationNode.postion - leftPos + 1);
            }
        }
        return ans;
    }

    static class AnnotationNode {
        int depth;
        int postion;
        TreeNode node;

        public AnnotationNode(int depth, int postion, TreeNode node) {
            this.depth = depth;
            this.postion = postion;
            this.node = node;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
