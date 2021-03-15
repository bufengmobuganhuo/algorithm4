package leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/5/11 上午8:20
 * TODO
 */
public class Ex36 {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer> rowMap[] = new HashMap[9];
        Map<Integer, Integer> colMap[] = new HashMap[9];
        Map<Integer, Integer> boxMap[] = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rowMap[i] = new HashMap<>();
            colMap[i] = new HashMap<>();
            boxMap[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = board[i][j];
                if (num != '.') {
                    int boxIdx = (i / 3) * 3 + j / 3;
                    if (rowMap[i].getOrDefault(num, 0) > 0) {
                        return false;
                    }
                    if (colMap[j].getOrDefault(num, 0) > 0) {
                        return false;
                    }
                    if (boxMap[boxIdx].getOrDefault(num, 0) > 0) {
                        return false;
                    }
                    rowMap[i].put(num, 1);
                    colMap[j].put(num, 1);
                    boxMap[boxIdx].put(num, 1);
                }
            }
        }
        return true;
    }
}
