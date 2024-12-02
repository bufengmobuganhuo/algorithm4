package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex3208 {

    public static void main(String[] args) {
        int[] colors = {0, 1, 0, 0, 1, 0, 1};
        System.out.println(new Ex3208().numberOfAlternatingGroups2(colors, 6));
    }

    public int numberOfAlternatingGroups2(int[] colors, int k) {
        // cnt: 遍历到i时，已经有的交替组数量
        int cnt = 1, n = colors.length;
        int ans = 0;
        // 为了得到遍历到0时的cnt，则起始点需要从0开始往前回拨k个，则其对应的起始点是1-k。
        // 我们这里比较的是当前节点和上一个节点，所以需要将i=2-k
        for (int i = -k + 2; i < n; i++) {
            // 当前节点和上一个节点
            if (colors[(i + n) % n] != colors[(i + n - 1) % n]) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt >= k) {
                ans++;
            }
        }
        return ans;
    }

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int cnt = 0, n = colors.length;
        int j = 0;
        for (int i = 0; i < n; ) {
            while (colors[j % n] != colors[(j + 1) % n] && j - i + 1 < k) {
                j++;
            }
            if (j - i + 1 == k) {
                cnt++;
                i++;
            } else {
                i = j + 1;
                j = i;
            }
        }
        return cnt;
    }
}
