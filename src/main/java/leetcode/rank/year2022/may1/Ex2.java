package leetcode.rank.year2022.may1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/5/1 10:47
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] cards = {70,37,70,41,1,62,71,49,38,50,29,76,29,41,22,66,88,18,85,53};
        System.out.println(new Ex2().minimumCardPickup(cards));
    }
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();
        int minLen = Integer.MIN_VALUE;
        for (int i = 0; i < cards.length; i++) {
            int len = 0;
            int idx = map.getOrDefault(cards[i], -1);
            if (idx != -1) {
                len += idx;
                len += cards.length - i - 1;
                minLen = Math.max(minLen, len);
            }
            map.put(cards[i], i);
        }
        return minLen == Integer.MIN_VALUE ? -1 : cards.length - minLen;
    }
}
