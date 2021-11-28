package leetcode.tree;

/**
 * @author yuzhang
 * @date 2021/11/28 9:49 上午
 * TODO
 */
public class Ex865 {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).root;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            // 走到了最深节点的下一层，那么距离=0
            return new Result(null, 0);
        }
        Result left = dfs(node.left);
        Result right = dfs(node.right);
        if (left.dist > right.dist) {
            return new Result(left.root, left.dist + 1);
        }
        if (left.dist < right.dist) {
            return new Result(right.root, right.dist + 1);
        }
        return new Result(node, left.dist + 1);
    }

    private static class Result {
        TreeNode root;
        int dist;

        public Result(TreeNode root, int dist) {
            this.root = root;
            this.dist = dist;
        }
    }
}
