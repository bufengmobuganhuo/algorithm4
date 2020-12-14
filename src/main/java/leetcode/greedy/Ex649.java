package leetcode.greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/12/11 上午9:33
 * TODO
 */
public class Ex649 {
    public static void main(String[] args) {
        Ex649 ex649 = new Ex649();
        String senate = "RDD";
        System.out.println(ex649.predictPartyVictory2(senate));
    }

    private static final String RADIANT = "Radiant";
    private static final String DIRE = "Dire";

    public String predictPartyVictory2(String senate) {
        if (senate == null || senate.length() == 0) {
            return senate;
        }
        // 按照出现顺序，存储两种议员在字符串中的索引
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        for (int i = 0; i < senate.length(); i++) {
            char chr = senate.charAt(i);
            if (chr == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            // radiant队首的索引<dire的，说明轮到了radiant,
            // 让他将dire队首（距离radiant最近的右边的对立者）的置为失效（删除队首的元素）
            // 同时将radiant的索引+senate.len，并重新放入队列，表示他会参加下次的投票
            int radIdx = radiant.poll(), direIdx = dire.poll();
            if (radIdx < direIdx) {
                radiant.offer(radIdx + senate.length());
            } else {
                dire.offer(direIdx + senate.length());
            }
        }
        // 谁不为空，谁就是赢家
        return radiant.isEmpty() ? DIRE : RADIANT;
    }

    /**
     * 1. 模仿投票的过程：每次都失效距离他最近的，右边的对立方，直到只剩下一个立场的人
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        if (senate == null || senate.length() == 0) {
            return senate;
        }
        // 构成回环，防止回头找的情况：DR，此时遍历到R，他要失效离他最近的D
        StringBuilder senateBuilder = new StringBuilder(senate);
        senateBuilder.append(senate);
        // 字符串扩大了一倍，当只有两个时，说明投票结束
        while (senateBuilder.length() > 2) {
            for (int i = 0; i < senateBuilder.length() / 2; i++) {
                char chr = senateBuilder.charAt(i);
                char target = chr == 'R' ? 'D' : 'R';
                int ptr = i + 1;
                // 找到离当前议员右边最近的要失效的对立者的位置
                int targetIdx = find(target, ptr, senateBuilder);
                if (targetIdx < 0) {
                    return chr == 'R' ? RADIANT : DIRE;
                }
                // 字符串扩大了一倍，删除两边的对立者
                int len = senateBuilder.length() / 2;
                senateBuilder.deleteCharAt(targetIdx);
                senateBuilder.deleteCharAt(targetIdx + len - 1);
            }
        }
        char chr = senateBuilder.charAt(0);
        return chr == 'R' ? RADIANT : DIRE;
    }

    private int find(char target, int startPtr, StringBuilder senate) {
        while (startPtr < senate.length() && senate.charAt(startPtr) != target) {
            startPtr++;
        }
        if (startPtr >= senate.length()) {
            return -1;
        }
        return startPtr >= senate.length() / 2 ? startPtr - senate.length() / 2 : startPtr;
    }
}
