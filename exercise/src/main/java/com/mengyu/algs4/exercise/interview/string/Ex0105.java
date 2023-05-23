package com.mengyu.algs4.exercise.interview.string;

/**
 * @author yu zhang
 */
public class Ex0105 {
    public boolean oneEditAway(String first, String second) {
        int fLen = first.length(), sLen = second.length();
        if (fLen - sLen == 1) {
            return oneInsert(new StringBuilder(second), first);
        } else if (sLen - fLen == 1) {
            return oneInsert(new StringBuilder(first), second);
        } else if (sLen == fLen) {
            boolean replaced = false;
            for (int i = 0; i < sLen; i++) {
                if (first.charAt(i) != second.charAt(i) && !replaced) {
                    replaced = true;
                } else if (first.charAt(i) != second.charAt(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean oneInsert(StringBuilder source, String target) {
        boolean inserted = false;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i) && !inserted) {
                source.insert(i, target.charAt(i));
                inserted = true;
            } else if (source.charAt(i) != target.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
