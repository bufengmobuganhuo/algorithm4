package leetcode.string;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/8/20 9:46 上午
 * TODO
 */
public class Ex893 {
    public static void main(String[] args) {
        String[] A = {"a", "b", "c", "a", "c", "c"};
        Ex893 ex893 = new Ex893();
        System.out.println(ex893.numSpecialEquivGroups2(A));
    }

    public int numSpecialEquivGroups2(String[] A) {
        // 用于去重
        Set<String> set = new HashSet<>();
        for (String word : A) {
            // 提取出奇数位字符
            StringBuilder sb1=new StringBuilder();
            for (int i = 0; i < word.length(); i+=2) {
                sb1.append(word.charAt(i));
            }
            char[] chars1=sb1.toString().toCharArray();
            // 对提取出的字符串进行排序
            Arrays.sort(chars1);

            // 提取出偶数位字符
            StringBuilder sb2=new StringBuilder();
            for (int i = 1; i < word.length(); i+=2) {
                sb2.append(word.charAt(i));
            }
            char[] chars2=sb2.toString().toCharArray();
            Arrays.sort(chars2);
            set.add(String.valueOf(chars1)+String.valueOf(chars2));
        }
        return set.size();
    }

    public int numSpecialEquivGroups(String[] A) {
        int[] id = new int[A.length];
        int count = 1;
        for (int i = 0; i < A.length; i++) {
            if (id[i] != 0) {
                continue;
            }
            id[i] = count;
            for (int j = i + 1; j < A.length; j++) {
                if (isEqual(new StringBuilder(A[i]), new StringBuilder(A[j]))) {
                    id[j] = count;
                }
            }
            count++;
        }
        return count - 1;
    }

    private boolean isEqual(StringBuilder str1, StringBuilder str2) {
        if (str1.toString().equals(str2.toString())) {
            return true;
        }
        for (int i = 0; i < str1.length(); i++) {
            char chr1 = str1.charAt(i);
            char chr2 = str2.charAt(i);
            if (chr1 == chr2) {
                continue;
            }
            int chrIdx = 2;
            char tmp = chr1;
            while (i + chrIdx < str1.length() && tmp != chr2) {
                tmp = str1.charAt(i + chrIdx);
                chrIdx += 2;
            }
            if (tmp != chr2) {
                return false;
            }
            chrIdx -= 2;
            str1.replace(i, i + 1, String.valueOf(tmp));
            str1.replace(i + chrIdx, i + chrIdx + 1, String.valueOf(chr1));
            if (str1.toString().equals(str2.toString())) {
                return true;
            }
        }
        return false;
    }
}
