package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yuzhang
 * @date 2021/1/25 下午3:32
 * TODO
 */
public class Ex158 {
    public static void main(String[] args) {
        Ex158 ex158 = new Ex158();
        char[] buf = new char[1000];
        System.out.println(ex158.read(buf, 1));
        System.out.println(ex158.read(buf, 2));
        System.out.println(ex158.read(buf, 1));
    }

    private char[] fileBuf = new char[4];   // read4 读到的数据存到这里
    private int readOffset = 0; // 指向 fileBuf 尚未使用的开头索引
    private int bufSize = 0;    // 最近一次从 read4 读到的数据有多少个

    public int read(char[] buf, int n) {
        for (int i = 0; i < n; i++) {
            char nextChar = getNextCharFromFile();
            if (nextChar == 0) {
                return i;
            } else {
                buf[i] = nextChar;
            }
        }
        return n;
    }

    public char getNextCharFromFile() {
        // 比如最近一次 read4 读到的有效数据有 2 个，此时 readOffset == 2，说明 fileBuf 已经没有未使用的数据，需要重新 read4
        if (readOffset == bufSize) {
            bufSize = read4(fileBuf);
            readOffset = 0;
            if (bufSize == 0) {
                return 0;
            }
        }
        // readOffset 永远指向尚未读取的数据的最开头
        return fileBuf[readOffset++];
    }
    public int read4(char[] buf){
        return 9;
    }
}
