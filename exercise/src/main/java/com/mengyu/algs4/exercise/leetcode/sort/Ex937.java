package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yuzhang
 * @date 2022/5/3 10:42
 * TODO
 */
public class Ex937 {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int spaceIdx1 = o1.indexOf(" ");
                int spaceIdx2 = o2.indexOf(" ");
                boolean isLetter1 = Character.isLetter(o1.charAt(spaceIdx1 + 1));
                boolean isDigit1 = Character.isDigit(o1.charAt(spaceIdx1 + 1));
                boolean isLetter2 = Character.isLetter(o2.charAt(spaceIdx2 + 1));
                boolean isDigit2 = Character.isDigit(o2.charAt(spaceIdx2 + 1));
                if (isLetter1 && isDigit2) {
                    return -1;
                } else if (isDigit1 && isLetter2) {
                    return 1;
                } else if (isLetter1 && isLetter2) {
                    String flag1 = o1.substring(0, spaceIdx1);
                    String flag2 = o2.substring(0, spaceIdx2);
                    String content1 = o1.substring(spaceIdx1 + 1);
                    String content2 = o2.substring(spaceIdx2 + 1);
                    if (content1.equals(content2)) {
                        return flag1.compareTo(flag2);
                    } else {
                        return content1.compareTo(content2);
                    }
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        return logs;
    }
}
