package leetcode.rank.year2022.may1;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2022/5/1 11:25
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {5, 11, 17, 13, 16, 9, 4, 9, 20};
        System.out.println(new Ex3().countDistinct(nums, 7, 1));
    }
    public int countDistinct(int[] nums, int k, int p) {
        // 1-2-3,用于子数组去重
        Set<String> set = new HashSet<>();
        int count = 0;
        int leftPtr = 0, rightPtr = 0;
        while (rightPtr < nums.length) {
            if (nums[rightPtr] % p == 0) {
                count++;
            }
            while (count > k) {
                if (nums[leftPtr] % p == 0) {
                    count--;
                }
                leftPtr++;
            }
            // 创建字符串，加入set
            for (int i = leftPtr; i <= rightPtr; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j <= rightPtr; j++) {
                    sb.append(nums[j]).append("-");
                }
                set.add(sb.toString());
            }
            rightPtr++;
        }
        return set.size();
    }
}
