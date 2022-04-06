package leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2191 {
    public static void main(String[] args) {
        int[] mapping = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(new Ex2191().sortJumbled(mapping, nums)));
    }
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int[][] tmp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                tmp[i][1] = mapping[0];
                continue;
            }
            int mappedNum = 0;
            int multi = 1;
            while (num != 0) {
                int digit = num % 10;
                mappedNum += mapping[digit] * multi;
                num /= 10;
                multi *= 10;
            }
            tmp[i][0] = nums[i];
            tmp[i][1] = mappedNum;
        }
        Arrays.sort(tmp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] res = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            res[i] = tmp[i][0];
        }
        return res;
    }
}
