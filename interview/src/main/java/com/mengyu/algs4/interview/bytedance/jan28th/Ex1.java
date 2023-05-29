package com.mengyu.algs4.interview.bytedance.jan28th;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/28 上午10:12
 * TODO
 */
public class Ex1 {
    private static final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private Map<String, String> map;

    public Ex1() {
        map = new HashMap<>();
        for (int i = 0; i < MONTH.length; i++) {
            map.put(MONTH[i], i < 10 ? "0" + i : String.valueOf(i));
        }
    }

    public String reformatDate(String date) {
        if (date == null || date.length() == 0) {
            return "";
        }
        String[] dateArr = date.split(" ");
        String day = dateArr[0].substring(0, dateArr[0].length() - 1);
        day = day.length() < 2 ? "0" + day : day;
        String month = map.get(dateArr[1]);
        return dateArr[2] + "-" + month + "-" + day;
    }
}
