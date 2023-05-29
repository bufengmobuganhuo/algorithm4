package com.mengyu.algs4.interview.huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2021/3/27 上午10:14
 * TODO
 */
public class Ex14 {
    private static boolean[] marked;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String quack = scanner.nextLine();
            marked = new boolean[quack.length()];
            int count = 0;
            int lastStartPtr = 0;
            for (int i = 0; i < quack.length(); i++) {
                while (i < quack.length() && marked[i]) {
                    i++;
                }
                if (i == quack.length()) {
                    break;
                }
                if (quack.charAt(i) != 'q') {
                    count = -1;
                    break;
                }
                marked[i] = true;
                if (!find(quack, i + 1, 'u') || !find(quack, i + 1, 'a') || !find(quack, i + 1, 'c') || !find(quack, i + 1, 'k')) {
                    count = -1;
                    break;
                }
                if (i - lastStartPtr != 5) {
                    count++;
                }
                lastStartPtr = i;
            }
            System.out.println(count);
        }
    }

    private static boolean find(String quack, int idx, char chr) {
        for (int i = idx; i < quack.length(); i++) {
            char chr1 = quack.charAt(i);
            if (chr1 != 'u' && chr1 != 'a' && chr1 != 'c' && chr1 != 'k') {
                return false;
            }
            if (quack.charAt(i) == chr && !marked[i]) {
                marked[i] = true;
                return true;
            }
        }
        return false;
    }
}
