package offer.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import offer.tree.TreeNode;

/**
 * @author yu zhang
 */
public class Ex34 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(new Ex34().pathSum(root, 22));
    }

    private List<List<Integer>> ans;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        backtracking(root, new LinkedList<>(), 0, target);
        return ans;
    }

    private void backtracking(TreeNode node, LinkedList<Integer> track, int sum, int target) {
        track.add(node.val);
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum == target) {
                ans.add(new ArrayList<>(track));
            }
            track.removeLast();
            return;
        }
        if (node.left != null) {
            backtracking(node.left, track, sum, target);
        }
        if (node.right != null) {
            backtracking(node.right, track, sum, target);
        }
        track.removeLast();
    }
}
