package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_1_StringSorts;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/27 16:05
 * 低位优先的字符串排序
 */
public class LSD {
    public static void main(String[] args) {
        String[] arr = {
                "4PGC838",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
        };
        LSD.sort(arr, 7);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr     字符串数组
     * @param wordLen 每个字符串的长度
     */
    public static void sort(String[] arr, int wordLen) {
        int arrLen = arr.length;
        //ascii码的范围[0,256)
        int R = 256;
        String[] aux = new String[arrLen];
        //从右到左遍历字符串
        for (int wordCount = wordLen - 1; wordCount >= 0; wordCount--) {
            int[] count = new int[R + 1];
            //第一步：频率统计,charAt(wordCount)就是键
            for (int j = 0; j < arrLen; j++) {
                count[arr[j].charAt(wordCount) + 1] += 1;
            }
            //第二步：转化为起始索引位置
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            //第三步：数据分类
            for (int j = 0; j < arrLen; j++) {
                aux[count[arr[j].charAt(wordCount)]++] = arr[j];
            }
            System.arraycopy(aux, 0, arr, 0, aux.length);
        }
    }
}
