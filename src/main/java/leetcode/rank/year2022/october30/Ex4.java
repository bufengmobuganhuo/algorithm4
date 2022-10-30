package leetcode.rank.year2022.october30;

import leetcode.tree.TreeNode;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2022/10/30 11:22
 * https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries/solution/liang-bian-dfspythonjavacgo-by-endlessch-vvs4/
 */
public class Ex4 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        int[] queries = {3};
        System.out.println(Arrays.toString(new Ex4().treeQueries(root, queries)));
    }

    private int[] height = new int[100010]; // 每棵子树的高度
    private int[] res = new int[100010]; // 每个节点的答案

    public int[] treeQueries(TreeNode root, int[] queries) {
        getHeight(root);
        dfs(root, -1, 0);
        for (int i = 0; i < queries.length; i++) {
            queries[i] = res[queries[i]];
        }
        return queries;
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int h = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        height[node.val] = h;
        return h;
    }

    /**
     *
     * @param node
     * @param depth 从根节点到当前节点的深度，根节点为-1
     * @param restH 删除当前节点后，树的高度
     */
    private void dfs(TreeNode node, int depth, int restH) {
        if (node == null) {
            return;
        }
        ++depth;
        res[node.val] = restH;
        /**
         * 删除当前节点的左子结点后，树的高度 = max{(当前节点深度 + 当前节点右子树的高度)，(当前节点被删除后，当前树的高度)}
         */
        dfs(node.left, depth, Math.max(depth + (node.right == null ? 0 : height[node.right.val]), restH));
        dfs(node.right, depth, Math.max(depth + (node.left == null ? 0 : height[node.left.val]), restH));
    }
}
