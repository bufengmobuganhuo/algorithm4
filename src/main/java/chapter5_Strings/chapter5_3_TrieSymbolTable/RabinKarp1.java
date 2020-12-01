package chapter5_Strings.chapter5_3_TrieSymbolTable;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/11/30 上午10:19
 * TODO
 */
public class RabinKarp1 {
    private final String pattern;
    private final int patLen;
    private final long patHash;
    private final int R;
    private final long Q;
    private long RM;

    public RabinKarp1(String pattern) {
        R = 256;
        this.patLen = pattern.length();
        this.pattern = pattern;
        this.patHash = hash(pattern, 0);
        this.Q = longRandomPrime();
        this.RM = 1;
        for (int i = 0; i < patLen - 1; i++) {
            RM = (RM * R) % Q;
        }
    }

    public int search(String txt) {
        long txtHash = hash(txt, 0);
        if (txtHash == patHash && isEqual(txt, 0)) {
            return 0;
        }
        for (int i = patLen; i < txt.length(); i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - patLen) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            int offset = i - patLen + 1;
            if (txtHash == patHash && isEqual(txt, offset)) {
                return offset;
            }
        }
        return -1;
    }

    private boolean isEqual(String txt, int i) {
        for (int j = 0; j < patLen; j++) {
            if (txt.charAt(i + j) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private long hash(String key, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (R * hash + key.charAt(i)) % Q;
        }
        return hash;
    }
}
