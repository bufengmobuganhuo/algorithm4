package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex1893 {
    public static void main(String[] args) {
        int[][] ranges = {
            {25, 42},
            {7, 14},
            {2, 32},
            {25, 28},
            {39, 49},
            {1, 50},
            {29, 45},
            {18, 47},
        };
        Ex1893 ex1893 = new Ex1893();
        System.out.println(ex1893.isCovered(ranges, 15, 38));
    }

    /**
     * 差分数组，diff[i]：覆盖整数i的区间数量相对于覆盖i-1区间数量的变化量，当遍历到闭区间 [l, r][l,r] 时，l 相对于 l−1 被覆盖区间数量多 1，r+1 相对于r被覆盖区间数量少 1。
     * 对应到差分数组上，我们需要将 diff[l] 加上 1，并将 diff[r+1] 减去 1
     * diff[i]=0：表示相对于i-1来说，覆盖i的区间数量没变化，由i-1决定
     * 如一组区间：[1,2],[3,4][5,6]，对应的diff是：[0,1,0,0,0,0]，对于diff[2]来说，覆盖他的区间数量与覆盖1的相同
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        // 前缀和，下标 i 对应的被覆盖区间数量即为初始数量 0 加上 [1,i] 闭区间的变化量之和
        int curr = 0;
        for (int i = 1; i <= 50; ++i) {
            curr += diff[i];
            if (i >= left && i <= right && curr <= 0) {
                return false;
            }
        }
        return true;
    }
}
