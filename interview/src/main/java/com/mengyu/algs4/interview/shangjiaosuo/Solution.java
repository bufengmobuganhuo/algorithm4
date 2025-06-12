package com.mengyu.algs4.interview.shangjiaosuo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @date 2025/5/18 10:20
 * TODO
 */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer, Integer> cache = new HashMap<>();
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int t = in.nextInt();
            for(int i = 0; i < t; i++) {
                int n = in.nextInt();
                if (cache.containsKey(n)) {
                    System.out.println(cache.get(n));
                    continue;
                }
                int maxMod = n % (n / 2 + 1);
//                for(int j = 1; j < n / 2 + 2; j++) {
//                    maxMod = Math.max(maxMod, n % j);
//                }
                cache.put(n, maxMod);
                System.out.println(maxMod);
            }
        }
    }
}
