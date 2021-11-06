package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/11/6 10:23 上午
 * TODO
 */
public class Ex589 {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i > -1; i--) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }
}
