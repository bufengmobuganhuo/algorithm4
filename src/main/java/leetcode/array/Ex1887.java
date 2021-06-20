package leetcode.array;

import java.util.HashMap;

/**
 * @author yuzhang
 * @date 2021/6/6 上午11:18
 * TODO
 */
public class Ex1887 {
    public static void main(String[] args) {
        int[] nums = {5,1,3};
        Ex1887 ex1887 = new Ex1887();
        System.out.println(ex1887.reductionOperations(nums));
    }
    /**
     * 1. 最终从数组中的最大值变成了数组中的最小值
     * 2. 每次都改变一个元素，变化的范围就是从max -> min
     */
    public int reductionOperations(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        // 数组中每个数的出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int count = 0;
        int pre = 0;
        for (int i = max; i > min; i--) {
            if (map.containsKey(i)){
                pre += map.get(i);
                count += pre;
            }
        }
        return count;
    }

}
