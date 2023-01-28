package leetcode.slide_window;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2461 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 4, 2, 9, 9, 9};
        System.out.println(new Ex2461().maximumSubarraySum(nums, 3));
    }

    public long maximumSubarraySum(int[] nums, int k) {
        long ans = 0, curSum = 0;
        int leftPtr = 0, rightPtr = 0;
        Set<Integer> set = new HashSet<>();
        while (rightPtr < nums.length) {
            int num = nums[rightPtr];
            while (set.contains(num) || set.size() >= k) {
                set.remove(nums[leftPtr]);
                curSum -= nums[leftPtr];
                leftPtr++;
            }
            set.add(num);
            curSum += num;
            rightPtr++;
            if (set.size() == k) {
                ans = Math.max(ans, curSum);
            }
        }
        return ans;
    }
}
