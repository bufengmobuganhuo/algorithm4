package com.mengyu.algs4.exercise.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex71_1 {

    public static void main(String[] args) {
        String path = "/home/";
        System.out.println(new Ex71_1().simplifyPath(path));
    }

    public String simplifyPath(String path) {
        Deque<String> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length() + 1; i++) {
            if (i == path.length() || path.charAt(i) == '/') {
                String p = sb.toString();
                if ("..".equals(p)) {
                    deque.pollLast();
                } else if (!".".equals(p) && !"/".equals(p) && sb.length() > 0) {
                    deque.offerLast(p);
                }
                sb = new StringBuilder();
            } else {
                sb.append(path.charAt(i));
            }
        }
        StringBuilder ans = new StringBuilder("/");
        while (!deque.isEmpty()) {
            String p = deque.pollFirst();
            ans.append(p).append("/");
        }
        if (ans.length() > 1) {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}
