package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2020/12/4 上午10:15
 * TODO
 */
public class Ex659 {
    public static void main(String[] args) {
        Ex659 ex659 = new Ex659();
        int[] nums = {1, 2, 3, 3, 4, 5};
        System.out.println(ex659.isPossible2(nums));
    }

    /**
     * 应该尽量避免新建短的子序列
     * 1. 两个Map：map1(数字 -> 原数组中每个数字的剩余个数)，map2(数字 -> 以该数字结尾的子序列的数量)
     * 2. 对于一个数x：
     * （1）如果在map2中以x-1结尾的子序列长度>0,则以x结尾的子序列数量+1，以x-1结尾的子序列数量-1（长度是以步骤（2）保证的），同时更新map1(x)-1
     * （2）否则，如果想构成满足长度条件的子序列，则需要(x,x+1,x+2)：
     * 1⃣️ 如果map1中，x+1，x+2的剩余次数均>0，则说明可以组成一个子序列，则更新map1,map2对应值
     * 2⃣️ 否则，不可能组成，返回false
     */
    public boolean isPossible2(int[] nums) {
        // 数字  -> 剩余次数
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int x : nums) {
            map1.put(x, map1.getOrDefault(x, 0) + 1);
        }
        for (int x : nums) {
            // 用完了就不需要再判断了
            if (map1.get(x) <= 0) {
                continue;
            }
            int preLengthCount = map2.getOrDefault(x - 1, 0);
            if (preLengthCount > 0) {
                map2.put(x, map2.getOrDefault(x, 0) + 1);
                map2.put(x - 1, preLengthCount - 1);
                // x用过一次，需要更新
                map1.put(x, map1.get(x) - 1);
            } else {
                int xLeftCount1 = map1.getOrDefault(x + 1, 0);
                int xLeftCount2 = map1.getOrDefault(x + 2, 0);
                if (xLeftCount1 > 0 && xLeftCount2 > 0) {
                    // x,x+1,x+2都用过一次，需要更新
                    map1.put(x, map1.get(x) - 1);
                    map1.put(x + 1, map1.get(x + 1) - 1);
                    map1.put(x + 2, map1.get(x + 2) - 1);
                    map2.put(x + 2, map2.getOrDefault(x + 2, 0) + 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 应该尽可能满足那些子序列长度较小的
     * 1. 使用一个Map（子序列最后一个数字，以该字符结尾的子序列长度（使用最小优先队列存储））
     * 2. 对于一个字符x：
     * （1）如果Map中存在以x-1结尾的子序列，则可以从对应的优先队列中取出最小长度+1，此时以x-1结尾的子序列少了一个
     * 以x结尾的子序列多了一个
     * （2）如果Map中不存在以x-1结尾的子序列，则创建一个以x结尾的、长度为1的子序列放入优先队列
     * 3. 当Map中所有子序列的长度都 >= 3时，说明可以组成
     */
    public boolean isPossible(int[] nums) {
        // 数字 -> 以该字符结尾的子序列长度集合
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<>());
            }
            // 如果包含以x-1结尾的子序列
            if (map.containsKey(x - 1)) {
                int preLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(preLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            PriorityQueue<Integer> priorityQueue = entry.getValue();
            while (!priorityQueue.isEmpty()) {
                if (priorityQueue.poll() < 3) {
                    return false;
                }
            }
        }
        return true;
    }
}
