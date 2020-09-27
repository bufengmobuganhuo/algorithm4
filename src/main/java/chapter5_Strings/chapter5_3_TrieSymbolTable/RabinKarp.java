package chapter5_Strings.chapter5_3_TrieSymbolTable;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/9/25 9:30 上午
 * TODO
 */
public class RabinKarp {
    public static void main(String[] args) {
        RabinKarp rabinKarp = new RabinKarp("abababca");
        String txt = "ababababca";
        System.out.println(rabinKarp.search(txt));
    }
    // 要查找的字符串
    private String pattern;
    // 要查找字符串的长度
    private int M;
    // 要查找字符串的哈希值
    private long patHash;
    // 进制
    private int R;
    // 模
    private long Q;
    // R^(M-1)%Q的值
    private long RM;

    public RabinKarp(String pattern) {
        this.pattern = pattern;
        this.Q = longRandomPrime();
        this.R = 256;
        this.M = pattern.length();
        this.patHash = hash(pattern, M);
        this.RM = 1;
        for (int i = 0; i < M-1; i++) {
            RM = (RM * R) % Q;
        }
    }

    /**
     * 在txt字符串中查找是否包含pattern的子字符串
     *
     * @param txt
     * @return
     */
    public int search(String txt) {
        // txt[0...M-1]的哈希值
        long txtHash = hash(txt, M);
        if (txtHash == patHash && isEqual(txt, 0)) {
            return 0;
        }
        for (int i = M; i < txt.length(); i++) {
            // 计算txt[i...i+M-1]的哈希值
            // 减去第一个数字的值
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;

            int offset = i - M + 1;
            if (txtHash == patHash && isEqual(txt, offset)) {
                return offset;
            }
        }
        return -1;
    }

    /**
     * txt[i...M+i]是否和pattern相同
     *
     * @param txt
     * @param i
     * @return
     */
    private boolean isEqual(String txt, int i) {
        for (int j = 0; j < M; j++) {
            if (txt.charAt(j + i) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 随机生成一个模数
     *
     * @return
     */
    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * 使用Horner方法计算key[0...M-1]的哈希值
     *
     * @param key
     * @param M
     * @return
     */
    private long hash(String key, int M) {
        long hash = 0;
        for (int i = 0; i < M; i++) {
            hash = (R * hash + key.charAt(i)) % Q;
        }
        return hash;
    }
}
