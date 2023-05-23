package com.mengyu.algs4.exercise.bytedance.dec25th;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzhang
 * @date 2020/12/25 上午10:05
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        List<String> dic = new ArrayList<>();
        dic.add("aaa");
        dic.add("aa");
        dic.add("a");
        dic.add("");
        String str = "aaa";
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.findLongestWord2(str, dic));
    }

    /**
     * 不对字典排序，只是对结果做过滤
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord2(String s, List<String> d) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String res = "";
        for (int i = 0; i < d.size(); i++) {
            String dicStr = d.get(i);
            if (dicStr.length() > s.length()) {
                continue;
            }
            if (isSubString(dicStr, s)) {
                if (res.length() < dicStr.length() || (res.length() == dicStr.length() && res.compareTo(dicStr) > 0)) {
                    res = dicStr;
                }
            }
        }
        return res;
    }

    /**
     * 贪心，先将字典按照长度，字典序进行排序，然后用字典字符串一个个匹配s
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0) {
            return s;
        }
        d = d.stream().sorted((o1, o2) -> {
            int lenSub = o2.length() - o1.length();
            if (lenSub != 0) {
                return lenSub;
            }
            return o1.compareTo(o2);
        }).collect(Collectors.toList());
        for (int i = 0; i < d.size(); i++) {
            String dicStr = d.get(i);
            if (dicStr.length() > s.length()) {
                continue;
            }
            if (isSubString(dicStr, s)) {
                return dicStr;
            }
        }
        return "";
    }

    private boolean isSubString(String target, String source) {
        int idx = 0;
        for (int j = 0; j < target.length(); j++) {
            while (idx < source.length() && source.charAt(idx) != target.charAt(j)) {
                idx++;
            }
            if (idx >= source.length()) {
                return false;
            }
            idx++;
        }
        return true;
    }
}
