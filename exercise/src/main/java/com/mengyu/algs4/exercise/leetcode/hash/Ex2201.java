package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex2201 {

    public static void main(String[] args) {
        int n = 2;
        int[][] artifacts = {{0, 0, 0, 0}, {0, 1, 1, 1}}, dig = {{0, 0}, {0, 1}};
        System.out.println(new Ex2201().digArtifacts(n, artifacts, dig));
    }

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[] map = new boolean[n * n];
        for (int[] digPos : dig) {
            int id = digPos[0] * n + digPos[1];
            map[id] = true;
        }
        int cnt = 0;
        for (int[] artifact : artifacts) {
            boolean founded = true;
            int leftUpX = artifact[0], leftUpY = artifact[1];
            int rightDownX = artifact[2], rightDownY = artifact[3];
            for (int x = leftUpX; x <= rightDownX; x += 1) {
                for (int y = leftUpY; y <= rightDownY; y += 1) {
                    if (!map[x * n + y]) {
                        founded = false;
                        break;
                    }
                }
            }
            if (founded) {
                cnt++;
            }
        }
        return cnt;
    }
}
