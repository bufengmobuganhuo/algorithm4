package com.mengyu.algs4.interview.zhongxin;

import java.util.Scanner;

/**
 * @author yu zhang
 */
public class Ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            String numLine = scanner.nextLine();
            String[] nums = numLine.split(" ");
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (Integer.parseInt(nums[i]) % 2 == 1) {
                    cnt++;
                }
            }
            if (cnt > 0) {
                System.out.println(cnt);
            } else {
                System.out.println(-1);
            }

        }
    }
}
