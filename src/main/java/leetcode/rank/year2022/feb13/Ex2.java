package leetcode.rank.year2022.feb13;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/2/13 10:08 上午
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 2, 4, 3};
        System.out.println(new Ex2().minimumOperations(nums));
    }
    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> oddMap = new HashMap<>();
        Map<Integer, Integer> evenMap = new HashMap<>();
        int oddMaxCount = 0, oddMax = -1, oddSecondMaxCount = 0;
        int evenMaxCount = 0, evenMax = -1, evenSecondMaxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                int count = oddMap.getOrDefault(nums[i], 0);
                oddMap.put(nums[i], count + 1);
            } else {
                int count = evenMap.getOrDefault(nums[i], 0);
                evenMap.put(nums[i], count + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : oddMap.entrySet()) {
            if (entry.getValue() > oddMaxCount) {
                oddSecondMaxCount = oddMaxCount;
                oddMaxCount = entry.getValue();
                oddMax = entry.getKey();
            } else if (entry.getValue() > oddSecondMaxCount) {
                oddSecondMaxCount = entry.getValue();
            }
        }
        for (Map.Entry<Integer, Integer> entry : evenMap.entrySet()) {
            if (entry.getValue() > evenMaxCount) {
                evenSecondMaxCount = evenMaxCount;
                evenMaxCount = entry.getValue();
                evenMax = entry.getKey();
            } else if (entry.getValue() > evenSecondMaxCount) {
                evenSecondMaxCount = entry.getValue();
            }
        }
        int halfLen = nums.length / 2;
        int offset = nums.length % 2 == 0 ? 0 : 1;
        if (oddMax != evenMax) {
            return halfLen + offset - oddMaxCount + halfLen - evenMaxCount;
        } else {
            return Math.min(halfLen + offset - oddMaxCount + halfLen - evenSecondMaxCount,
                    halfLen + offset - oddSecondMaxCount + halfLen - evenMaxCount);
        }
    }
}
