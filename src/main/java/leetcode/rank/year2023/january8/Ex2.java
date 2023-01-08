package leetcode.rank.year2023.january8;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2023/1/8 10:07
 * TODO
 */
public class Ex2 {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            que.offer(num);
        }
        long score = 0;
        for (int i = 0; i < k; i++) {
            int num = que.poll();
            score += num;
            num = (num + 3 - 1) / 3;
            que.offer(num);
        }
        return score;
    }
}
