package com.mengyu.algs4.knowledge.chapter18_SegmentTree;

/**
 * @author yu zhang
 */
public interface SegmentTree {
    /**
     * 为给定区间的每个元素加一个值
     * @param left 区间左端点
     * @param right 区间右端点
     * @param diff 变化量
     */
    void update(int left, int right, int diff);

    /**
     * 查询给定区间的和
     * @param left 区间左端点
     * @param right 区间右端点
     * @return
     */
    int getSum(int left, int right);
}
