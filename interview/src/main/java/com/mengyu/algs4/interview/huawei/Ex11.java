package com.mengyu.algs4.interview.huawei;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/3/21 上午10:29
 * TODO
 */
public class Ex11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Character> set1;
        Set<Character> set2;
        while (scanner.hasNext()){
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            set1 = new HashSet<>();
            set2 = new HashSet<>();
            for (char chr : str1.toCharArray()) {
                set1.add(chr);
            }
            for (char chr : str2.toCharArray()) {
                if (set1.contains(chr)){
                    set2.add(chr);
                }
            }
            Character[] chrs = new Character[set2.size()];
            set2.toArray(chrs);
            Arrays.sort(chrs);
            for (Character chr : chrs) {
                System.out.print(chr);
            }
        }
    }
}
