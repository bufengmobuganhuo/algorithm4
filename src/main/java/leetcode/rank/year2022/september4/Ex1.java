package leetcode.rank.year2022.september4;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/9/4 10:33
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().checkDistances("abaccb", new int[2]));
    }
    public boolean checkDistances(String s, int[] distance) {
        int[] count = new int[26];
        Arrays.fill(count, -1);
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (count[chr - 'a'] == -1) {
                count[chr - 'a'] = i;
            } else {
                count[chr - 'a'] = i - count[chr - 'a'] - 1;
            }
        }
        for (int i = 0; i < distance.length; i++) {
            if (count[i] == -1) {
                continue;
            }
            if (count[i] != distance[i]) {
                return false;
            }
        }
        return true;
    }
}
