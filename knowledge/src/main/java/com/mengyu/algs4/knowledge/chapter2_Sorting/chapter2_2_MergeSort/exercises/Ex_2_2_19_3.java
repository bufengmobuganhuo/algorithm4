package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;

/**
 * @author yuzhang
 * @date 2021/2/5 上午9:53
 * TODO
 */
public class Ex_2_2_19_3 {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2, 1, 6, 3};
        System.out.println(solution(nums));
        nums = new int[]{3, 4, 5, 1, 2, 1, 6, 3};
        System.out.println(insertionSort(nums));
    }

    private static int count = 0;
    private static int[] aux;

    public static int solution(int[] nums) {
        aux = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        return count;
    }

    private static int insertionSort(int[] nums){
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j-1]; j--) {
                int tmp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = tmp;
                count++;
            }
        }
        return count;
    }

    private static void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(nums, start, mid);
        sort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        System.arraycopy(nums, start, aux, start, end - start + 1);
        int i = start, j = mid + 1;
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                nums[k] = aux[j++];
            } else if (j > end) {
                nums[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                nums[k] = aux[i++];
            } else {
                count += (j - k);
                nums[k] = aux[j++];
            }
        }
    }
}
