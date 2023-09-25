package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

/**
 * 二分查找
 */
public class BinarySerach {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5, 7, 8, 9, 11};
        System.out.println(new BinarySerach().ceil(nums, 6));
        System.out.println(new BinarySerach().floor(nums, 2));
    }

    public static int find(int target, Integer[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (right + left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int ceil(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    public int floor(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

//    // <=target的最大值
//    public static int floor(int target, int[] arr) {
//        int len = arr.length, leftPtr = -1, rightPtr = len - 1;
//        while (leftPtr < rightPtr) {
//            int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
//            if (target <= arr[mid]) {
//                // 求的是<=target的最大值，那如果arr[mid] > target肯定不满足，则-1，下面会有判断=target的情况
//                rightPtr = mid - 1;
//            } else {
//                leftPtr = mid;
//            }
//        }
//        // =target
//        if (leftPtr + 1 < len && arr[leftPtr + 1] == target) {
//            return leftPtr + 1;
//        }
//        // 找到<target
//        return leftPtr;
//    }
//
//    public static int ceil(int target, int[] arr) {
//        int len = arr.length, leftPtr = 0, rightPtr = len;
//        while (leftPtr < rightPtr) {
//            int mid = leftPtr + (rightPtr - leftPtr) / 2;
//            if (target >= arr[mid]) {
//                leftPtr = mid + 1;
//            } else {
//                rightPtr = mid;
//            }
//        }
//        if (rightPtr - 1 >= 0 && arr[rightPtr - 1] == target){
//            return rightPtr - 1;
//        }
//        return rightPtr;
//    }
}
