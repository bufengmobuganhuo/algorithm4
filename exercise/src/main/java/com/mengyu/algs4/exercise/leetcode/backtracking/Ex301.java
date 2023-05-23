package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex301 {
    public static void main(String[] args) {
        Ex301 ex301 = new Ex301();
        System.out.println(ex301.removeInvalidParentheses("()())()"));
    }

    private char[] chrArr;

    private Set<String> validParentheses;

    /**
     * 一、从左到右遍历，维护两个变量leftNeedRemove, rightNeedRemove，分别代表需要移除的左右括号数：
     * 1. 如果遇到了'('，leftNeedRemove+1
     * 2. 如果遇到了')':
     * 1. 如果leftNeedRemove>0，则令leftNeedRemove-1
     * 2. 如果leftNeedRemove=0，则令rightNeedRemove+1
     * 二、得到上述两个值以后，可以使用回溯算法来枚举每种每种情况，并使用哈希去重
     */
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        chrArr = s.toCharArray();
        validParentheses = new HashSet<>();
        int leftNeedRemove = 0, rightNeedRemove = 0;
        for (char chr : chrArr) {
            if (chr == '(') {
                leftNeedRemove++;
            } else if (chr == ')') {
                // 说明可以和右括号组成一个完整括号
                if (leftNeedRemove > 0) {
                    leftNeedRemove--;
                    // 没有左括号可以和它组成完整括号
                } else if (leftNeedRemove == 0) {
                    rightNeedRemove++;
                }
            }
        }
        backtracking(0, 0, 0, leftNeedRemove, rightNeedRemove, new StringBuilder());
        return new ArrayList<>(validParentheses);
    }

    /**
     * @param idx             遍历到的为止
     * @param leftCount       遍历过程中sb有几个左括号
     * @param rightCount      遍历过程中sb有几个右括号
     * @param leftNeedRemove  需要移除的左括号
     * @param rightNeedRemove 需要移除的右括号
     * @param sb
     */
    private void backtracking(int idx, int leftCount, int rightCount, int leftNeedRemove, int rightNeedRemove,
                              StringBuilder sb) {
        if (idx == chrArr.length) {
            if (leftNeedRemove == 0 && rightNeedRemove == 0) {
                validParentheses.add(sb.toString());
            }
            return;
        }
        char chr = chrArr[idx];
        if (chr == '(' && leftNeedRemove > 0) {
            // 移除当前的字符
            backtracking(idx + 1, leftCount, rightCount, leftNeedRemove - 1, rightNeedRemove, sb);
        }
        if (chr == ')' && rightNeedRemove > 0) {
            // 移除当前的字符
            backtracking(idx + 1, leftCount, rightCount, leftNeedRemove, rightNeedRemove - 1, sb);
        }
        // 枚举完移除当前字符的情况后，枚举不移除的情况
        sb.append(chr);
        if (chr == '(') {
            backtracking(idx + 1, leftCount + 1, rightCount, leftNeedRemove, rightNeedRemove, sb);
            // sb中，只有左括号的个数>右括号的个数时才能要当前的右括号，否则不是完整的括号
        } else if (chr == ')' && leftCount > rightCount) {
            backtracking(idx + 1, leftCount, rightCount + 1, leftNeedRemove, rightNeedRemove, sb);
            // 如果是字母，直接保留
        } else if (chr != ')') {
            backtracking(idx + 1, leftCount, rightCount, leftNeedRemove, rightNeedRemove, sb);
        }
        // 回溯固定套路
        sb.deleteCharAt(sb.length() - 1);
    }

}
