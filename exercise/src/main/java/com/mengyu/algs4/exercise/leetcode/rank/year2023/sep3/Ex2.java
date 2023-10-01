package com.mengyu.algs4.exercise.leetcode.rank.year2023.sep3;

import java.util.HashSet;
import java.util.Set;

/**
 * @date 2023/9/3 10:01
 * TODO
 */
public class Ex2 {

    public static void main(String[] args) {
        System.out.println(new Ex2().minimumOperations("53478"));
    }

    private Set<String> set = new HashSet<>();

    public Ex2() {
        set.add("0");
        set.add("00");
        set.add("25");
        set.add("50");
        set.add("75");
    }

    private int ans;

    public int minimumOperations(String num) {
        if (num.length() <= 2) {
            if (set.contains(num)) {
                return 0;
            }
            if (num.charAt(num.length() - 1) == '0') {
                return num.length() - 1;
            } else {
                return num.length();
            }
        }
        int cnt = 0, idx = num.length() - 1;
        while (idx >= 0 && num.charAt(idx) != '0' && num.charAt(idx) != '5') {
            cnt++;
            idx--;
        }
        ans = Integer.MAX_VALUE;
        track(num, idx, 0, new StringBuilder());
        return cnt + ans;
    }

    private void track(String num, int idx, int cnt, StringBuilder track) {
        if (idx < 0 || track.length() == 2) {
            if (set.contains(track.toString()) || track.toString().equals("0") || track.length() == 0) {
                ans = Math.min(ans, cnt);
            }
            return;
        }
        char chr = num.charAt(idx);
        if (track.length() == 0) {
            if (chr != '0' && chr != '5') {
                track(num, idx - 1, cnt + 1, track);
            } else {
                track.append(chr);
                track(num, idx - 1, cnt, track);
                track.deleteCharAt(track.length() - 1);
                track(num, idx - 1, cnt + 1, track);
            }
        } else {
            track.insert(0, chr);
            if (set.contains(track.toString())) {
                track(num, idx - 1, cnt, track);
                track.deleteCharAt(0);
            } else {
                track(num, idx - 1, cnt + 1, track.deleteCharAt(0));
            }
        }
    }
}
