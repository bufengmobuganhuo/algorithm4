package com.mengyu.algs4.exercise.leetcode.prefix_sum;

import java.util.HashMap;

/**
 * @author yu zhang
 */
public class Ex5799 {
    public static void main(String[] args) {
        String word = "aabb";
        Ex5799 ex5799 = new Ex5799();
        System.out.println(ex5799.wonderfulSubstrings(word));
    }

    public long wonderfulSubstrings(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        long res = 0;
        int len = word.length();
        // 前缀和，每一位代表字符出现次数的奇偶性
        int preSum = 0;
        // 每个前缀和的出现次数
        HashMap<Integer,Long> preSumCountMap = new HashMap<>();
        preSumCountMap.put(0, 1L);
        for (int i = 0; i < len; i++) {
            char chr = word.charAt(i);
            // 算上当前字符时的前缀和
            preSum = preSum ^ (1 << chr - 'a');
            long count = preSumCountMap.getOrDefault(preSum, 0L);
            // 在求解字符出现次数时，是用两个下标对应的前缀和做异或，如果两个数相同，则异或结果为0，符合条件
            res += count;
            preSumCountMap.put(preSum, count + 1);
            // 还有一种情况是两个数不同，但是异或结果中只有一位是1，
            // 可以将当前前缀和的每一位翻转（其他位保持不变，此时异或结果就是上述情况）枚举每一种符合条件的情况，并计数
            for (int j = 0; j < 10; j++) {
                int tmp = preSum ^ (1 << j);
                res += preSumCountMap.getOrDefault(tmp, 0L);
            }
        }
        return res;
    }

}
