package leetcode.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex448_1 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            nums[(x - 1) % n] += n;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
