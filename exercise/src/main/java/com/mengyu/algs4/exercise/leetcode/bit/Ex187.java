package com.mengyu.algs4.exercise.leetcode.bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex187 {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCAAAAAGGGTTT";
        Ex187 ex187 = new Ex187();
        System.out.println(ex187.findRepeatedDnaSequences2(s));
        System.out.println(1 << 20);

    }

    /**
     * 1. 和上述一样，映射成比特位计算，但是使用一个长度为10的滑动窗口
     * 2. 当滑动窗口向右滑动一位时（假设当前滑动窗口的值为x），首先令x = x << 2(此时x的末尾会添加00)
     * 同时最右边有一个字符进来，所以此时x = x | bin(chr) ,就是x的最末尾2位（左移后变成了00）与字符对应的二进制做或运算，这样最右边的两位就进来了
     * (此时x变成了22个比特)
     * 3. 让窗口最左边的元素出去，x = x & ((1 << 20) - 1)，字符串长度是10，则正常情况下，窗口对应的数字是20比特
     * 1 << 20 : 1,00,00,00,00,00,00,00,00,00,00
     * (1 << 20) - 1 : 00,11,11,11,11,11,11,11,11,11,11 (记为y)
     * 那么x & y就会把x的第21，20位的比特位置为0, 则窗口最左边的字符被移出窗口
     *
     * 综合上述，在滑动窗口移动时，x = ((x << 2) | bin(chr)) & ((1 << 20) - 1)
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = s.length();
        int len = 10;
        int x = 0;
        // 初始条件下滑动窗口的值，此时最左边的不需要出滑动窗口, 先入窗口9个，此时窗口最高位为00，后面再添加
        for (int i = 0; i < len - 1; i++) {
            x = (x << 2) | convert(s.charAt(i));
        }
        for (int i = 0; i < n - len + 1; ++i) {
            x = ((x << 2) | convert(s.charAt(i + len - 1))) & ((1 << 20) - 1);
            int count = cnt.getOrDefault(x, 0);
            cnt.put(x, count + 1);
            if (count == 1) {
                ans.add(s.substring(i, i + len));
            }
        }
        return ans;
    }

    private int convert(char chr) {
        switch (chr) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            default:
                return 3;
        }
    }

    /**
     * 1. 令字符对应成数字：A: 00, C: 01, G: 10, T: 11
     * 2. 将一个子字符串转化成数字，然后用Map来判断，这样可以减少占用空间（同时提高Map里的比较效率）
     * 时间复杂度：O(10n)
     */
    public List<String> findRepeatedDnaSequences(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        int len = 10;
        for (int i = 0; i < s.length() - len + 1; i++) {
            String subStr = s.substring(i, i + len);
            int num = convert(subStr);
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
            if (count == 1) {
                ans.add(subStr);
            }
        }
        return ans;
    }

    private int convert(String str) {
        int res = 0;
        int len = 10;
        for (int i = 0; i < len; i++) {
            char chr = str.charAt(len - i - 1);
            res += convert(chr, i);
        }
        return res;
    }

    private int convert(char chr, int i) {
        switch (chr) {
            case 'A':
                return 0;
            case 'G':
                return pow(2 * i + 1);
            case 'T':
                return pow(2 * i + 1) + pow(2 * i);
            default:
                return pow(2 * i);
        }
    }

    private int pow(int b) {
        if (b == 0) {
            return 1;
        }
        int a = 2;
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
            }
            a *= a;
            b >>= 1;
        }
        return res;
    }
}
