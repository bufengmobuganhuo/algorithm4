package leetcode.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author yu zhang
 */
public class Ex398 {
    private int[] nums;

    private static final Random random = new Random();

    public Ex398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int ans = 0;
        for (int i = 1, cnt = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                // 找到了cnt个target，从cnt个中随机选一个，变成了水塘抽样，只不过k=1
                cnt++;
                if (random.nextInt(cnt) == 0) {
                    ans = i;
                }
            }
        }
        return ans;
    }
}
