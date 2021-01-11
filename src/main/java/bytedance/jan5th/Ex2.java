package bytedance.jan5th;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/1/5 上午9:03
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        String S = "loveleetcode";
        Ex2 ex2 = new Ex2();
        System.out.println(Arrays.toString(ex2.shortestToChar(S,'e')));
    }
    public int[] shortestToChar(String S, char C) {
        if (S == null || S.length() == 0) {
            return new int[1];
        }
        int[] res = new int[S.length()];
        Arrays.fill(res, Integer.MAX_VALUE);
        int lastIdx = -1;
        for (int i = 0; i < S.length(); i++) {
            char chr = S.charAt(i);
            if (chr == C) {
                for (int j = i - 1; j > lastIdx; j--) {
                    res[j] = Math.min(i - j, res[j]);
                }
                res[i] = 0;
                lastIdx = i;
            } else if (lastIdx >= 0) {
                res[i] = Math.min(i - lastIdx, res[i]);
            }
        }
        return res;
    }
}
