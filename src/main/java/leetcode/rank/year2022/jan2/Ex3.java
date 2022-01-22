package leetcode.rank.year2022.jan2;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2022/1/2 10:40 上午
 * TODO
 */
public class Ex3 {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        PriorityQueue<Long> que = new PriorityQueue<>();
        for (int asteroid : asteroids) {
            que.offer((long) asteroid);
        }
        long count = mass;
        while (!que.isEmpty()) {
            if (count < que.peek()) {
                return false;
            }
            count += que.poll();
        }
        return true;
    }
}
