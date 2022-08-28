package leetcode.rank.year2022.august21;

import java.util.*;

/**
 * @author yuzhang
 * @date 2022/8/21 10:23
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        String num = "444947137";
        System.out.println(new Ex2().largestPalindromic(num));
    }

    public String largestPalindromic(String num) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char chr : num.toCharArray()) {
            countMap.put(chr, countMap.getOrDefault(chr, 0) + 1);
        }
        char max = '\0';
        List<int[]> odds = new ArrayList<>(), evens = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                odds.add(new int[]{entry.getKey(), entry.getValue()});
            } else {
                if (entry.getValue() > 1) {
                    evens.add(new int[]{entry.getKey(), entry.getValue()});
                }
                if (entry.getKey() > max) {
                    max = entry.getKey();
                }
            }
        }
        odds.sort((o1, o2) -> o2[0] - o1[0]);
        evens.sort((o1, o2) -> o2[0] - o1[0]);
        StringBuilder sb = new StringBuilder();
        int oddIdx = 0, evensIdx = 0;
        while (oddIdx < odds.size() && evensIdx < evens.size()) {
            if (odds.get(oddIdx)[0] > evens.get(evensIdx)[0]) {
                int count = odds.get(oddIdx)[1];
                char chr = (char) odds.get(oddIdx)[0];
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    tmp.append(chr);
                }
                sb.insert(sb.length() / 2, tmp);
                oddIdx++;
            } else {
                int count = evens.get(evensIdx)[1] > 1 ? evens.get(evensIdx)[1] - 1 : 1;
                char chr = (char) evens.get(evensIdx)[0];
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    tmp.append(chr);
                }
                sb.insert(sb.length() / 2, tmp);
                evensIdx++;
            }
        }
        while (oddIdx < odds.size()) {
            int count = odds.get(oddIdx)[1];
            char chr = (char) odds.get(oddIdx)[0];
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < count; i++) {
                tmp.append(chr);
            }
            sb.insert(sb.length() / 2, tmp);
            oddIdx++;
        }
        while (evensIdx < evens.size()) {
            int count = evens.get(evensIdx)[1] - 1;
            char chr = (char) evens.get(evensIdx)[0];
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < count; i++) {
                tmp.append(chr);
            }
            sb.insert(sb.length() / 2, tmp);
            evensIdx++;
        }
        if (max != '\0') {
            sb.insert(sb.length() / 2, max);
        }
        if (sb.charAt(0) != '0') {
            return sb.toString();
        }
        while (sb.length() > 1) {
            if (sb.charAt(0) == '0' && sb.charAt(sb.length() - 1) == '0') {
                sb.delete(0, 1);
                if (sb.length() == 1) {
                    break;
                }
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        return sb.toString();
    }
}
