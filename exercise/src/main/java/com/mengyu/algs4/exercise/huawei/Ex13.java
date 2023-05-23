package com.mengyu.algs4.exercise.huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2021/3/27 上午9:36
 * TODO
 */
public class Ex13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            StringBuilder sb = new StringBuilder(str);
            int leftPtr = 0;
            boolean flag = true;
            while (leftPtr < sb.length() - 1) {
                if (leftPtr < 0) {
                    leftPtr = 0;
                }
                if (sb.charAt(leftPtr) >='a' && sb.charAt(leftPtr) <= 'z') {
                    flag=false;
                }else if (sb.charAt(leftPtr) >='A' && sb.charAt(leftPtr) >= 'Z'){
                    flag=false;
                }
                if (leftPtr < sb.length() - 1 && sb.charAt(leftPtr) == sb.charAt(leftPtr + 1)) {
                    sb.deleteCharAt(leftPtr);
                    sb.deleteCharAt(leftPtr);
                    leftPtr--;
                } else {
                    leftPtr++;
                }
            }
            System.out.println(flag ? -1 : sb.length());
        }
    }
}
