package leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex919 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        Ex919 ex919 = new Ex919(root);
        ex919.insert(2);
        ex919.insert(3);
        ex919.insert(4);
        ex919.insert(5);
        ex919.insert(6);
    }
    private TreeNode root;

    private Deque<TreeNode> que;

    public Ex919(TreeNode root) {
        this.root = root;
        this.que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            TreeNode parent = que.peekFirst();
            if (parent.left != null) {
                que.offerLast(parent.left);
            }
            if (parent.right != null) {
                que.offerLast(parent.right);
            }
            if (parent.left != null && parent.right != null) {
                que.pollFirst();
            } else {
                break;
            }
        }
    }

    public int insert(int val) {
        if (que.isEmpty()) {
            return -1;
        }
        TreeNode parent = que.pollFirst();
        if (parent.left == null) {
            parent.left = new TreeNode(val);
            que.offerFirst(parent);
            que.offerLast(parent.left);
        } else {
            parent.right = new TreeNode(val);
            que.offerLast(parent.right);
        }
        return parent.val;
    }

    public TreeNode get_root() {
        return this.root;
    }
}
