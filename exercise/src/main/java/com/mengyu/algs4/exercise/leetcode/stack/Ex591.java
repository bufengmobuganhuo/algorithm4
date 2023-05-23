package com.mengyu.algs4.exercise.leetcode.stack;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2022/5/2 10:04
 * TODO
 */
public class Ex591 {
    public static void main(String[] args) {
        System.out.println(new Ex591().isValid("<TRUE><![CDATA[wahaha]]]><![CDATA[]> wahaha]]></TRUE>"));
    }

    public boolean isValid(String code) {
        if (code.charAt(0) != '<') {
            return false;
        }
        int idx = 0;
        Stack<String> stack = new Stack<>();
        while (idx < code.length()) {
            if (code.startsWith("<![CDATA[", idx)) {
                if (stack.isEmpty()) {
                    return false;
                }
                int i = code.indexOf("]]>", idx);
                if (i == -1) {
                    return false;
                } else {
                    idx = i + 3;
                }
            } else if (code.startsWith("</", idx)) {
                StringBuilder tag = new StringBuilder("<");
                int endIdx = findTag(code, tag, idx + 2);
                if (endIdx == -1) {
                    return false;
                }
                if (stack.isEmpty() || !stack.peek().equals(tag.toString())) {
                    return false;
                } else {
                    stack.pop();
                }
                idx = endIdx + 1;
                if (stack.isEmpty() && idx != code.length()){
                    return false;
                }
            } else if (code.startsWith("<", idx)) {
                StringBuilder tag = new StringBuilder("<");
                int endIdx = findTag(code, tag, idx + 1);
                if (endIdx == -1) {
                    return false;
                }
                stack.push(tag.toString());
                idx = endIdx + 1;
            } else if (!stack.isEmpty()) {
                idx++;
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private int findTag(String sb, StringBuilder tag, int startIdx) {
        while (startIdx < sb.length() && sb.charAt(startIdx) != '>') {
            if (sb.charAt(startIdx) - 'A' < 0 || sb.charAt(startIdx) - 'A' >= 26) {
                return -1;
            }
            tag.append(sb.charAt(startIdx));
            startIdx++;
        }
        if (startIdx == sb.length() && sb.charAt(startIdx - 1) != '>') {
            return -1;
        }
        tag.append(">");
        if (tag.length() > 11 || tag.length() < 3) {
            return -1;
        }
        return startIdx;
    }
}
