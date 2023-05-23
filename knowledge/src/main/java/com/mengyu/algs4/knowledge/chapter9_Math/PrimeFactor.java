package com.mengyu.algs4.knowledge.chapter9_Math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class PrimeFactor {
    public static void main(String[] args) {
        System.out.println(find(9));
        System.out.println(find(18));
        System.out.println(find(17));
    }

    private static List<Integer> find(int num) {
        int prime = 2;
        // 是否包含除本身外的质因数
        boolean hasPrimeFactorExceptItSelf = false;
        List<Integer> res = new ArrayList<>();
        while (prime * prime <= num) {
            while (num % prime == 0) {
                num /= prime;
                res.add(prime);
                hasPrimeFactorExceptItSelf = true;
            }
            prime++;
        }
        // 如果没除尽，
        if (num > 1 || !hasPrimeFactorExceptItSelf) {
            res.add(num);
        }
        return res;
    }
}
