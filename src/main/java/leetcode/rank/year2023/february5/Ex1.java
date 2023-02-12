package leetcode.rank.year2023.february5;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2023/2/5 10:14
 * TODO
 */
public class Ex1 {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int gift : gifts) {
            que.offer(gift);
        }
        for (int i = 0; i < k; i++) {
            int gift = que.poll();
            int left = (int) Math.floor(Math.sqrt(gift));
            if (left >= 1) {
                que.offer(left);
            } else {
                que.offer(gift);
            }
        }
        long ans = 0;
        while (!que.isEmpty()) {
            ans += que.poll();
        }
        return ans;
    }
}
