package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import com.mengyu.algs4.utils.ArrayUtil;
import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/10/10 11:17 上午
 * TODO
 */
public class EX_1_4_17_2 {
    public static void main(String[] args) {
        double[] arr = ArrayUtil.createDouble(5, 10);
        double[] res = solution(arr);
        System.out.println("原数组：" + Arrays.toString(arr) + " 结果：" + Arrays.toString(res));
    }
    private static double[] solution(double[] nums){
        if (nums==null||nums.length<2){
            return null;
        }
        double[] res = new double[2];
        // 线性对数级别
        heapSort(nums);
        res[0]=nums[0];
        res[1]=nums[nums.length-1];
        return res;
    }

    private static void heapSort(double[] nums) {
        int len = nums.length;
        for (int k = (len-1)/2-1; k >= 0; k--) {
            sink(nums,k,len);
        }
        while (len>0){
            exch(nums,0,--len);
            sink(nums,0,len);
        }
    }

    private static void sink(double[] nums, int k, int N) {
        while (2 * k + 1 < N) {
            int j = 2 * k + 1;
            if (j < N - 1 && nums[j] > nums[j + 1]) {
                j++;
            }
            if (nums[k] < nums[j]) {
                break;
            }
            exch(nums, k, j);
            k = j;
        }
    }

    private static void exch(double[] nums, int i, int j) {
        double tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
