package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yuzhang
 * @date 2020/12/7 下午3:32
 * 1. 为了得到最高的分数，矩阵的每一行的最左边的数都必须为 1。为了做到这一点，我们可以翻转那些最左边的数不为 1 的那些行，而其他的行则保持不动。
 * 2. 当将每一行的最左边的数都变为 1 之后，就只能进行列翻转了。为了使得总得分最大，我们要让每个列中 1 的数目尽可能多。
 *      因此，我们扫描除了最左边的列以外的每一列，如果该列 0 的数目多于 1 的数目，就翻转该列，其他的列则保持不变
 * 3. 实际编写代码时，我们无需修改原矩阵，而是可以计算每一列对总分数的「贡献」，从而直接计算出最高的分数。假设矩阵共有 mm 行 nn 列，计算方法如下：
 *      1⃣️ 对于最左边的列而言，由于最优情况下，它们的取值都为 1，因此每个元素对分数的贡献都为 2^{n-1}，总贡献为 m * 2^{n-1}
 *      2⃣️ 对于第 j 列（j>0，此处规定最左边的列是第 0 列）而言，我们统计这一列 0,1 的数量，令其中的最大值为 k，则 k 是列翻转后的 1 的数量，该列的总贡献为 k * 2^{n-j-1}
 *         需要注意的是，在统计 0,10,1 的数量的时候，要考虑最初进行的行反转
 *
 */
public class Ex861 {
    public static void main(String[] args) {

    }
    public int matrixScore(int[][] A) {
        // m行，n列的矩阵
        int m = A.length, n = A[0].length;
        // 默认经过行翻转后，每一行的第一个数都被反转成了"1"，
        // 则他对结果的贡献是：m * 2^n-1
        int res = (int) (Math.pow(2, n - 1) * m);
        for (int j = 1; j < n; j++) {
            int oneCount = 0;
            for (int i = 0; i < m; i++) {
                // 如果该行的第0个数字的值已经是1，则说明行没有被反转
                if (A[i][0] == 1) {
                    oneCount += A[i][j];
                    // 如果不为1，说明经过了行翻转，那么这个位置的真实值应该是1-A[i][j]
                } else {
                    oneCount += (1 - A[i][j]);
                }
            }
            // 让每一列中，1的个数最多
            oneCount = Math.max(oneCount, m - oneCount);
            res += oneCount * Math.pow(2, n - j - 1);
        }
        return res;
    }
}
