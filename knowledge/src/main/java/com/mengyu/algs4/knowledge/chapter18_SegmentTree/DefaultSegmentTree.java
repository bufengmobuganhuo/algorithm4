package com.mengyu.algs4.knowledge.chapter18_SegmentTree;

/**
 * 线段树
 * @author yu zhang
 */
public class DefaultSegmentTree implements SegmentTree {

    private int n;

    private SegmentTreeNode[] segmentTreeNodes;

    public DefaultSegmentTree(int[] arr) {
        build(arr);
    }

    /**
     * 在给定区间内所有元素的情况下构建线段树
     */
    private void build(int[] arr) {
        this.n = arr.length;
        this.segmentTreeNodes = new SegmentTreeNode[4 * n + 1];
        for (int i = 0; i < n; i++) {
            this.segmentTreeNodes[i] = new SegmentTreeNode();
        }
        build(arr, 0, arr.length - 1, 1);
    }

    private void build(int[] arr, int start, int end, int idx) {
        // 到达叶子节点
        if (start == end) {
            segmentTreeNodes[idx].val = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        // 构建左子树
        build(arr, start, mid, idx * 2);
        build(arr, mid + 1, end, idx * 2 + 1);
        segmentTreeNodes[idx].val = segmentTreeNodes[idx * 2].val + segmentTreeNodes[idx * 2 + 1].val;
    }

    /**
     * 为给定区间的每个元素加一个值
     * @param left 区间左端点
     * @param right 区间右端点
     * @param diff 变化量
     */
    @Override
    public void update(int left, int right, int diff) {
        update(left, right, 0, n - 1, diff, 1);
    }

    /**
     * 查询给定区间的和
     * @param left 区间左端点
     * @param right 区间右端点
     * @return
     */
    @Override
    public int getSum(int left, int right) {
        return getSum(left, right, 0, n - 1, 1);
    }

    /**
     *
     * @param left 区间左端点
     * @param right 区间右端点
     * @param start 当前节点负责的区间开始点
     * @param end 当前节点负责的区间结束点
     * @param diff 变化量
     * @param idx 当前节点的索引
     */
    private void update(int left, int right, int start, int end, int diff, int idx) {
        // 如果当前节点负责的区间被包含在待修改区间内，则这些区间内的节点都应该被修改，所以可以直接修改后返回
        if (start <= left && end >= right) {
            // 负责的节点数
            int cnt = end - start + 1;
            segmentTreeNodes[idx].val += cnt * diff;
            segmentTreeNodes[idx].m = diff;
            return;
        }
        int mid = start + (end - start) / 2;
        // 当前节点负责的一部分区间不在待修改区间内，但是它又之前被标记过，
        // 则需要把这个标记下放到它的子节点中，否则新标记的会影响那些待修改区间外的元素值
        if (segmentTreeNodes[idx].m != 0 && start != end) {
            // 左区间负责的节点数
            int leftCnt = mid - start + 1;
            segmentTreeNodes[idx * 2].val += leftCnt * segmentTreeNodes[idx].m;
            segmentTreeNodes[idx * 2].m += segmentTreeNodes[idx].m;

            // 右区间负责的节点数
            int rightCnt = end - mid;
            segmentTreeNodes[idx * 2 + 1].val += rightCnt * segmentTreeNodes[idx].m;
            segmentTreeNodes[idx * 2 + 1].m += segmentTreeNodes[idx].m;

            segmentTreeNodes[idx].m = 0;
        }
        // 如果待修改区间的一部分在当前节点负责区间的左半边
        if (left <= mid) {
            update(left, right, start, mid, diff, idx * 2);
        }
        // 如果待修改区间的一部分在当前节点负责区间的右半边
        if (right > mid) {
            update(left, right, mid + 1, end, diff, idx * 2 + 1);
        }
        // 左右节点更新后，再更新父节点的值，但是不需要更新父节点的标记，因为标记已经被下放到子节点去了
        segmentTreeNodes[idx].val = segmentTreeNodes[idx * 2].val + segmentTreeNodes[idx * 2 + 1].val;
    }

    /**
     * @param left 待查询区间左端点
     * @param right 待查询区间右端点
     * @param start 当前节点负责的区间开始点
     * @param end 当前节点负责的区间结束点
     * @param idx 当前节点的索引
     */
    private int getSum(int left, int right, int start, int end, int idx) {
        // 如果待查询的区间包含当前节点负责的区间，直接返回
        // 因为每个节点负责的区间是不重叠的，所以可以直接返回
        if (left <= start && right >= end) {
            return segmentTreeNodes[idx].val;
        }
        int sum = 0;
        int mid = start + (end - start) / 2;
        // 当前节点负责的一部分区间不在待查询区间内，但是它又之前被标记过，
        // 则需要把这个标记下放到它的子节点中，否则这个标记会影响我们最终的和（包含了一些不需要的元素）
        if (segmentTreeNodes[idx].m != 0) {
            int leftCnt = mid - start;
            segmentTreeNodes[idx * 2].val += leftCnt * segmentTreeNodes[idx].m;
            segmentTreeNodes[idx * 2].m += segmentTreeNodes[idx].m;

            int rightCnt = end - mid;
            segmentTreeNodes[idx * 2 + 1].val += rightCnt * segmentTreeNodes[idx].m;
            segmentTreeNodes[idx * 2 + 1].m += segmentTreeNodes[idx].m;

            segmentTreeNodes[idx].m = 0;
        }
        // 如果待查询区间包含当前节点负责区间的左半边，则向左查询
        if (left <= mid) {
            sum += getSum(left, right, start, mid, idx * 2);
        }
        // 如果待查询区间包含当前节点负责区间的左半边，则向右查询
        if (right > mid) {
            sum += getSum(left, right, mid + 1, end, idx * 2 + 1);
        }
        return sum;
    }

    private static class SegmentTreeNode {
        // 当前区间的值（当前区间的和）
        private int val;
        private int m;
    }
}
