package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex2583 {

    public static void main(String[] args) {

    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            offer(priorityQueue, sum, k);
        }
        return priorityQueue.size() == k ? priorityQueue.peek() : -1;
    }

    private void offer(PriorityQueue<Long> priorityQueue, long val, int k) {
        if (priorityQueue.size() < k) {
            priorityQueue.offer(val);
        } else if (priorityQueue.peek() < val){
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
    }
}
