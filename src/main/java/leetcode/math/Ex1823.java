package leetcode.math;

import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2022/5/4 10:41
 * TODO
 */
public class Ex1823 {
    public static void main(String[] args) {
        System.out.println(new Ex1823().findTheWinner2(5, 2));
    }

    public int findTheWinner2(int n, int k) {
        // 最后获胜的是pos=0的
        int winnerPos = 0;
        for (int i = 2; i <= n; i++) {
            // 当前轮的索引=(pos+k)%当前轮的总个数
            winnerPos = (k + winnerPos) % i;
        }
        return winnerPos + 1;
    }

    public int findTheWinner(int n, int k) {
        LinkedList<Integer> cycle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            cycle.offer(i);
        }
        int startIdx = 0;
        while (cycle.size() > 1) {
            int removeIdx = (startIdx + k - 1) % cycle.size();
            if (removeIdx != cycle.size() - 1) {
                startIdx = removeIdx;
            } else {
                startIdx = 0;
            }
            cycle.remove(removeIdx);
        }
        return cycle.getFirst();
    }
}
