package com.mengyu.algs4.exercise.leetcode.breadfirst;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex1036 {
    /**
     * 1. 可达问题，可以想到广度优先搜索，从source开始广度搜索，但是肯定会超时
     * 2. 无法到达有两种情况：
     * (1) source被障碍包围住
     * (2) target被障碍包围住
     * 3. 注意到障碍的个数不超过200，说明障碍能形成的包围圈也是有限的，此时可以估计包围圈包含的非障碍物的最大数目：
     *  对于n个障碍，能包围的最大范围（包含最多的非障碍物）是左上角，此时利用两个边界形成一个等腰直角三角形，则最多可以包含n(n-1)/2个非障碍物
     *  （从左上角开始，有1个非障碍物，2个，3个....n-1个）
     * 4.则可以设计如下算法：
     * (1)从source开始bfs，如果在经过了不超过 n(n-1)/2个非障碍位置后搜索结束，说明source在包围圈中，此时有两种情况：
     *  1. 经过了target，则可达
     *  2. 没有经过，不可达
     * (2)上述过程，如果超过了n(n-1)/2个非障碍位置后搜索结束，说明source不在包围圈，但是target还不确定，此时也有两种情况：
     *  1. 经过了target，可达
     *  2. 没经过target，此时无法确定，需要从target搜索
     */

    /**
     * 找到
     */
    private final static int FOUND = 1;

    /**
     * 被包围，找不到
     */
    private final static int BLOCKED = 0;

    /**
     * 没找到，但是不在包围圈中
     */
    private final static int NOT_BLOCKED = -1;

    /**
     * 边界的长度
     */
    private final static int BOUNDARY = 1000000;

    private final static int[][] DIRECTS = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // 如果障碍数没超过2个，则围不成一个圈，肯定可以到达
        if (blocked.length < 2) {
            return true;
        }
        Set<Point> blockedPoints = new HashSet<>();
        for (int[] pos : blocked) {
            blockedPoints.add(new Point(pos[0], pos[1]));
        }
        int result = bfs(source, target, blockedPoints);
        if (result == FOUND) {
            return true;
        } else if (result == BLOCKED) {
            return false;
        } else {
            // 从target开始搜索
            result = bfs(target, source, blockedPoints);
            return result != BLOCKED;
        }
    }

    private int bfs(int[] start, int[] finish, Set<Point> blockedPoints) {
        int sx = start[0], sy = start[1];
        int fx = finish[0], fy = finish[1];
        Queue<Point> que = new ArrayDeque<>();
        Point startPoint = new Point(sx, sy);
        que.offer(startPoint);
        HashSet<Point> visited = new HashSet<>();
        visited.add(startPoint);
        // 非障碍物的个数
        int countDown = blockedPoints.size() * (blockedPoints.size() - 1) / 2;
        while (!que.isEmpty() && countDown > 0) {
            Point point = que.poll();
            for (int i = 0; i < 4; i++) {
                int nextx = point.x + DIRECTS[i][0], nexty = point.y + DIRECTS[i][1];
                Point nextPoint = new Point(nextx, nexty);
                if (nextx >= 0 && nextx < BOUNDARY && nexty >= 0 && nexty < BOUNDARY && !blockedPoints.contains(nextPoint) && !visited.contains(nextPoint)) {
                    if (nextx == fx && nexty == fy) {
                        return FOUND;
                    }
                    countDown--;
                    visited.add(nextPoint);
                    que.offer(nextPoint);
                }
            }
        }
        // bfs结束后，countdown > 0，没超过最大非障碍数，说明有包围圈
        if (countDown > 0) {
            return BLOCKED;
        }
        return NOT_BLOCKED;
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
