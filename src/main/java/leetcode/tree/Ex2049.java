package leetcode.tree;

import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2049 {
    public static void main(String[] args) {
        int[] parents = {-1,2,0};
        System.out.println(new Ex2049().countHighestScoreNodes(parents));
    }
    private long maxScore;
    private int cnt;
    private int n;
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        int[][] children = new int[n][2];
        for (int i = 1; i < parents.length; i++) {
            int parent = parents[i];
            if (children[parent][0] == 0) {
                children[parent][0] = i;
            } else {
                children[parent][1] = i;
            }
        }
        maxScore = 0;
        dfs(children, 0);
        return cnt;
    }

    private int dfs(int[][] children, int node) {
        if (children[node][0] == 0 && children[node][1] == 0) {
            // 如果最大的没变，就可以直接+1
            if (maxScore == n - 1) {
                cnt++;
            } else if (maxScore < n - 1) {
                maxScore = n - 1;
                cnt = 1;
            }
            return 1;
        }
        long multi = 1;
        int left = 0, right = 0;
        if (children[node][0] != 0) {
            left = dfs(children, children[node][0]);
            multi *= left;
        }
        if (children[node][1] != 0) {
            right = dfs(children, children[node][1]);
            multi *= right;
        }
        if (node != 0) {
            multi *= (n - (left + right + 1));
        }
        if (maxScore == multi) {
            cnt++;
        } else if (maxScore < multi) {
            maxScore = multi;
            cnt = 1;
        }
        return left + right + 1;
    }
}
