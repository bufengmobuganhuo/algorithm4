package com.mengyu.algs4.exercise.leetcode.prefix_sum;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex2271 {

    public static void main(String[] args) {
        int[][] tiles = {{1, 5}, {10, 11}, {12, 18}, {20, 25}, {30, 32}};
        System.out.println(new Ex2271().maximumWhiteTiles(tiles, 10));
    }

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        int n = tiles.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = tiles[0][1] - tiles[0][0] + 1;
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + tiles[i][1] - tiles[i][0] + 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (tiles[i][1] - tiles[i][0] + 1 >= carpetLen) {
                ans = carpetLen;
                break;
            } else {
                int target = tiles[i][0] + carpetLen - 1;
                int leftPtr = i, rightPtr = n;
                while (leftPtr < rightPtr) {
                    int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
                    if (target >= tiles[midPtr][1]) {
                        leftPtr = midPtr + 1;
                    } else {
                        rightPtr = midPtr;
                    }
                }
                int pre = i > 0 ? prefixSum[i - 1] : 0;
                if (rightPtr - 1 >= 0 && tiles[rightPtr - 1][1] == target) {
                    ans = Math.max(ans, prefixSum[rightPtr - 1] - pre);
                } else if (rightPtr < n) {
                    if (tiles[rightPtr][0] > target) {
                        ans = Math.max(ans, prefixSum[rightPtr] - pre - (tiles[rightPtr][1] - tiles[rightPtr][0] + 1));
                    } else {
                        ans = Math.max(ans, prefixSum[rightPtr] - pre - (tiles[rightPtr][1] - target));
                    }

                } else {
                    ans = Math.max(ans, prefixSum[n - 1] - pre);
                }
            }
        }
        return ans;
    }
}
