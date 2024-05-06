package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex1052 {

    public static void main(String[] args) {
        System.out.println(new Ex1052().maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }

        int leftPtr = 0, rightPtr = 0;
        int max = 0;
        int slideSum = 0;
        while (rightPtr < n) {
            if (rightPtr - leftPtr + 1 <= minutes) {
                if (grumpy[rightPtr] == 1) {
                    slideSum += customers[rightPtr];
                }
                rightPtr++;
                max = Math.max(max, slideSum);
            } else {
                max = Math.max(max, slideSum);
                if (grumpy[rightPtr] == 1) {
                    slideSum += customers[rightPtr];
                }
                if (leftPtr < n && grumpy[leftPtr] == 1) {
                    slideSum -= customers[leftPtr];
                }
                rightPtr++;
                leftPtr++;
                max = Math.max(max, slideSum);
            }
        }
        return sum + max;
    }
}
