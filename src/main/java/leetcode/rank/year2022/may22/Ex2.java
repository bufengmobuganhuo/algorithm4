package leetcode.rank.year2022.may22;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2022/5/22 10:25
 * TODO
 */
public class Ex2 {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int ans = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] == rocks[i]) {
                ans++;
            } else if (capacity[i] > rocks[i]) {
                que.offer(capacity[i] - rocks[i]);
            }
        }
        while (!que.isEmpty() && additionalRocks > 0) {
            int left = que.poll();
            if (additionalRocks - left >= 0) {
                ans++;
                additionalRocks -= left;
            }
        }
        return ans;
    }
}
