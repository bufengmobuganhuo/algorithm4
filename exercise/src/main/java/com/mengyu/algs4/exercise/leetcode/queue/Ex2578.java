package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yu zhang
 */
public class Ex2578 {
    public int splitNum(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num != 0) {
            int mod = num % 10;
            num /= 10;
            digits.add(mod);
        }
        digits = digits.stream().sorted().collect(Collectors.toList());
        int num1 = 0, num2 = 0;
        for (int i = 0; i < digits.size(); i++) {
            int digit = digits.get(i);
            if (i % 2 == 0) {
                num1 *= 10;
                num1 += digit;
            } else {
                num2 *= 10;
                num2 += digit;
            }
        }
        return num1 + num2;
    }
}
