package com.mengyu.algs4.exercise.huawei;

/**
 * @author yuzhang
 * @date 2020/8/9 8:31 下午
 * TODO
 */
public class Ex8 {
    public static void main(String[] args) {
        String str = "hello world   ";
        System.out.println(solution(str));
    }

    private final static char SPACE = ' ';

    public static int solution(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int pointer = str.length() - 1;
        while (pointer >= 0 && str.charAt(pointer) == SPACE) {
            pointer--;
        }
        int length = 0;
        while (pointer >= 0 && str.charAt(pointer) != SPACE) {
            pointer--;
            length++;
        }
        return length;
    }
}
