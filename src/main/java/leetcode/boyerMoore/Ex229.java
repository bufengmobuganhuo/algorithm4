package leetcode.boyerMoore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex229 {
    /**
     * 超过n/3，则说明最多有2个元素符合条件
     */
    public List<Integer> majorityElement(int[] nums) {
        int[] candidate1 = new int[2];
        int[] candidate2 = new int[2];
        for (int num : nums) {
            // 如果相等，则票数+1
            if (candidate1[0] == num) {
                candidate1[1]++;
            } else if (candidate2[0] == num) {
                candidate2[1]++;
                // 如果票数为0，更新候选人
            } else if (candidate1[1] == 0) {
                candidate1[0] = num;
                candidate1[1] = 1;
            } else if (candidate2[1] == 0) {
                candidate2[0] = num;
                candidate2[1] = 1;
                // 如果票数都不为0，则-1
            } else {
                candidate1[1]--;
                candidate2[1]--;
            }
        }
        candidate1[1] = 0;
        candidate2[1] = 0;
        // 最后检查一遍是不是超过了n/3
        for (int num : nums) {
            if (candidate1[0] == num) {
                candidate1[1]++;
            } else if (candidate2[0] == num) {
                candidate2[1]++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if (candidate1[1] > nums.length / 3) {
            res.add(candidate1[0]);
        }
        if (candidate2[1] > nums.length / 3) {
            res.add(candidate2[0]);
        }
        return res;
    }
}
