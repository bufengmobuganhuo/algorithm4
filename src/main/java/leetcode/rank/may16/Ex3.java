package leetcode.rank.may16;

/**
 * @author yuzhang
 * @date 2021/5/15 下午10:52
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        char[][] box= {
                {'#','.','*','.'},
                {'#','#','*','.'}
        };
        ex3.rotateTheBox(box);
    }
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (box[i][j] == '#') {
                    box[i][j] = '.';
                    count++;
                } else if (box[i][j] == '*') {
                    for (int k = 0; k < count; k++) {
                        box[i][j - k - 1] = '#';
                    }
                    count = 0;
                }
            }
            while (count != 0) {
                box[i][n - count] = '#';
                count--;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = box[m - j - 1][i];
            }
        }
        return res;
    }
}
