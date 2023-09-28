package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

import com.mengyu.algs4.utils.ArrayUtil;
import java.util.Arrays;
import java.util.Random;

/**
 * 二分查找
 */
public class BinarySerach {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int len = random.nextInt(100000);
            while (len == 0) {
                len = random.nextInt(100000);
            }
            Integer[] arr = ArrayUtil.createInt(len, len);
            int idx = random.nextInt(len);
            Arrays.sort(arr);
            int target = arr[idx] + (random.nextBoolean() ? random.nextInt() : -1 * random.nextInt());
            int floor1 = floor2(arr, target);
            int floor2 = -1;
            for (int j = len - 1; j >= 0; j--) {
                if (arr[j] < target) {
                    floor2 = j;
                    break;
                }
            }
            assert floor1 == floor2;
            System.out.println(i);
        }
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

    // > target的最小值
    public static int ceil2(Integer[] arr, int target) {
        // 如果找不到，结果就是arr.length，这里为了能在二分中遇到arr.length，所以r=arr.length
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (arr[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return l;
    }

    // >= target的最小值
    public static int ceil(Integer[] arr, int target) {
        int l = 0, r = arr.length - 1;
        int ceil = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) {
                // 这里返回的不一定是最左边的
                return arr[mid];
            } else if (arr[mid] > target) {
                ceil = arr[mid];
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ceil;
    }

    // < target的最大值
    public static int floor2(Integer[] arr, int target) {
        // 如果找不到，结果就是-1，这里为了能在二分中遇到-1，所以l=-1
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (arr[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }

    // <= target的最大值
    public static int floor(Integer[] arr, int target) {
        int l = 0, r = arr.length - 1;
        int floor = -1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (arr[mid] == target) {
                // 这里返回的不一定是最右边的
                return arr[mid];
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                floor = arr[mid];
                l = mid + 1;
            }
        }
        return floor;
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
