package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/9/28 8:48 上午
 * TODO
 */
public class Ex15_1 {
    public static void main(String[] args) {
        int[] nums = {-2,0,1,1,2};
        Ex15_1 ex15_1 = new Ex15_1();
        System.out.println(Arrays.toString(ex15_1.threeSum(nums).toArray()));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        int left = 0, right = 0;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 如果目前最小的三个数的和>0，则说明后面的也无法组成0
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                continue;
            }
            // 如果目前使用最大的两个数+nums[i]<0，则说明说用nums[i]无法组成0
            if (nums[i] + nums[len - 2] + nums[len - 1] < 0) {
                continue;
            }
            left = i + 1;
            right = len - 1;
            int target = -nums[i];
            while (left < right) {
                // 忽略重复数字
                while (left < right && left > i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (right < len - 1 && right > left && nums[right] == nums[right + 1]) {
                    right--;
                }
                if (left >= right) {
                    break;
                }
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(nums[i]);
                    ans.add(nums[left]);
                    ans.add(nums[right]);
                    res.add(ans);
                    left++;
                    right--;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
