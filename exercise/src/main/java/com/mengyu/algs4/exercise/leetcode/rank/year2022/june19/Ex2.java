package com.mengyu.algs4.exercise.leetcode.rank.year2022.june19;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2022/6/19 10:16
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        System.out.println(new Ex2().minimumNumbers(30, 1));
    }


    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        List<Integer> nums = new ArrayList<>();
        if (k > 0) {
            nums.add(k);
        }
        int add = 10, tmp = k;
        while (tmp + add <= num) {
            tmp += add;
            nums.add(tmp);
        }
        int rightPtr = nums.size() - 1, leftPtr = 0;
        int ans = Integer.MAX_VALUE;
        while (rightPtr >= 0) {
            int sum = 0;
            int count = 0;
            while (nums.get(rightPtr) + sum <= num) {
                count++;
                sum += nums.get(rightPtr);
                if (num == sum) {
                    return count;
                }
                leftPtr = rightPtr - 1;
                while (leftPtr >= 0) {
                    int count2 = 0;
                    tmp = sum;
                    while (nums.get(leftPtr) + tmp <= num) {
                        count2++;
                        tmp += nums.get(leftPtr);
                    }
                    if (tmp == num) {
                        return count + count2;
                    }
                    leftPtr--;
                }
            }
            rightPtr--;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
