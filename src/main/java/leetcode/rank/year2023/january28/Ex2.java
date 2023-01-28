package leetcode.rank.year2023.january28;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2 {
    public static void main(String[] args) {
        int[][] score = {
                {10,6,9,1},{7,5,11,2},{4,8,3,15}
        };
        System.out.println(Arrays.deepToString(new Ex2().sortTheStudents(score, 2)));
    }
    public int[][] sortTheStudents(int[][] score, int k) {
        sort(score, 0, score.length - 1, k);
        return score;
    }

    private void sort(int[][] score, int startPtr, int endPtr, int k) {
        if (startPtr >= endPtr) {
            return;
        }
        int partitionIdx = partition(score, startPtr, endPtr, k);
        sort(score, startPtr, partitionIdx - 1, k);
        sort(score, partitionIdx + 1, endPtr, k);
    }

    private int partition(int[][] score, int startPtr, int endPtr, int k) {
        int ele = score[startPtr][k], leftPtr = startPtr, rightPtr = endPtr + 1;
        while (true) {
            while (ele > score[--rightPtr][k]) {
                if (rightPtr <= startPtr) {
                    break;
                }
            }
            while (ele < score[++leftPtr][k]) {
                if (leftPtr >= endPtr) {
                    break;
                }
            }
            if (rightPtr <= leftPtr) {
                break;
            }
            exch(score, leftPtr, rightPtr, k);
        }
        exch(score, startPtr, rightPtr, k);
        return rightPtr;
    }

    private void exch(int[][] score, int i, int j, int k) {
        for (int l = 0; l < score[i].length; l++) {
            int tmp = score[i][l];
            score[i][l] = score[j][l];
            score[j][l] = tmp;
        }
    }
}
