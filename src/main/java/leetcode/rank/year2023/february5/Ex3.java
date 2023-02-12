package leetcode.rank.year2023.february5;

/**
 * @author yuzhang
 * @date 2023/2/5 10:41
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 9};
        System.out.println(new Ex3().minCapability(nums, 2));
    }

//    private int ans = 0;
//
//    private int k = 0;

    public int minCapability(int[] nums, int k) {
        // 找到nums中的最大最小值，最终结果只能是[left, right]范围内
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }
        // 以2分查找答案
        while (left <= right) {
            // 假如经过k次偷窃，偷窃能力是mid
            int mid = left + (right - left) / 2;
            // 假如偷窃结果可以是mid，则向左缩小答案，直到找到偷窃能力的最小值
            if (check(nums, mid, k)) {
                right = mid - 1;
            } else {
                // 否则扩大可能的偷窃能力，直到找到最小的偷窃能力
                left = mid + 1;
            }
        }
        return right + 1;
    }

    public boolean check(int[] nums,int max,int k){
        // 偷窃次数
        int count = 0;
        for (int i = 0; i < nums.length;) {
            // max 表示偷窃完所有房子后的单间房屋最大获取，则如果nums[i]比小，则说明可以偷
            if (nums[i] <= max) {
                // 偷完当前房屋后，相邻房屋不能偷，所以+2
                i += 2;
                count++;
            } else {
                // 当前房屋没有被偷，则下一个可以被偷
                i++;
            }
        }
        return count >= k;
    }

//    private void rob(int[] nums, int idx, int max, int count, boolean canRob) {
//        if (idx >= nums.length) {
//            if (count >= k && max < ans) {
//                ans = max;
//            }
//            return;
//        }
//        if (canRob) {
//            int tmp = max;
//            max = Math.max(max, nums[idx]);
//            rob(nums, idx + 1, max, count + 1, false);
//
//            rob(nums, idx + 1, tmp, count, true);
//        } else {
//            rob(nums, idx + 1, max, count, true);
//        }
//    }


}
