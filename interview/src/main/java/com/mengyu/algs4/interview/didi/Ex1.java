package com.mengyu.algs4.interview.didi;

import java.util.*;

/**
 * @date 2025/4/10 19:07
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        System.out.println(new Ex1().generate());
    }

    private Set<String> generate(){
        LinkedList<Integer> track = new LinkedList<>();
        Set<String> marked = new HashSet<>();
        backtracking(track, marked, new HashSet<>(), new HashSet<>());
        return marked;
    }

    private void backtracking(LinkedList<Integer> track, Set<String> marked, Set<Integer> red, Set<Integer> blue) {
        if (track.size() == 7) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < track.size(); i++) {
                sb.append(track.get(i)).append("-");
            }
            if (marked.contains(sb.toString())) {
                return;
            }
            marked.add(sb.toString());
            return;
        }
        if (track.size() < 6) {
            for (int i = 1; i < 34; i++) {
                if (red.contains(i)) {
                    continue;
                }
                red.add(i);
                track.add(i);
                backtracking(track, marked, red, blue);
                track.removeLast();
                red.remove(i);
            }
        } else {
            for (int i = 1; i < 17; i++) {
                if (blue.contains(i)) {
                    continue;
                }
                blue.add(i);
                track.add(i);
                backtracking(track, marked, red, blue);
                track.removeLast();
                blue.remove(i);
            }
        }

    }
}
