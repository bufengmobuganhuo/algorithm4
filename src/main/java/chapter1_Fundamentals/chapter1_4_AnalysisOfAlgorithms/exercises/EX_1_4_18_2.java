package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/10/12 8:52 上午
 * TODO
 */
public class EX_1_4_18_2 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(8,100,false);
        System.out.println("原数组："+ Arrays.toString(arr));
        System.out.println(solution(arr));
    }
    private static int solution(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int leftNum = mid > 0 ? nums[mid - 1] : Integer.MAX_VALUE;
            int rightNum = mid < nums.length - 1 ? nums[mid + 1] : Integer.MAX_VALUE;
            int midNum = nums[mid];
            if (midNum < leftNum && midNum < rightNum) {
                return midNum;
            } else if (midNum > leftNum) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return -1;
    }
}
