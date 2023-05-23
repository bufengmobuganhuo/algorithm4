package com.mengyu.algs4.exercise.leetcode.prefix_sum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1177 {
    public static void main(String[] args) {
        String s = "rkzavgdmdgt";
        Ex1177 ex1177 = new Ex1177();
        int[][] queries = {
            {5, 8, 0},
            {7, 9, 1},
            {3, 6, 4},
            {5, 5, 1},
            {8, 10, 0},
            {3, 9, 5},
            {0, 10, 10},
            {6, 8, 3},
        };
        System.out.println(ex1177.canMakePaliQueries1(s, queries));
    }

    /**
     * 对于一个字符串，可以通过统计字符次数的奇偶性来实现计算最小修改操作数：
     * 1. 对于出现偶数次的字符，只需要调整他们的位置，将其左右分别排列即可，不需要修改字符
     * 2. 对于出现奇数次的字符，左右排列后，还会剩一个字符，可以将它修改为另一个出现奇数次的字符
     * 故最小操作数=奇数次数字符个数n/2 向下取整，因为如果n为奇数，则说明整个字符串的长度是奇数，则可以让一个元素放在中间，这个不需要被修改
     * 维护一个二维数组prefixSum[i][j]：从s(0....i)，26字母表中第j个字符的出现次数，则对于s(i...j)，prefixSum[j]-prefixSum[i]就是每个字符的出现次数
     */
    public List<Boolean> canMakePaliQueries1(String s, int[][] queries) {
        int len = s.length();
        int[][] prefixSum = new int[len + 1][26];
        for (int i = 1; i < len + 1; i++) {
            System.arraycopy(prefixSum[i - 1], 0, prefixSum[i], 0, 26);
            prefixSum[i][s.charAt(i - 1) - 'a']++;
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0], j = query[1], k = query[2];
            int count = 0;
            for (int l = 0; l < 26; l++) {
                if ((prefixSum[j + 1][l] - prefixSum[i][l]) % 2 == 1) {
                    count++;
                }
            }
            // 需要的修改操作数
            res.add(k >= count / 2);
        }
        return res;
    }

    /**
     * 使用位运算减少空间占用：
     * 1. 使用一个26bit的数，每一位从低到高分别代表a,b,c..，
     * 1<<'a'-'a'=000...001，
     * 1<<'b'-'a'=000...010，
     * 1<<'c'-'a'=000...100,
     * ...
     * 2. 假设上一个字符串是a，则000...001，此时又来了一个a，做异或操作：000...000，则表明a的出现次数是偶数
     * 假设上一个字符串是a，则000...001，此时来了一个b，做异或操作：000...011，表明a, b出现的次数都是奇数
     * 同理，s(0...i)(abc)=000...111，s(0...j)(abcab)=000...100，则s(i+1...j)=s(0...i) ^ s(0...j)=000...011
     * 下面就是计算出现奇数次字符的个数：
     * 3. 对于一个数：000...011，和1做与运算，就可以知道对应最低为是1还是0：
     * 000...011 & 1 = 000...001，第0位是1，然后让这个数右移一位，就可以比较第1个数：
     * 000...01 & 1 = 000...01，第1位是1，一次类推
     */
    public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
        int len = s.length();
        // 加一个哨兵
        int[] counter = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            char chr = s.charAt(i - 1);
            counter[i] = counter[i - 1] ^ (1 << chr - 'a');
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0], j = query[1], k = query[2];
            int count = counter[j + 1] ^ counter[i];
            // 需要的修改操作数
            int cnt = 0;
            while (count != 0) {
                if ((count & 1) == 1) {
                    cnt++;
                }
                count = count >> 1;
            }
            res.add(k >= cnt / 2);
        }
        return res;
    }
}
