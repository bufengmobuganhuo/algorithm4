package xinhuazhiyun;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/3/24 下午8:46
 * TODO
 */
public class Solution {
    /**
     * 目标值
     */
    private int target;
    /**
     * 组合过程中获取的与目标值最接近的和
     */
    private int lastSum;
    /**
     * 需要几个数的和
     */
    private int needNum;

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1};
        int target = 1;
        int needNum = 3;
        Solution solution = new Solution();
        System.out.println(solution.findTarget1(nums, target, needNum));
    }

    private int findTarget2(int[] nums, int target, int needNum) {
        this.needNum = needNum;
        if (nums == null || nums.length < needNum) {
            return -1;
        }
        this.target = target;
        Arrays.sort(nums);
        return threeSum(nums);
    }

    private int threeSum(int[] nums) {
        for (int i = 0; i < nums.length - needNum; i++) {
            twoSum(nums, i);
        }
        return this.lastSum;
    }

    private void twoSum(int[] nums, int startPtr) {
        int leftPtr = startPtr + 1, rightPtr = nums.length - 1;
        while (leftPtr < rightPtr) {
            int sum = nums[startPtr] + nums[leftPtr] + nums[rightPtr];
            if (Math.abs(lastSum - target) > Math.abs(sum - target)) {
                lastSum = sum;
            }
            if (sum > target) {
                rightPtr--;
            } else if (sum < target) {
                leftPtr++;
            } else {
                return;
            }
        }
    }

    private int findTarget1(int[] nums, int target, int needNum) {
        this.needNum = needNum;
        if (nums == null || nums.length < needNum) {
            return -1;
        }
        this.target = target;
        for (int i = 0; i < needNum; i++) {
            this.lastSum += nums[i];
        }
        backtracking(nums, new ArrayList<>(3), 0);
        return lastSum;
    }

    private void backtracking(int[] nums, List<Integer> track, int idx) {
        if (track.size() == needNum) {
            int sum = 0;
            for (Integer integer : track) {
                sum += integer;
            }
            if (Math.abs(lastSum - target) > Math.abs(sum - target)) {
                lastSum = sum;
            }
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            track.add(nums[i]);
            backtracking(nums, track, i + 1);
            track.remove(track.size() - 1);
        }
    }
}
