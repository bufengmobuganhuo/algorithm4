package com.mengyu.algs4.exercise.leetcode.segmen_tree;

/**
 * @author yu zhang
 */
public class Ex731 {

    public static void main(String[] args) {
        Ex731 ex731 = new Ex731();
        System.out.println(ex731.book(10, 20));
        System.out.println(ex731.book(50, 60));
        System.out.println(ex731.book(10, 40));
        System.out.println(ex731.book(5, 15));
    }

    private DynamicSegmentTreeNode root;

    public Ex731() {
        root = new DynamicSegmentTreeNode();
        root.start = 0;
        root.end = 1000000000;
    }

    public boolean book(int startTime, int endTime) {
        if (getSum(startTime, endTime - 1, root) == 2) {
            return false;
        }
        update(startTime, endTime - 1, root);
        return true;
    }

    private int getSum(int left, int right, DynamicSegmentTreeNode node) {
        if (left <= node.start && right >= node.end) {
            return node.val;
        }
        pushDown(node);
        int sum = 0;
        int mid = node.start + (node.end - node.start) / 2;
        if (left <= mid) {
            sum = getSum(left, right, node.left);
        }
        if (right > mid) {
            sum = Math.max(sum, getSum(left, right, node.right));
        }
        return sum;
    }

    private void update(int left, int right, DynamicSegmentTreeNode node) {
        if (left <= node.start && right >= node.end) {
            node.val += 1;
            node.add += 1;
            return;
        }
        pushDown(node);
        int mid = node.start + (node.end - node.start) / 2;
        if (left <= mid) {
            update(left, right, node.left);
        }
        if (right > mid) {
            update(left, right, node.right);
        }
        pushUp(node);
    }

    private void pushUp(DynamicSegmentTreeNode node) {
        node.val = Math.max(node.left.val, node.right.val);
    }

    private void pushDown(DynamicSegmentTreeNode node) {
        int mid = node.start + (node.end - node.start) / 2;
        if (node.left == null) {
            node.left = new DynamicSegmentTreeNode();
            node.left.start = node.start;
            node.left.end = mid;
        }
        if (node.right == null) {
            node.right = new DynamicSegmentTreeNode();
            node.right.start = mid + 1;
            node.right.end = node.end;
        }
        if (node.add == 0) {
            return;
        }
        int leftCnt = mid - node.start + 1;
        node.left.val += node.add;
        node.left.add += node.add;

        int rightCnt = node.end - mid;
        node.right.val += node.add;
        node.right.add += node.add;

        node.add = 0;
    }

    private static class DynamicSegmentTreeNode {
        private DynamicSegmentTreeNode left, right;

        private int start;

        private int end;

        private int add;

        private int val;
    }
}
