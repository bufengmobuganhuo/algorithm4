package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/2/5 下午5:40
 * TODO
 */
public class Ex77_1 {
    public static void main(String[] args) {
        Ex77_1 ex77_1 = new Ex77_1();
        ex77_1.combine(4, 2);
    }

    private List<List<Integer>> ans;

    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        backtracking(1, n, k, new LinkedList<>());
        return ans;
    }

    private void backtracking(int startIdx, int n, int k, LinkedList<Integer> track) {
        if (track.size() == k) {
            List<Integer> list = new ArrayList<>(track);
            ans.add(list);
            return;
        }
        for (int i = startIdx; i < n + 1; i++) {
            track.offerLast(i);
            backtracking(startIdx + 1, n, k, track);
            track.removeLast();
        }
    }
}
