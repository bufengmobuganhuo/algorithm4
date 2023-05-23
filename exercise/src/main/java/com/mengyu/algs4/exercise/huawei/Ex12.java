package com.mengyu.algs4.exercise.huawei;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/3/21 上午10:48
 * TODO
 */
public class Ex12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<String> sortedSet;
        while (scanner.hasNext()) {
            String time = scanner.nextLine();
            sortedSet = new TreeSet<>();
            backtracking(time, 0, new StringBuilder(), sortedSet);
            TreeSet<String> headSet = (TreeSet<String>) sortedSet.headSet(time);
            String less = null;
            if (headSet.size() > 0) {
                less = sortedSet.headSet(time).first();
            }
            String larger = sortedSet.ceiling(time);
            if (larger != null) {
                System.out.println(larger);
            } else if (less != null) {
                System.out.println(less);
            } else {
                System.out.println(time);
            }
        }
    }

    private static void backtracking(String time, int idx, StringBuilder track, TreeSet<String> set) {
        if (idx >= 4) {
            track.insert(2, ':');
            if (time.equals(track.toString())) {
                track.deleteCharAt(2);
                return;
            }
            set.add(track.toString());
            track.deleteCharAt(2);
            return;
        }
        for (int i = 0; i < time.length(); i++) {
            char chr = time.charAt(i);
            if (chr == ':') {
                continue;
            }
            if (idx == 0 && chr > '2') {
                continue;
            }
            if (idx == 1 && track.charAt(0) == '2' && chr > '3') {
                continue;
            }
            if (idx == 2 && chr > '5') {
                continue;
            }
            track.append(chr);
            backtracking(time, idx + 1, track, set);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
