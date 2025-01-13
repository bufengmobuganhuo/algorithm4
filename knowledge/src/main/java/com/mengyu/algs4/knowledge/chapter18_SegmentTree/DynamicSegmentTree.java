package com.mengyu.algs4.knowledge.chapter18_SegmentTree;

/**
 * 动态开点的线段树
 * @author yu zhang
 */
public class DynamicSegmentTree implements SegmentTree {
    private DynamicSegmentTreeNode root;

    public DynamicSegmentTree(int n) {
        this.root = new DynamicSegmentTreeNode();
        this.root.start = 0;
        this.root.end = n - 1;
    }

    @Override
    public void update(int left, int right, int diff) {
        update(left, right, diff, root);
    }

    @Override
    public int getSum(int left, int right) {
        return getSum(left, right, root);
    }

    private int getSum(int left, int right, DynamicSegmentTreeNode node) {
        // 如果节点负责区间包含在待查询区间内，则可以直接范围，因为每个区间不是重叠的，可以直接返回
        if (node.start <= left && node.end >= right) {
            return node.val;
        }
        // 当前节点负责的一部分区间不在待查询区间内，
        // 则需要把这个标记下放到它的子节点中，否则这个标记会影响我们最终的和（包含了一些不需要的元素）
        // 这里一定要调用一下，因为需要初始化左右子节点
        pushDown(node);
        int sum = 0;
        int mid = node.start + (node.end - node.start) / 2;
        // 如果待查询区间的一部分在节点负责区间的左半边，则向左统计
        if (left <= mid) {
            sum += getSum(left, right, node.leftArr);
        }
        // 如果待查询区间的一部分在节点负责区间的右半边，则向右统计
        if (right > mid) {
            sum += getSum(left, right, node.rightArr);
        }
        return sum;
    }

    private void update(int left, int right, int diff, DynamicSegmentTreeNode node) {
        // 如果节点负责的区间范围在待修改区间内，则这些区间内的元素都应该变更diff，所以直接进行惰性标记
        if (left <= node.start  && right <= node.end) {
            // 节点负责元素的个数
            int cnt = node.end - node.start + 1;
            node.val += cnt * diff;
            node.add += diff;
            return;
        }
        // 如果节点包含了一部分待修改区间，并且节点已经有标记，那需要将这个标记下放到其孩子节点，
        // 因为如果直接在其上增加的话会影响待修改区间外的其他元素，导致计算不准
        if (node.add != 0 && node.start != node.end) {
            pushDown(node);
        }
        int mid = node.start + (node.end - node.start) / 2;
        // 如果节点负责的左区间包含待修改区间的一部分
        if (left <= mid) {
            update(left, right, diff, node.leftArr);
        }
        // 如果节点负责的右区间包含待修改区间的一部分
        if (right > mid) {
            update(left, right, diff, node.rightArr);
        }
        // 修改完孩子节点后，更新当前节点的值，称为上浮
        pushUp(node);
    }

    private void pushUp(DynamicSegmentTreeNode node) {
        node.val = node.leftArr.val + node.rightArr.val;
    }

    private void pushDown(DynamicSegmentTreeNode node) {
        int mid = node.start + (node.end - node.start) / 2;
        if (node.leftArr == null) {
            node.leftArr = new DynamicSegmentTreeNode();
            node.leftArr.start = node.start;
            node.leftArr.end = mid;
        }
        if (node.rightArr == null) {
            node.rightArr = new DynamicSegmentTreeNode();
            node.rightArr.start = mid + 1;
            node.rightArr.end = node.end;
        }
        if (node.add == 0) {
            return;
        }
        int leftCnt = mid - root.start + 1;
        node.leftArr.val += leftCnt * node.add;
        node.leftArr.add += node.add;

        int rightCnt = root.end - mid;
        node.rightArr.val += rightCnt * node.add;
        node.rightArr.add += node.add;

        node.add = 0;
    }

    private static class DynamicSegmentTreeNode {
        private DynamicSegmentTreeNode leftArr, rightArr;

        // 负责区间起始端点
        private int start;

        // 负责区间结束端点
        private int end;

        private int val;

        private int add;
    }
}
