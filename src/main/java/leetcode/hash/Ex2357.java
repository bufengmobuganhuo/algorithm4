package leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2357 {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num != 0) {
                set.add(num);
            }
        }
        return set.size();
    }
}
