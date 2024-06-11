package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex331 {
    /**
     * 使用一个槽位数来做。初始有一个槽位，当遇到一个空节点，则消耗1个槽位；当遇到一个非空节点，则消耗一个槽位，并且增加2个槽位
     * 当最后槽位数=0时，就说明是一个合法的前序序列
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length(), slots = 1;
        int idx = 0;
        while (idx < n) {
            if (slots <= 0) {
                return false;
            }
            if (preorder.charAt(idx) == '#') {
                slots--;
                idx++;
            } else if (preorder.charAt(idx) == ',') {
                idx++;
            } else {
                // 把整个数字遍历完
                while (idx < n && preorder.charAt(idx) != ',') {
                    idx++;
                }
                // slots = slots - 1 + 2
                slots++;
            }
        }
        return slots == 0;
    }
}
