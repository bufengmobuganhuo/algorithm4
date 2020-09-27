package leetcode.string;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/9/24 3:38 下午
 * TODO
 */
public class Ex1044 {
    public static void main(String[] args) {
        Ex1044 ex1044 = new Ex1044();
        String txt = "banana";
        System.out.println(ex1044.longestDupSubstring(txt));
    }
    private long Q = longRandomPrim();
    private int R = 256;
    /**
     * 1. 对于一个字符串，如果存在长度为len的重复子字符串，则一定存在长度为len1<len的子字符串；
     * 反之同理，如果不存在len1<len的子字符串，则一定不存在长度为len的子字符串
     * 2. 所以可以采用二分法来查找最长重复子字符串的长度：
     * 示例：对于一个长度为15的子字符串
     * 1⃣️ 首先left=0,right=14查找len=left+(right-left)/2=7的重复子字符串
     * 2⃣️ 假设上述存在，令left=len+1=8,则继续查找len=left+(right-left)/2=11的重复子字符串
     * 3⃣️ 假设上述不存在，令right=len-1=10,则查找len = left+(right-left)/2=9的重复子字符串
     * 4⃣️ 假设上述存在，令left=len+1=10>right,跳出二分查找
     * 3. 在查找重复子字符串时，可以使用RabinKarp算法
     *
     * @param S
     * @return
     */
    public String longestDupSubstring1(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        int left = 1, right = S.length();
        while (left != right) {
            int midLen = left + (right - left) / 2;
            // 如果存在长度为midLen的重复子字符串，则向更长的长度查找
            if (rabinKarpSearch(S, midLen) != -1) {
                left = midLen + 1;
                // 否则向更小长度找
            } else {
                right = midLen;
            }
        }
        // 再找一次，防止因为不符合条件而跳出循环的情况
        int start = rabinKarpSearch(S, left - 1);
        return start == -1 ? "" : S.substring(start, start + left-1);
    }

    private int rabinKarpSearch(String txt, int M) {
        long RM = 1;
        for (int i = 0; i < M - 1; i++) {
            RM = (RM * R) % Q;
        }
        long txtHash = hash(txt, M);
        // 已经出现的哈希值
        HashMap<Long,String> map = new HashMap<>();
        for (int i = M; i < txt.length(); i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            int offset = i - M + 1;
            String item = txt.substring(offset,offset+M);
            // 如果该值已经出现过，则说明重复，实际上还应该判断连个字符串是否真的一致
            if (map.getOrDefault(txtHash,"").equals(item)) {
                return offset;
            }
            map.put(txtHash,item);
        }
        return -1;
    }

    private long hash(String txt, int M) {
        long hash = 0;
        for (int i = 0; i < M; i++) {
            hash = (hash * R + txt.charAt(i)) % Q;
        }
        return hash;
    }

    private long longRandomPrim() {
        BigInteger bigInteger = BigInteger.probablePrime(31, new Random());
        return bigInteger.longValue();
    }

    /**
     * 使用算法4中的方法，超出内存限制
     *
     * @param S
     * @return
     */
    public String longestDupSubstring(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        // 构建后缀数组
        String[] suffixArr = new String[S.length()];
        for (int i = 0; i < S.length(); i++) {
            suffixArr[i] = S.substring(i);
        }
        // 对后缀数组排序
        quickSort(suffixArr, 0, suffixArr.length - 1);
        // 比较相邻的后缀字符串，找到长度最长的
        String lrs = "";
        for (int i = 1; i < suffixArr.length; i++) {
            int length = lcp(suffixArr, i);
            if (length > lrs.length()) {
                lrs = suffixArr[i].substring(0, length);
            }
        }
        return lrs;
    }

    private int lcp(String[] suffixArr, int index) {
        String suffix1 = suffixArr[index - 1];
        String suffix2 = suffixArr[index];
        int len = Math.min(suffix1.length(), suffix2.length());
        for (int i = 0; i < len; i++) {
            if (suffix1.charAt(i) != suffix2.charAt(i)) {
                return i;
            }
        }
        return len;
    }

    private void quickSort(String[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partitionIdx = partition(arr, lo, hi);
        quickSort(arr, partitionIdx + 1, hi);
        quickSort(arr, lo, partitionIdx - 1);
    }

    private int partition(String[] arr, int lo, int hi) {
        int left = lo, right = hi + 1;
        String partitionKey = arr[lo];
        while (true) {
            while (partitionKey.compareTo(arr[--right]) < 0) {
                if (right == lo) {
                    break;
                }
            }
            while (partitionKey.compareTo(arr[++left]) > 0) {
                if (left == hi) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            exch(arr, left, right);
        }
        exch(arr, lo, right);
        return right;
    }

    private void exch(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
