package com.mengyu.algs4.exercise.leetcode.rank.year2022.july31;

/**
 * @author yuzhang
 * @date 2022/7/31 10:29
 * TODO
 */
public class Ex2 {

    public static void main(String[] args) {
        int[] grades = {2,31,41,31,36,46,33,45,47,8,31,6,12,12,15,35};
        System.out.println(new Ex2().maximumGroups(grades));
    }

    /**
     * 将一个数组从小到大排序后，如果取{nums[0]}, {nums[1], nums[2]}, {nums[3], nums[4], nums[5]}....
     * 一定满足分组后：第 i 个分组中的学生总成绩 小于 第 (i + 1) 个分组中的学生总成绩，对所有组均成立（除了最后一组）
     * 那么现在问题转化成了每个分组中有1，2，3，..., n个元素，这些分组的元素个数和=grades.length，即1+2+3+4..+n = grades.length
     * 就是等差数列求n的过程
     * @param grades
     * @return
     */
    public int maximumGroups(int[] grades) {
        int len = grades.length;
        return (int) ((Math.sqrt(1 + 8 * len) - 1) / 2);
    }
}
