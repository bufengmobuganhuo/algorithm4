package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2021/1/14 上午11:20
 * TODO
 */
public class Ex367 {
    /**
     * 二分法
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        int leftPtr = 1, rightPtr = num;
        while (leftPtr < rightPtr){
            int mid = leftPtr + (rightPtr - leftPtr)/2;
            int divided = num / mid;
            if (mid == divided){
                return true;
            } else if (mid > divided){
                rightPtr = mid - 1;
            } else {
                leftPtr = mid + 1;
            }
        }
        return false;
    }
}
