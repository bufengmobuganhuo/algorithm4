package leetcode.pointer;

import java.util.HashSet;

/**
 * @author yu zhang
 */
public class Ex219 {
    public static void main(String[] args) {
        System.out.println(new Ex219().containsNearbyDuplicate(new int[]{1, 2, 3, 7, 2}, 3));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length <= 1) {
            return false;
        }
        int leftPtr = 0, rightPtr = 0;
        HashSet<Integer> set = new HashSet<>();
        while (rightPtr < nums.length) {
            if (!set.add(nums[rightPtr])) {
                return true;
            }
            if (set.size() > k) {
                set.remove(nums[leftPtr++]);
            }
            rightPtr++;
        }
        return false;
    }
}
