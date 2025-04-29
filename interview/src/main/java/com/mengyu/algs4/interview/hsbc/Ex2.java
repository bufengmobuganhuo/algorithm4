package com.mengyu.algs4.interview.hsbc;

/**
 * @date 2025/4/24 22:36
 * TODO
 */
public class Ex2 {

    public static void main(String[] args) {
        int[][] matA = {
                {1, 2, 3},
                {1, 2, 3}
        };
        System.out.println(prodSubMat(matA, 3));
    }

    public static int  prodSubMat(int[][] matA, int maxK)
    {
        int  answer = 0;
        // Write your code here
        int[][] multis = generate(matA);
        int m = matA.length, n = matA[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int divided = 1;
                if (i > 0 && j > 0) {
                    divided = multis[i - 1][j - 1];
                }
                int product = multis[i][j] / divided;
                if (product <= maxK) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private static int[][] generate(int[][] matA) {
        int m = matA.length, n = matA[0].length;
        int[][] multis = new int[m + 1][n + 1];
        multis[0][0] = matA[0][0];
        for (int i = 1; i < m; i++) {
            multis[i][0] = multis[i - 1][0] * matA[i][0];
        }
        for (int j = 1; j < n; j++) {
            multis[0][j] = multis[0][j - 1] * matA[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                multis[i][j] = multis[i - 1][j] * multis[i][j - 1] / multis[i - 1][j - 1] * matA[i][j];
            }
        }
        return multis;
    }
}
