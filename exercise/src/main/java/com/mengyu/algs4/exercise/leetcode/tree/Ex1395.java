package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1395 {
    public static void main(String[] args) {
        int[] rating = {2, 5, 3, 4, 1};
        System.out.println(new Ex1395().numTeams2(rating));
    }

    private int len;

    private int[] tree;

    public int numTeams2(int[] rating) {
        this.len = rating.length;
        // 排序后的rating
        int[] a = new int[len + 1];
        a[0] = -1;
        System.arraycopy(rating, 0, a, 1, rating.length);
        Arrays.sort(a);
        tree = new int[len + 1];
        // kless[i] = 1，表示rating[i]在对应i位置存在
        int[] kless = new int[len];
        int[] kmore = new int[len];
        // 从右到左遍历，那么对于rating[i]，先遇到的肯定是在他右边的元素，可以求出kless和kmore
        for (int i = len - 1; i >= 0; i--) {
            // 找到在数组a中的位置
            int idInA = searchIdxInA(a, rating[i]);
            // a排序后，idInA左边的就是比他小的数，可以求前缀和[1...idInA-1]
            kless[i] = queryPreSum(idInA - 1);
            // a排序后，idInA右边的就是比他大的数，可以求前缀和[idInA+1...len+1]
            kmore[i] = queryPreSum(len) - queryPreSum(idInA);
            // 在idInA位置增加了一个元素
            update(idInA, 1);
        }
        tree = new int[len + 1];
        int[] iless = new int[len];
        int[] imore = new int[len];
        // // 从左到右遍历，那么对于rating[i]，先遇到的肯定是在他左边的元素，可以求出iless和imore
        for (int i = 0; i < len; i++) {
            int idInA = searchIdxInA(a, rating[i]);
            iless[i] = queryPreSum(idInA);
            imore[i] = queryPreSum(len) - queryPreSum(idInA);
            update(idInA, 1);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += iless[i] * kmore[i] + imore[i] * kless[i];
        }
        return ans;
    }

    private int searchIdxInA(int[] a, int target) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private void update(int i, int delta) {
        while (i <= len) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    private int queryPreSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    private int lowbit(int i) {
        return i & (-i);
    }

    /**
     * 方法一：枚举中间值
     * 1. 对于(i, j, k)，枚举所有的j，找到：
     * (1)j左边，比rating[j]大的数的个数, imore
     * (2)j左边，比rating[j]小的数的个数，iless
     * (3)j右边，比rating[j]大的数的个数，kmore
     * (4)j右边，比rating[j]小的数的个数，kless
     * <p>
     * 那么最终的结果=imore * kless + iless * kmore
     */
    public int numTeams(int[] rating) {
        int ans = 0;
        for (int j = 1; j < rating.length; j++) {
            int iless = 0, imore = 0, kless = 0, kmore = 0;
            for (int i = 0; i < j; i++) {
                if (rating[i] < rating[j]) {
                    iless++;
                } else if (rating[i] > rating[j]) {
                    imore++;
                }
            }
            for (int k = j + 1; k < rating.length; k++) {
                if (rating[k] < rating[j]) {
                    kless++;
                } else if (rating[k] > rating[j]) {
                    kmore++;
                }
            }
            ans += iless * kmore + imore * kless;
        }
        return ans;
    }
}
