package com.mengyu.algs4.exercise.leetcode.rank.year2022.october30;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2022/10/30 10:45
 * TODO
 */
public class Ex3 {

    private long ans = Long.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new Ex3().makeIntegerBeautiful(590, 1));
    }

    public long makeIntegerBeautiful(long n, int target) {
        List<Integer> digits = new ArrayList<>();
        int ten = 10;
        int sum = 0;
        while (n != 0) {
            int digit = (int) (n % ten);
            digits.add(digit);
            n /= 10;
            sum += digit;
        }
        if (sum <= target) {
            return 0;
        }
        StringBuilder ans = new StringBuilder();
        int flag = 0;
        for (int i = 0; i < digits.size(); i++) {
            int digit = digits.get(i);
            if (digit + flag == 0) {
                ans.append("0");
                continue;
            }
            int add = 10 - digit - flag;
            ans.append(add);
            sum = sum - digit - flag;
            flag = 1;
            sum += flag;
            digits.set(i, 0);
            if (sum <= target) {
                break;
            }
        }
        return Long.parseLong(ans.reverse().toString());
    }
}
