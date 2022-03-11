package leetcode.prefix_sum;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2055 {
    public static void main(String[] args) {
        int[][] queries = {
                {2,5},{5,9}
        };
        System.out.println(Arrays.toString(new Ex2055().platesBetweenCandles("**|**|***|", queries)));
    }
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int len = s.length();
        int[] mostLeft = new int[len];
        int[] mostRight = new int[len];
        long[] preSum = new long[len];
        int leftStarIdx = -1, rightStarIdx = len;
        if (s.charAt(0) == '*') {
            mostLeft[0] = -1;
            preSum[0] = 1;
        } else {
            leftStarIdx = 0;
        }
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '*') {
                mostLeft[i] = leftStarIdx;
                preSum[i] = preSum[i - 1] + 1;
            } else {
                leftStarIdx = i;
                mostLeft[i] = i;
                preSum[i] = preSum[i - 1];
            }
            if (s.charAt(len - i - 1) == '*') {
                mostRight[len - i - 1] = rightStarIdx;
            } else {
                rightStarIdx = len - i - 1;
                mostRight[len - i - 1] = len - i - 1;
            }
        }
        if (s.charAt(len - 1) == '*') {
            mostRight[len - 1] = len;
        } else {
            mostRight[len - 1] = len - 1;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0], right = queries[i][1];
            int startPtr = mostRight[left], endPtr = mostLeft[right];
            if (startPtr > right || endPtr < left) {
                ans[i] = 0;
            } else {
                ans[i] = (int) (preSum[endPtr] - preSum[startPtr]);
            }
        }
        return ans;
    }
}
