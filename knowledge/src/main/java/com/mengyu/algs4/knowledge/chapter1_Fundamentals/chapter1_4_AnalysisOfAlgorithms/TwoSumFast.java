package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms;

import com.mengyu.algs4.utils.ArrayUtil;
import java.util.Arrays;
import java.util.Random;
public class TwoSumFast {
    public static void main(String[] args) {
        Random random = new Random();
        //生成随机数组进行测试
        for (int i = 0; i < 1000000; i++) {
            int length = random.nextInt(500);
            if (length <= 0) {
                continue;
            }
            Integer[] arr = ArrayUtil.createInt(length, 500, true);
            int count1 = TwoSum.getResult(arr);
            int count2 = getResult(arr);
            if (count1 != count2) {
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Sus");
    }

    public static int getResult(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int count = 0;
        int left = 0;
        int right = arr.length - 1;
        Arrays.sort(arr);
        while (left < right) {
            //如果找到了则加一
            if (arr[left] + arr[right] == 0) {
                count++;
                left++;
                right--;
                //此时说明右边较小，left右移
            } else if (arr[left] + arr[right] < 0) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
