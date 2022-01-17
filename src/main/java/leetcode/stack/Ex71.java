package leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex71 {
    public static void main(String[] args) {
        Ex71 ex71 = new Ex71();
        System.out.println(ex71.simplifyPath("/a//b////c/d//././/.."));
    }
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        StringBuilder dir = new StringBuilder();
        for (int i = 1; i < path.length() + 1; i++) {
            if (i == path.length() || path.charAt(i) == '/') {
                if ("..".equals(dir.toString()) && !stack.isEmpty()) {
                    stack.pollLast();
                } else if (!".".equals(dir.toString()) && !"..".equals(dir.toString()) && dir.length() > 0) {
                    stack.offerLast(dir.toString());
                }
                dir.delete(0, dir.length());
                continue;
            }
            dir.append(path.charAt(i));
        }
        StringBuilder res = new StringBuilder("/");
        while (!stack.isEmpty()) {
            res.append(stack.pollFirst());
            res.append("/");
        }
        if (res.length() > 1) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}
