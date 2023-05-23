package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.Stack;

/**
 * @author yu zhang
 * 遍历树
 */
public class TreeIterator {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        preorder(root);
        System.out.println("---------------");
        inorder(root);
        System.out.println("---------------");
        postorder(root);
    }

    /**
     * 根据前序遍历访问的顺序，优先访问根结点，然后再分别访问左孩子和右孩子。
     * 对于任一结点，其可看做是根结点，因此可以直接访问，访问完之后，若其左孩子不为空，
     * 按相同规则访问它的左子树；当访问其左子树时，再访问它的右子树
     */
    public static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                System.out.println(root.val);
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop().right;
            }
        }
    }

    /**
     * 根据中序遍历的顺序，对于任一结点，优先访问其左孩子，而左孩子结点又可以看做一根结点，
     * 然后继续访问其左孩子结点，直到遇到左孩子结点为空的结点才进行访问，然后按相同的规则访问其右子树。
     */
    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                System.out.println(stack.peek().val);
                root = stack.pop().right;
            }
        }
    }

    /*
     * 思想：因为在后序遍历中，要保证左孩子和右孩子都已被访问并且左孩子在右孩子前访问才能访问根结点
     * 要保证根结点在左孩子和右孩子访问之后才能访问，因此
     * 1.对于任一结点P，先将其入栈。
     * 2.如果P不存在左孩子和右孩子，则可以直接访问它；
     * 3.或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     * 4.若非上述两种情况，则将P的右孩子和左孩子依次入栈，
     * 这样就保证了每次取栈顶元素的时候，左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
     * */
    public static void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // 标记左右孩子是否访问过
        TreeNode visited = null;
        // 栈顶元素
        TreeNode stackTop = null;
        while (!stack.isEmpty()) {
            stackTop = stack.peek();
            // 左右孩子都为空，可以直接访问
            if ((stackTop.left == null && stackTop.right == null)
                    // 左右
            || visited != null && (stackTop.left == visited || stackTop.right == visited)) {
                System.out.println(stackTop.val);
                visited = stack.pop();
            } else {
                if (stackTop.right != null) {
                    stack.push(stackTop.right);
                }
                if (stackTop.left != null) {
                    stack.push(stackTop.left);
                }
            }
        }
    }

}
