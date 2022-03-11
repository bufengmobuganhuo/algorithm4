package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(candidates, ans, new ArrayList<>(), target, 0);
        return ans;
    }

    private boolean backtracking(int[] candidates, List<List<Integer>> ans, List<Integer> track, int remain,
                                 int startIdx) {
        if (remain == 0) {
            ans.add(new ArrayList<>(track));
            return false;
        }else if (remain < 0) {
            return false;
        }
        for (int i = startIdx; i < candidates.length; i++) {
            if (i != startIdx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            track.add(candidates[i]);
            // 因为数组是递增的，所以不能再选了
            if (!backtracking(candidates, ans, track, remain - candidates[i], i + 1)) {
                track.remove(track.size() - 1);
                break;
            }
            track.remove(track.size() - 1);
        }
        return true;
    }
}
