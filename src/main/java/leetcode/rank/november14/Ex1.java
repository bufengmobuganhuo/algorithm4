package leetcode.rank.november14;

/**
 * @author yuzhang
 * @date 2021/11/14 10:30 上午
 * TODO
 */
public class Ex1 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int idx = 0;
        int count = 0;
        int n = tickets.length;
        while (true) {
            if (tickets[idx] == 0) {
                idx++;
                idx %= n;
                continue;
            }
            tickets[idx]--;
            count++;
            if (idx % n == k && tickets[k] == 0) {
                break;
            }
            idx++;
            idx %= n;
        }
        return count;
    }
}
