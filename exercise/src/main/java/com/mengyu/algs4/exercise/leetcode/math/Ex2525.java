package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2525 {

    public static void main(String[] args) {
        System.out.println(new Ex2525().categorizeBox(3223, 1271, 2418, 749));
    }

    public String categorizeBox(int length, int width, int height, int mass) {
        boolean isBulky = isBulky(length, width, height);
        boolean isHeavy = isHeavy(mass);

        if (isBulky && isHeavy) {
            return "Both";
        } else if (isBulky) {
            return "Bulky";
        } else if (isHeavy) {
            return "Heavy";
        } else {
            return "Neither";
        }
    }

    private boolean isBulky(long length, int width, int height) {
        long v = length * width * height;
        return v >= 1000000000L || (length >= 10000 || width >= 10000 || height >= 10000);
    }

    private boolean isHeavy(int mass) {
        return mass >= 100;
    }
}
