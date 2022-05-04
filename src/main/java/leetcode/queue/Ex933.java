package leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yu zhang
 */
public class Ex933 {
    private Deque<Integer> que;

    public Ex933() {
        que = new ArrayDeque<>();
    }

    public int ping(int t) {
        que.offerLast(t);
        while (que.peekFirst() < t - 3000) {
            que.pollFirst();
        }
        return que.size();
    }
}
