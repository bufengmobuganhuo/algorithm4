package leetcode.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1713 {
    public static void main(String[] args) {
        int[] target = {6, 4, 8, 1, 3, 2};
        int[] arr = {4, 7, 6, 2, 3, 8, 6, 1};
        Ex1713 ex1713 = new Ex1713();
        System.out.println(ex1713.minOperations(target, arr));
    }

    /**
     * target={6,4,8,1,3,2}, arr={4,7,6,2,3,8,6,1}
     * 1. 可以想到求二者的最长公共子序列长度n，那么target.length - n就是答案，但是因为{@link leetcode.dp.Ex1143}的时间复杂度是n^2，不行
     * 2. 可以做一些变换：将arr中的元素转化成元素在target中的下标(不存在则忽略)，则arr'={1，0，5，2，0，3}
     * 如果对target也做相同转换，则target'={0,1,2,3,4,5}
     * 那么问题转化为了求上述两个数组的最长公共子序列
     * 并且由于target'是递增的，那么二者的最长公共子序列也是递增的，则转化为了求arr'的最长递增子序列
     * 可以使用{@link leetcode.greedy.Ex300_1}的贪心算法(list那个)
     */
    public int minOperations(int[] target, int[] arr) {
        // target'
        Map<Integer, Integer> reverseTarget = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            reverseTarget.put(target[i], i);
        }
        List<Integer> dp = new ArrayList<>();
        for (int num : arr) {
            if (reverseTarget.containsKey(num)) {
                // arr'
                int reverseArr = reverseTarget.get(num);
                int idx = floor(dp, reverseArr);
                if (dp.size() == 0 || idx + 1 == dp.size()) {
                    dp.add(reverseArr);
                } else {
                    dp.set(idx + 1, reverseArr);
                }
            }
        }
        return target.length - dp.size();
    }

    private int floor(List<Integer> dp, int target) {
        // 优化
        if (dp.size() == 0 || dp.get(dp.size()-1) < target){
            return dp.size() - 1;
        }
        int leftPtr = -1, rightPtr = dp.size() - 1;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
            if (dp.get(mid) >= target) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid;
            }
        }
        return leftPtr;
    }
}
