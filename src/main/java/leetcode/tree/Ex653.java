package leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex653 {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode node, int k, Set<Integer> set) {
        if (node == null) {
            return false;
        }
        if (set.contains(k - node.val)) {
            return true;
        }
        set.add(node.val);
        return dfs(node.left, k, set) || dfs(node.right, k, set);
    }
}
