package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2021/11/6 10:20 上午
 * TODO
 */
public class Ex367_1 {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        int leftPtr = 1, rightPtr = num;
        while (leftPtr <= rightPtr){
            int mid = leftPtr + (rightPtr - leftPtr)/2;
            int divided = num / mid;
            if (mid == divided && num % mid == 0){
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
