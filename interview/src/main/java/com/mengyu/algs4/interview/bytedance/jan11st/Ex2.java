package com.mengyu.algs4.interview.bytedance.jan11st;

/**
 * @author yuzhang
 * @date 2021/1/11 上午9:36
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] bits = {1, 0, 1, 0};
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.isOneBitCharacter(bits));
    }

    public boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0) {
            return false;
        }
        int len = bits.length;
        if (bits[len - 1] != 0) {
            return false;
        }
        if (len > 1 && bits[len - 2] == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            if (bits[i] == 0) {
                continue;
            }
            if (len > 1 && i == len - 2) {
                return false;
            }
            i++;
        }
        return true;
    }
}
