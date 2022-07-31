package leetcode.rank.year2022.july17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/7/17 10:21
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] nums = {243,205,369,253,72,484,300,161,188,69,309,96,226,308,252};
        System.out.println(new Ex2().maximumSum(nums));
    }
    public int maximumSum(int[] nums) {
        int ans = -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int digit = 0, tmp = num, sum = 0;
            while (tmp != 0) {
                digit = tmp % 10;
                tmp /= 10;
                sum += digit;
            }
            List<Integer> arr = map.computeIfAbsent(sum, key -> new ArrayList<>());
            if (arr.size() < 2) {
                arr.add(num);
                if (arr.size() == 2) {
                    ans = Math.max(ans, arr.get(0) + arr.get(1));
                }
            }  else if (arr.size() == 2) {
                if (arr.get(0) < num && arr.get(1) > num) {
                    arr.set(0, num);
                } else if (arr.get(1) < num && arr.get(0) > num) {
                    arr.set(1, num);
                } else if (arr.get(0) < num && arr.get(1) < num) {
                    if (arr.get(0) > arr.get(1)) {
                        arr.set(1, num);
                    } else if (arr.get(1) > arr.get(0)) {
                        arr.set(0, num);
                    } else {
                        arr.set(0, num);
                    }
                }
                ans = Math.max(ans, arr.get(0) + arr.get(1));
            }
        }
        return ans;
    }
}
