package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2020/12/17 上午9:02
 * TODO
 */
public class Ex223 {
    public static void main(String[] args) {
        Ex223 ex223 = new Ex223();
        System.out.println(ex223.computeArea(-2, -2, 2, 2, 3, 3, 4, 4));
    }

    /**
     * 1. 对于两个线段：
     *  A         B
     *  -----------
     *         C       D
     *         ---------
     *  他们之间重合的长度=min(B-D)-max(A,C)
     * 2. 对于两个矩阵，可以将两个矩阵的横纵坐标分别映射到X，Y轴，通过上述公式就可以求出X，Y轴的线段重合长度，从而求出重合矩阵的面积
     * 3. 注意溢出的情况
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // 矩阵1映射到X轴
        int xA = A;
        int xB = C;
        // 矩阵2映射到X轴
        int xC = E;
        int xD = G;
        // 重合长度,最小重合长度为0
        long x = Math.max(0, (long)Math.min(xB, xD) - Math.max(xA, xC));

        // 矩阵1映射到Y轴
        int yA = B;
        int yB = D;
        // 矩阵2映射到Y轴
        int yC = F;
        int yD = H;
        long y = Math.max(0, (long)Math.min(yB, yD) - Math.max(yA, yC));
        return (int)((long)(C - A) * (D - B) - x * y + (G - E) * (H - F));
    }
}
