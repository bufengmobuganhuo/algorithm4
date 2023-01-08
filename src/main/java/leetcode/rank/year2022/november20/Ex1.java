package leetcode.rank.year2022.november20;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2022/11/20 10:10
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        int[] nums = {4, 4, 2, 4, 3};
        System.out.println(new Ex1().unequalTriplets(nums));
    }

    private int count = 0;

    public int unequalTriplets(int[] nums) {
        this.count = 0;
        backtracking(nums, 0, new HashSet<>());
        return count;
    }

    private void backtracking(int[] nums, int idx, Set<Integer> set) {
        if (idx == nums.length || set.size() == 3) {
            if (set.size() == 3) {
                count++;
            }
            return;
        }
        if (!set.contains(nums[idx])) {
            set.add(nums[idx]);
            backtracking(nums, idx + 1, set);
            set.remove(nums[idx]);
            backtracking(nums, idx + 1, set);
        } else {
            backtracking(nums, idx + 1, set);
        }
    }
}
