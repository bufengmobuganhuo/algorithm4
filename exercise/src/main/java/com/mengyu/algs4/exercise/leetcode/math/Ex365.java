package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex365 {

    /**
     * 因为操作水壶时都是要么倒满，要么清空，这是一个线性关系，即解方程 ax+by=target
     * 根据贝祖定理，ax+by=z有解当且仅当z是x,y的最大公约数的倍数，因此我们只要找到x,y的最大公约数即可
     * @param x
     * @param y
     * @param target
     * @return
     */
    public boolean canMeasureWater2(int x, int y, int target) {
        if (x + y < target) {
            return false;
        }
        if (x == 0 || y == 0) {
            return target == 0 || x + y == target;
        }
        return target % gcd(x, y) == 0;
    }

    private int gcd(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        } while (b != 0);
        return a;
    }

    /**
     * 方法一：dfs，每次只能有如下几种操作：
     * 1. 把X装满
     * 2. 把Y装满
     * 3. 把X倒给Y，直到X空或者Y满
     * 4. 把Y倒给X，直到Y空或者X满
     * 5. 把X清空
     * 6. 把Y清空
     * 我们每次都枚举这几种操作，直到满足条件。但是因为可能要搜索很多次，递归可能太深了，这里使用栈模拟
     * 同时为了防止无限递归，需要使用一个seen来记录访问过的情况(dfs的marked数组)
     */
    public boolean canMeasureWater(int x, int y, int target) {
        // 全装满也凑不够
        if (x + y < target) {
            return false;
        }
        Set<Long> seen = new HashSet<>();
        // {X剩下的水，Y剩下的水}
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, 0});
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            int[] remains = stack.pop();
            seen.add(hash(remains));
            int xRemain = remains[0], yRemain = remains[1];
            if (remains[0] == target || remains[1] == target || remains[0] + remains[1] == target) {
                return true;
            }
            // 将x装满
            stack.push(new int[]{x, yRemain});
            // 将Y装满
            stack.push(new int[]{xRemain, y});
            // 把X倒给Y，直到X空或者Y满
            stack.push(new int[]{xRemain - Math.min(y - yRemain, xRemain), yRemain + Math.min(y - yRemain, xRemain)});
            // 把Y倒给X，直到Y空或者X满
            stack.push(new int[]{xRemain + Math.min(x - xRemain, yRemain), yRemain - Math.min(x - xRemain, yRemain)});
            // 把X清空
            stack.push(new int[]{0, yRemain});
            // 把Y清空
            stack.push(new int[]{xRemain, 0});
        }
        return false;
    }

    private long hash(int[] remains) {
        return remains[0] * 10001L + remains[1];
    }
}
