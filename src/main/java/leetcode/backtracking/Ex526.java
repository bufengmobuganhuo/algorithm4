package leetcode.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2021/3/10 上午10:55
 * TODO
 */
public class Ex526 {
    private int count;

    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];
        backtracking(N, 0, used);
        return count;
    }

    private void backtracking(int N, int index, boolean[] used) {
        if (index == N + 1) {
            count++;
            return;
        }
        for (int i = 1; i < N + 1; i++) {
            if (used[i]) {
                continue;
            }
            if (i % index == 0 || index % i == 0) {
                used[i] = true;
                backtracking(N, index + 1, used);
                used[i] = false;
            }
        }
    }

    private void backtracking(int N, HashSet<Integer> track) {
        if (track.size() == N) {
            count++;
            return;
        }
        int index = track.size() + 1;
        for (int i = 1; i < N + 1; i++) {
            if (track.contains(i)) {
                continue;
            }
            if (i % index == 0 || index % i == 0) {
                track.add(i);
                backtracking(N, track);
                track.remove(i);
            }
        }
    }
}
