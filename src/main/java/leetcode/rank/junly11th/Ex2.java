package leetcode.rank.junly11th;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/7/11 上午10:16
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        String s = "bbcbaba";
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.countPalindromicSubsequence(s));
    }

    public int countPalindromicSubsequence(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (!map.containsKey(chr)) {
                map.computeIfAbsent(chr, key -> new ArrayList<>()).add(i);
                continue;
            }
            List<Integer> list = map.get(chr);
            int startIdx = list.get(list.size() - 1);
            if (i - startIdx + 1 >= 3) {
                StringBuilder sb = new StringBuilder();
                sb.append(chr);
                sb.append(chr);
                for (int j = startIdx + 1; j < i; j++) {
                    sb.insert(1, s.charAt(j));
                    set.add(sb.toString());
                    sb.deleteCharAt(1);
                }
            }
            list.add(i);
        }
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() >= 3) {
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getKey());
                sb.append(entry.getKey());
                sb.append(entry.getKey());
                set.add(sb.toString());
            }
        }
        return set.size();
    }
}
