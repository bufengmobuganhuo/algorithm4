package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex167 {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int leftPtr = 0, rightPtr = n - 1;
        while (leftPtr < rightPtr) {
            int sum = numbers[leftPtr] + numbers[rightPtr];
            if (sum == target) {
                return new int[]{leftPtr, rightPtr};
            } else if (sum < target) {
                leftPtr++;
            } else {
                rightPtr--;
            }
        }
        return new int[]{};
    }
}
