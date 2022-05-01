package chapter9_Math;

import java.util.List;
import java.util.Random;

/**
 * @author yu zhang
 * 水塘抽样
 */
public class ReservoirSample {
    /**
     * 从list中随机抽样k个元素，保证list.size() > k
     */
    public static int[] getRandom(List<Integer> list, int k) {
        Random random = new Random();
        int[] ans = new int[k];
        // 前k个先保留
        for (int i = 0; i < k; i++) {
            ans[i] = list.get(i);
        }
        for (int i = k; i < list.size(); i++) {
            // 概率就是1/k
            int rand = random.nextInt(i + 1);
            // 以1/k的概率替换已选好的元素，就是保留下的概率就是k/i
            if (rand < k) {
                ans[rand] = list.get(i);
            }
        }
        return ans;
    }
}
