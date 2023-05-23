package com.mengyu.algs4.exercise.leetcode.rank.year2022.november13;

import com.mengyu.algs4.utils.leetcode.TreeNode;

import java.util.*;

/**
 * @author yuzhang
 * @date 2022/11/13 10:20
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(9);
        root.right.right.left = new TreeNode(10);
        System.out.println(new Ex3().minimumOperations(root));
    }
    private int count;

    public int minimumOperations(TreeNode root) {
        count = 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                arr[i] = node.val;
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
            }
            count += count(arr);
        }
        return count;
    }

    public int count(int[] nums) {
        int len = nums.length;
        int[] ordered = new int[len];
        System.arraycopy(nums, 0, ordered, 0, len);
        Arrays.sort(ordered);
        Map<Integer,Integer> positionIdx = new HashMap<>();
        // 记录排序后每个元素的正确位置
        for (int i = 0; i < len; i++) {
            positionIdx.put(ordered[i], i);
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            while (true) {
                int rightIdx = positionIdx.get(nums[i]);
                // 说明不在正确位置，这个时候需要交换位置
                if (rightIdx != i) {
                    count++;
                    exch(nums, i, rightIdx);
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
