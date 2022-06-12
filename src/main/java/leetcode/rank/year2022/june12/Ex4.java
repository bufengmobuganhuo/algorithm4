package leetcode.rank.year2022.june12;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/6/12 11:27
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        String[] ideas = {"coffee", "donuts", "time", "toffee"};
        System.out.println(new Ex4().distinctNames(ideas));
    }

    /**
     * 1. 对于一个固定的ideaA，枚举ideaB，那么：
     * 如果ideaA的首字母从x变成了y后，不在字典里 && ideaB的首字母从y变成了x后，不在字典里
     * 则ideaA和ideaB是一对有效的答案
     */
    public long distinctNames(String[] ideas) {
        // <除首字母外组成的字符串，这个字符串包含的首字母的情况>
        // "ime" -> '00000010000000000000000000'："time"除首字母以后是ime，并且首字母是't'
        Map<String, Integer> group = new HashMap<>();
        for (String s : ideas) {
            String t = s.substring(1);
            group.put(t, group.getOrDefault(t, 0) | 1 << (s.charAt(0) - 'a'));
        }
        long ans = 0L;
        // 首字母从j变成i之后，不在字典中的数量
        int[][] cnt = new int[26][26];
        for (int mask : group.values()) {
            // 对于一个后缀字符串"...."
            for (int i = 0; i < 26; i++) {
                // 对于字符i，ideas中不包含字符串"i..."
                if ((mask >> i & 1) == 0) {
                    for (int j = 0; j < 26; j++) {
                        // 对于字符j，ideas中包含字符串"j..."
                        if ((mask >> j & 1) > 0) {
                            // 也就是说对于ideas里的一个字符串"j..."，它可以变成"i..."
                            ++cnt[i][j];
                        }
                    }
                    // 对于字符i，ideas中包含字符串"i..."
                } else {
                    for (int j = 0; j < 26; j++) {
                        // 对于字符j，ideas中不包含字符串"j..."
                        if ((mask >> j & 1) == 0) {
                            // 则ideas中的字符串"i..."可以变成的字符有cnt[i][j]个
                            ans += cnt[i][j];
                        }
                    }
                }
            }
        }
        return ans * 2;
    }
}
