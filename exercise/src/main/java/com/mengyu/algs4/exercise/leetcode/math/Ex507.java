package com.mengyu.algs4.exercise.leetcode.math;

public class Ex507 {
    public static void main(String[] args) {
        Ex507 ex507 = new Ex507();
        System.out.println(ex507.checkPerfectNumber(28));
    }
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        // 枚举每个因数
        for (int i = 2; i * i<= num; i++) {
            if (num % i == 0) {
                sum += i;
                // 找到了因数i,则另一个num/i也找到了，但是二者不能重复，故i * i != num
                if (i * i < num) {
                    sum += num / i;
                }
            }
        }
        return sum == num;
    }
}
