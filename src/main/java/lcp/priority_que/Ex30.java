package lcp.priority_que;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex30 {
    public static void main(String[] args) {
        int[] nums = {-1, -1, 10};
        System.out.println(new Ex30().magicTower(nums));
    }
    public int magicTower(int[] nums) {
        if (Arrays.stream(nums).sum() < 0) {
            return -1;
        }
        long sum = 0, right = 0;
        int counter = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            que.offer(nums[i]);
            sum += nums[i];
            if (sum >= 0) {
                continue;
            }
            while (!que.isEmpty() && que.peek() < 0 && sum < 0) {
                int poll = que.poll();
                right += poll;
                sum = sum - poll;
                counter++;
            }
            if (sum < 0) {
                return -1;
            }
        }
        return sum + right < 0 ? -1 : counter;
    }
}
