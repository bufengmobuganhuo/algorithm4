package leetcode.rank.year2022.apr10;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2022/4/10 10:28 AM
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {

    }
    private static final int MOD = (int) (Math.pow(10, 9) + 7);
    // 180820950
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int num : nums) {
            que.offer(num);
        }
        for (int i = 0; i < k; i++) {
            que.offer(que.poll() + 1);
        }
        long ans = 1;
        while (!que.isEmpty()) {
            ans = (ans * que.poll()) % MOD;
        }
        return (int) ans;
    }
}
