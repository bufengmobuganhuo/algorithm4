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
        backtracking(N, 1, used);
        return count;
    }

    private void backtracking(int n, int idx, int mask) {
        if (idx > n) {
            count++;
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            int pow = pow(2, i - 1);
            if ((mask & pow) > 0) {
                continue;
            }
            if (i % idx == 0 || idx % i == 0) {
                mask = mask | pow;
                backtracking(n, idx + 1, mask);
                mask = mask ^ pow;
            }
        }
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
            }
            a *= a;
            b >>= 1;
        }
        return res;
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
