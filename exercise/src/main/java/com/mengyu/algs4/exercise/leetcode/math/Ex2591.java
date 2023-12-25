package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2591 {
    public int distMoney(int money, int children) {
        // 每人必须分1元，钱不够
        if (money < children) {
            return -1;
        }
        // 先给每个人分1元
        money -= children;
        // 剩下的钱尝试分7元
        int cnt = Math.min(money / 7, children);
        money -= cnt * 7;
        children -= cnt;

        // (所有孩子都分了8元，但是钱没有分完，需要将剩下的钱分给其中1人) || (还剩一个小孩，并且剩余的钱为3，因为不能分4元，所以这里要分给之前获得8元的孩子)
        if ((children == 0 && money > 0) || (children == 1 && money == 3)) {
            cnt--;
        }
        return cnt;
    }
}
