package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/11/8 1:06 下午
 * TODO
 */
public class Ex893_1 {
    public static void main(String[] args) {
        String[] A = {
                "abc", "acb", "bac", "bca", "cab", "cba"
        };
        Ex893_1 ex893_1 = new Ex893_1();
        System.out.println(ex893_1.numSpecialEquivGroups(A));
    }

    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        int strLen = A[0].length();
        for (String s : A) {
            // 奇数
            char[] chrs1 = new char[strLen / 2];
            // 偶数
            char[] chrs2 = new char[strLen % 2 == 0 ? strLen / 2 : strLen / 2 + 1];
            for (int j = 0, i = 1; j < strLen || i < strLen; j += 2, i += 2) {
                // 模仿插入排序分别对奇数和偶数的字符进行排序
                int idx = chrs2.length - 1;
                chrs2[idx] = s.charAt(j);
                while (idx > 0 && chrs2[idx - 1] <= chrs2[idx]) {
                    exch(chrs2, idx, idx - 1);
                    idx--;
                }
                if (i >= strLen) {
                    continue;
                }
                idx = chrs1.length - 1;
                chrs1[idx] = s.charAt(i);
                while (idx > 0 && chrs1[idx - 1] <= chrs1[idx]) {
                    exch(chrs1, idx, idx - 1);
                    idx--;
                }
            }
            set.add(String.valueOf(chrs1) + String.valueOf(chrs2));
        }
        return set.size();
    }

    private void exch(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

}
