package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex307_1 {

    private int[] nums;

    private int[] tree;

    private int len;

    public Ex307_1(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length + 1];
        len = tree.length;
        for (int i = 1; i < len; i++) {
            update(i, nums[i - 1]);
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        updateTree(index, delta);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int leftSum = query(left);
        int rightSum = query(right + 1);
        return rightSum - leftSum;
    }

    private int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    private void updateTree(int i, int delta) {
        while (i < len) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    private int lowbit(int i) {
        return i & (-i);
    }
}
