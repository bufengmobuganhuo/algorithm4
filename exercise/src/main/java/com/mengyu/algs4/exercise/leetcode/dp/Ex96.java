package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/8/31 9:00 上午
 * 1. 对于序列长度=n的情况，当选定根结点为i时，那么[1...i-1]组成左子树，[i+1...n]组成右子树
 * 2. F(i,n):根结点为i，序列长度为n的不同二叉搜索树的个数
 * G(n):序列长度为n时，二叉搜索树的个数
 * 3. 可知G(n)=F(1,n)+F(2,n)+...+F(n,n)  (1)
 * 4. 对于F(i,n)，由于选定了根结点（为i）和左右子树中序列的长度（分别为i-1和n-i），可以知道:
 * F(i,n)=G(i-1)*G(n-i)  (2)
 * 5. 综合(1),(2)，可知：
 * G(n)=(G(0)*G(n-1))+(G(1)*G(n-2))+...+(G(n-1)*G(0))
 */
public class Ex96 {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        //为了避免G(0)*G(n-1)=0的情况，实际上应该有G(n-1个)
        G[0] = 1;
        G[1] = 1;
        // n取不同的值
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
