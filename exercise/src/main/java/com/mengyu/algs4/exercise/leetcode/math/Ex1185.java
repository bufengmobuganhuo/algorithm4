package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2022/1/3 10:57 上午
 * TODO
 */
public class Ex1185 {

    public static void main(String[] args) {
        Ex1185 ex1185 = new Ex1185();
        System.out.println(ex1185.dayOfTheWeek(29, 2, 2000));
        System.out.println(ex1185.dayOfTheWeek2(29, 2, 2000));
    }

    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;
        // 当前年之前的年贡献的天数,因为是4年一闰年，所以这里-1969，比如1972-1969=3，3/4 = 0，则不会多加一天
        // 但是对于1976-1969=7，则会多加1972年多出来的那一天
        days = 365 * (year - 1971) + (year - 1969) / 4;
        // 当前月贡献出来的天数
        for (int i = 0; i < month - 1; i++) {
            days += monthDays[i];
        }
        // 如果当年是闰年(并且当前月超过2月)，则需要+一天
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) {
            days += 1;
        }
        // 当前月份贡献的天数
        days += day;
        System.out.println(days);
        // 1970.12.31是周四，如果Sunday算是开始（索引为0），则需要给他补上4天，取余就是结果
        return week[(days + 4) % 7];
    }

    public String dayOfTheWeek2(int day, int month, int year) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        /* 输入年份之前的年份的天数贡献 */
        int days = 365 * (year - 1971) + (year - 1969) / 4;
        /* 输入年份中，输入月份之前的月份的天数贡献 */
        for (int i = 0; i < month - 1; ++i) {
            days += monthDays[i];
        }
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) {
            days += 1;
        }
        /* 输入月份中的天数贡献 */
        days += day;
        System.out.println(days);
        return week[(days + 3) % 7];
    }
}
