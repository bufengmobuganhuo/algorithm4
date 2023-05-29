package com.mengyu.algs4.interview.bytedance.mar30th;

/**
 * @author yuzhang
 * @date 2021/3/30 上午9:18
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] staple = {2,1,1};
        int[] drinks = {8,8,5,1};
        System.out.println(ex2.breakfastNumber(staple, drinks, 9));
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        sort(staple, 0, staple.length - 1);
        sort(drinks, 0, drinks.length - 1);
        int count = 0;
        int mod = 1000000007;
        for (int staplePrice : staple) {
            if (staplePrice >= x) {
                break;
            } else if (staplePrice + drinks[0] > x) {
                break;
            } else if (staplePrice + drinks[drinks.length - 1] <= x) {
                count = count + (drinks.length % mod);
                continue;
            }
            int idx = binarySearch(drinks, x - staplePrice);
            count =  count + (idx % mod);
        }
        return count % mod;
    }

    private int binarySearch(int[] nums, int target) {
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr <= rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            if (nums[mid] == target) {
                while (mid < nums.length && nums[mid] == target) {
                    mid++;
                }
                return mid;
            } else if (nums[mid] > target) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid + 1;
            }
        }
        return nums[leftPtr] > target ? leftPtr : leftPtr + 1;
    }

    private void sort(int[] nums, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }
        int partitionIdx = partition(nums, startIdx, endIdx);
        sort(nums, startIdx, partitionIdx - 1);
        sort(nums, partitionIdx + 1, endIdx);
    }

    private int partition(int[] nums, int startIdx, int endIdx) {
        int leftPtr = startIdx, rightPtr = endIdx + 1;
        int partitionEle = nums[startIdx];
        while (leftPtr < rightPtr) {
            while (nums[++leftPtr] < partitionEle) {
                if (leftPtr >= endIdx) {
                    break;
                }
            }
            while (nums[--rightPtr] > partitionEle) {
                if (rightPtr <= startIdx) {
                    break;
                }
            }
            if (leftPtr >= rightPtr) {
                break;
            }
            exch(nums, leftPtr, rightPtr);
        }
        exch(nums, startIdx, rightPtr);
        return rightPtr;
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
