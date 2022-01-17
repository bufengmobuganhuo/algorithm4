package leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex913 {
    static final int MOUSE_WIN = 1;
    static final int CAT_WIN = 2;
    static final int DRAW = 0;
    int n;
    int[][] graph;
    /**
     * dp[mouse][cat][turns]：老鼠位于节点mouse，猫位于cat，进行了turns轮之后的状态
     */
    int[][][] dp;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        this.dp = new int[n][n][n * 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getResult(1, 2, 0);
    }

    public int getResult(int mouse, int cat, int turns) {
        // 如果已经进行了2*n轮，则说明肯定有重复路径，则平局
        if (turns == n * 2) {
            return DRAW;
        }
        if (dp[mouse][cat][turns] < 0) {
            // 如果老鼠位于0位置，则老鼠获胜
            if (mouse == 0) {
                dp[mouse][cat][turns] = MOUSE_WIN;
                // 如果猫和老鼠相遇，则猫获胜
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = CAT_WIN;
                // 否则走下一步
            } else {
                getNextResult(mouse, cat, turns);
            }
        }
        return dp[mouse][cat][turns];
    }

    public void getNextResult(int mouse, int cat, int turns) {
        // 如果当前是偶数轮，则是老鼠走，否则猫在走
        int curMove = turns % 2 == 0 ? mouse : cat;
        // 默认结果，如果当前是老鼠走，则猫赢，如果当前是猫走，则老鼠赢
        int defaultResult = curMove == mouse ? CAT_WIN : MOUSE_WIN;
        int result = defaultResult;
        int[] nextNodes = graph[curMove];
        // 遍历所有可以走的下一个节点
        for (int next : nextNodes) {
            // 猫不可以走到洞里
            if (curMove == cat && next == 0) {
                continue;
            }
            // 如果当前是老鼠走，则走下一步
            int nextMouse = curMove == mouse ? next : mouse;
            // 如果当前是猫走，则走下一步，否则保持不动
            int nextCat = curMove == cat ? next : cat;
            int nextResult = getResult(nextMouse, nextCat, turns + 1);
            if (nextResult != defaultResult) {
                result = nextResult;
                // 如果不是平局，则分出了胜负
                if (result != DRAW) {
                    break;
                }
            }
        }
        dp[mouse][cat][turns] = result;
    }
}
