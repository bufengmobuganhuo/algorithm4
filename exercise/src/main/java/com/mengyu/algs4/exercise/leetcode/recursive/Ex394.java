package com.mengyu.algs4.exercise.leetcode.recursive;

/**
 * @author yu zhang
 */
public class Ex394 {
    public static void main(String[] args) {
        Ex394 ex394 = new Ex394();
        String s = "3[a2[c]]";
        System.out.println(ex394.decodeString2(s));
    }

    /**
     * 更快
     */
    public String decodeString2(String s) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < s.length() && Character.isLetter(s.charAt(idx))) {
            sb.append(s.charAt(idx++));
        }
        if (idx == s.length()) {
            return sb.toString();
        }
        int startPtr = idx;
        idx = findNum(s, idx);
        int num = Integer.parseInt(s.substring(startPtr, idx));
        int counter = 0;
        int endPtr = 0;
        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                counter++;
            } else if (s.charAt(i) == ']') {
                counter--;
            }
            if (counter == 0) {
                endPtr = i;
                break;
            }
        }
        String str = s.substring(idx + 1, endPtr);
        String res = decodeString2(str);
        for (int i = 0; i < num; i++) {
            sb.append(res);
        }
        sb.append(decodeString2(s.substring(endPtr + 1)));
        return sb.toString();
    }

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < s.length() && Character.isLetter(s.charAt(idx))) {
            sb.append(s.charAt(idx++));
        }
        if (idx == s.length()) {
            return sb.toString();
        }
        int startPtr = idx;
        idx = findNum(s, idx);
        int num = Integer.parseInt(s.substring(startPtr, idx));
        int counter = 0;
        int endPtr = 0;
        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                counter++;
            } else if (s.charAt(i) == ']') {
                counter--;
            }
            if (counter == 0) {
                endPtr = i;
                break;
            }
        }
        String str = s.substring(idx + 1, endPtr);
        for (int i = 0; i < num; i++) {
            sb.append(str);
        }
        if (endPtr < s.length() - 1) {
            sb.append(s.substring(endPtr + 1));
        }
        return decodeString(sb.toString());
    }


    private int findNum(String s, int idx) {
        while (Character.isDigit(s.charAt(idx))) {
            idx++;
        }
        return idx;
    }
}
