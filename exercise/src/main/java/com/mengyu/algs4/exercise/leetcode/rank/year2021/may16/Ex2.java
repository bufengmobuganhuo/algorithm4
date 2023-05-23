package com.mengyu.algs4.exercise.leetcode.rank.year2021.may16;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/5/15 下午10:40
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        System.out.println(Arrays.toString(ex2.memLeak(2,2)));
    }
    public int[] memLeak(int memory1, int memory2) {
        int[] res = new int[3];
        int second = 1;
        while (true){
            if (memory1>memory2 && memory1 >= second){
                memory1-=second;
            }else if (memory1 < memory2 && memory2 >= second){
                memory2-=second;
            }else if (memory1 == memory2 && memory1 >= second){
                memory1 -=second;
            }else {
                res[0]=second;
                break;
            }
            second++;
        }
        res[1]=memory1;
        res[2]=memory2;
        return res;
    }
}
