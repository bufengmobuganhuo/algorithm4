package com.mengyu.algs4.exercise.chapter5_3_triesymboltable;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author yu zhang
 */
public class RabinKarp {
    private String pattern;

    private int patLen;

    private long patHash;

    private int R;

    private long Q;

    private long RM;

    public RabinKarp(String pattern) {
        this.pattern = pattern;
        this.patLen = pattern.length();
        this.Q = longRandomPrime();
        this.R = 256;
        this.patHash = hash(pattern, patLen);
        this.RM = 1;
        for (int i = 0; i < patLen - 1; i++) {
            RM = (RM * R) % Q;
        }
    }

    public int search(String txt) {
        long txtHash = hash(txt, patLen);
        if (patHash == txtHash && isEqual(txt, 0)) {
            return 0;
        }
        for (int i = 0; i < txt.length() - patLen; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i + patLen)) % Q;
            int offset = i + 1;
            if (txtHash == patHash && isEqual(txt, offset)) {
                return offset;
            }
        }
        return -1;
    }

    private boolean isEqual(String txt, int idx) {
        for (int i = idx; i < patLen + idx; i++) {
            if (txt.charAt(i) != pattern.charAt(i - idx)) {
                return false;
            }
        }
        return true;
    }

    private long hash(String str, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (hash * R + str.charAt(i)) % Q;
        }
        return hash;
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
