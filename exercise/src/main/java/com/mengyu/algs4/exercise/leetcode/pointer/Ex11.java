package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yuzhang
 * @date 2021/11/28 9:43 上午
 * TODO
 */
public class Ex11 {
    public int maxArea(int[] height) {
        if (height==null||height.length==0){
            return 0;
        }
        int leftPtr=0,rightPtr=height.length-1;
        int res=0;
        while (leftPtr<rightPtr){
            res=height[leftPtr]<height[rightPtr]?
                    Math.max(res,(rightPtr-leftPtr)*height[leftPtr++]):
                    Math.max(res,(rightPtr-leftPtr)*height[rightPtr--]);
        }
        return res;
    }
}
