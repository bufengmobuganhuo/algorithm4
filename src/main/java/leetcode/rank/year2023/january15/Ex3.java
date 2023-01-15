package leetcode.rank.year2023.january15;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2023/1/15 10:36
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {2,3,2,3,1,1,3,3,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println(new Ex3().countGood(nums, 10));
    }
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int leftPtr = 0, rightPtr = 0;
        long ans = 0, curCount = 0;
        while (leftPtr < nums.length) {
            while (rightPtr < nums.length) {
                int num = nums[rightPtr];
                int numCount = countMap.getOrDefault(num, 0);
                countMap.put(num, numCount + 1);
                curCount += numCount;
                if (curCount >= k) {
                    ans += (nums.length - rightPtr);
                    rightPtr++;
                    break;
                }
                rightPtr++;
            }
            while (leftPtr < rightPtr) {
                int count = countMap.get(nums[leftPtr]);
                countMap.put(nums[leftPtr], count - 1);
                curCount -= (count - 1);
                if (curCount >= k) {
                    ans += (nums.length - rightPtr + 1);
                } else {
                    leftPtr++;
                    break;
                }
                leftPtr++;
            }
            if (leftPtr == rightPtr && leftPtr == nums.length - 1) {
                break;
            }
        }
        return ans;
    }
}
