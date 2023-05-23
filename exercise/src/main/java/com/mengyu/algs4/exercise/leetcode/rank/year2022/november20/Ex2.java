package com.mengyu.algs4.exercise.leetcode.rank.year2022.november20;

import com.mengyu.algs4.utils.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2022/11/20 10:10
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(13);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(15);
        root.right.right.left = new TreeNode(14);
        List<Integer> quries = new ArrayList<>();
        quries.add(2);
        quries.add(5);
        quries.add(16);
        System.out.println(new Ex2().closestNodes(root, quries));
    }
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> list = new ArrayList<>();
        travel(root, list);
        List<List<Integer>> ans = new ArrayList<>();
        for (int query : queries) {
            int idx = floor(list, query);
            if (idx == -1) {
                ans.add(build(-1, list.get(0)));
            } else if (list.get(idx) == query) {
                ans.add(build(query, query));
            } else if (idx + 1 < list.size()) {
                ans.add(build(list.get(idx), list.get(idx + 1)));
            } else {
                ans.add(build(list.get(idx), -1));
            }
        }
        return ans;
    }

    private int floor(List<Integer> list, int target) {
        int leftPtr = -1, rightPtr = list.size() - 1;
        while (leftPtr < rightPtr) {
            int midPtr = leftPtr + (rightPtr - leftPtr + 1) / 2;
            int mid = list.get(midPtr);
            if (target <= mid) {
                rightPtr = midPtr - 1;
            } else {
                leftPtr = midPtr;
            }
        }
        if (leftPtr + 1 < list.size() && list.get(leftPtr + 1) == target) {
            return leftPtr + 1;
        }
        return leftPtr;
    }

    private void travel(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        travel(node.left, list);
        list.add(node.val);
        travel(node.right, list);
    }

    private List<Integer> build(int min, int max) {
        List<Integer> list = new ArrayList<>();
        list.add(min);
        list.add(max);
        return list;
    }
}
