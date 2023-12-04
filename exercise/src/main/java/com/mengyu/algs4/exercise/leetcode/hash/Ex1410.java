package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1410 {

    public static void main(String[] args) {
        System.out.println(new Ex1410().entityParser("and I quote: &quot;...&quot;"));
    }

    Map<String, String> map = new HashMap<String, String> () { {
        put("&quot;", "\"");
        put("&apos;", "'");
        put("&amp;", "&");
        put("&gt;", ">");
        put("&lt;", "<");
        put("&frasl;", "/");
    }};

    public String entityParser(String text) {
        int idx = -1;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            sb.append(chr);
            if (chr == '&') {
                idx = i;
            } else if (idx != -1 && chr == ';') {
                int len = i - idx + 1;
                if (len == 6 || len == 5 || len == 4 || len == 7) {
                    String s = text.substring(idx, i + 1);
                    if (map.containsKey(s)) {
                        String x = map.get(s);
                        sb.replace(sb.length() - len, sb.length(), x);
                    }
                }
                idx = -1;
            }
        }
        return sb.toString();
    }
}
