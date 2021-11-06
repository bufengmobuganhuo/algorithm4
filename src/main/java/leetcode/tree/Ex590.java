package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/11/6 10:21 上午
 * TODO
 */
public class Ex590 {
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<Node> stack = new Stack<>();
        Stack<Integer> output = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            output.push(node.val);
            for (int i = 0; i < node.children.size(); i++) {
                stack.push(node.children.get(i));
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!output.isEmpty()) {
            res.add(output.pop());
        }
        return res;
    }
}
