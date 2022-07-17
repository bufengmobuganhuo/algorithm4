package leetcode.rank.year2022.july10;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2022/7/10 10:25
 * TODO
 */
public class Ex1 {
    public int fillCups(int[] amount) {
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int amt : amount) {
            if (amt > 0) {
                que.offer(amt);
            }
        }
        int count = 0;
        while (!que.isEmpty()) {
            int cup1 = que.poll();
            int cup2 = 0;
            if (!que.isEmpty()) {
                cup2 = que.poll();
            }
            count++;
            if (cup1 - 1 > 0) {
                que.offer(cup1 - 1);
            }
            if (cup2 - 1 > 0) {
                que.offer(cup2 - 1);
            }
        }
        return count;
    }

}
