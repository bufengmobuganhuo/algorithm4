package leetcode.rank.year2022.september18;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import leetcode.tree.TreeNode;

/**
 * @author yu zhang
 */
public class Ex3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(13);
        root.right.left = new TreeNode(21);
        root.right.right = new TreeNode(34);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        root.right.left.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
        System.out.println(new Ex3().reverseOddLevels(root));
    }
    public TreeNode reverseOddLevels(TreeNode root) {
        List<TreeNode> deque = new ArrayList<>();
        int layer = 1;
        if (root.left != null) {
            int tmp = root.left.val;
            root.left.val = root.right.val;
            root.right.val = tmp;
            deque.add(root.left);
            deque.add(root.right);
        }
        int startIdx = (int) (Math.pow(2, layer) - 2);
        while (startIdx < deque.size()) {
            int size = deque.size();
            if (layer % 2 == 0) {
                for (int i = startIdx; i < startIdx + (size - startIdx) / 2; i += 1) {
                    TreeNode leftNode = deque.get(i);
                    TreeNode rightNode = deque.get(size - i + startIdx - 1);
                    if (leftNode.left == null) {
                        break;
                    }
                    int tmp = leftNode.left.val;
                    leftNode.left.val = rightNode.right.val;
                    rightNode.right.val = tmp;
                    tmp = leftNode.right.val;
                    leftNode.right.val = rightNode.left.val;
                    rightNode.left.val = tmp;
                }
            }
            for (int i = startIdx; i < size; i++) {
                TreeNode node = deque.get(i);
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
            }
            layer++;
            startIdx = (int) (Math.pow(2, layer) - 2);
        }
        return root;
    }
}
