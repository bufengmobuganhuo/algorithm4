package com.mengyu.algs4.interview.zhongxin;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author yu zhang
 */
public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            String[] nums = new String[n];
            for (int i = 0; i < n; i++) {
                String num = scanner.nextLine();
                nums[i] = num;
            }
            Arrays.sort(nums, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] nums1 = o1.split(" ");
                    String[] nums2 = o2.split(" ");
                    long a1 = Integer.parseInt(nums1[0]), b1 = Integer.parseInt(nums1[1]);
                    long a2 = Integer.parseInt(nums2[0]), b2 = Integer.parseInt(nums2[1]);
                    long mul1 = a1 * b2, mul2 = a2 * b1;
                    return Long.compare(mul2, mul1);
                }
            });
            for (int i = 0; i < n; i++) {
                System.out.println(nums[i]);
            }
        }
    }
}
