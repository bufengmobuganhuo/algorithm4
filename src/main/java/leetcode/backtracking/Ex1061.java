package leetcode.backtracking;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1061 {
    public static void main(String[] args) {
        int[][] requests = {{0, 0}, {1, 2}, {2, 1}};
        System.out.println(new Ex1061().maximumRequests2(5, requests));
    }

    // 使用mask来枚举每种情况，某一位为1，则说明要允许搬
    public int maximumRequests2(int n, int[][] requests) {
        long[] buildings = new long[(n + 7) / 7];
        int ans = 0, m = requests.length;
        // 有m个请求，则说明有2^m种情况
        for (int mask = 0; mask < (1 << m); mask++) {
            // 统计允许的个数
            int count = Integer.bitCount(mask);
            // 如果允许的个数 < ans，则无法找到最大值，不需要找
            if (count <= ans) {
                continue;
            }
            // 重新初始化选取情况
            Arrays.fill(buildings, 0);
            // 获取在mask的情况下，每个人的选取情况
            for (int i = 0; i < m; i++) {
                // 说明要选
                if ((mask & (1 << i)) != 0) {
                    int from = requests[i][0], to = requests[i][1];
                    int fromIdx = from / 7, toIdx = to / 7;
                    int fromBitIdx = from % 7, toBitIdx = to % 7;
                    long fromTmp = buildings[fromIdx], toTmp = buildings[toIdx];
                    // 允许搬
                    buildings[fromIdx] -= Math.pow(16, fromBitIdx * 2);
                    buildings[toIdx] += Math.pow(16, toBitIdx * 2);
                }
            }
            boolean flag = true;
            // 搬完后，是否能满足条件
            for (long building : buildings) {
                if (building != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans = count;
            }
        }
        return ans;
    }

    private int ans = 0;

    public int maximumRequests(int n, int[][] requests) {
        long[] buildings = new long[(n + 7) / 7];
        backtracking(requests, 0, buildings, 0);
        return ans;
    }

    private void backtracking(int[][] requests, int i, long[] buildings, int count) {
        boolean flag = true;
        for (long building: buildings) {
            if (building != 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            ans = Math.max(ans, count);
        }
        if (Arrays.stream(buildings).allMatch(value -> value == 0)) {
            ans = Math.max(ans, count);
        }
        if (i >= requests.length) {
            return;
        }
        int from = requests[i][0], to = requests[i][1];
        int fromIdx = from / 7, toIdx = to / 7;
        int fromBitIdx = from % 7, toBitIdx = to % 7;
        long fromTmp = buildings[fromIdx], toTmp = buildings[toIdx];
        // 允许搬
        buildings[fromIdx] -= Math.pow(16, fromBitIdx * 2);
        buildings[toIdx] += Math.pow(16, toBitIdx * 2);
        backtracking(requests, i + 1, buildings, count + 1);
        buildings[fromIdx] = fromTmp;
        buildings[toIdx] = toTmp;
        // 不允许搬
        backtracking(requests, i + 1, buildings, count);
    }
}
