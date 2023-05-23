package com.mengyu.algs4.exercise.offer.recursive;

/**
 * @author yuzhang
 * @date 2020/11/9 9:00 上午
 * TODO
 */
public class Ex64 {

    public int sumNums(int n) {
        // 利用短路实现
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
