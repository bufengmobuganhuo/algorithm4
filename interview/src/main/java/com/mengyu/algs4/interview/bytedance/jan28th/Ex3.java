package com.mengyu.algs4.interview.bytedance.jan28th;

/**
 * @author yuzhang
 * @date 2021/1/28 上午10:27
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.numberToWords(20));
    }

    private static final String HUNDRED = "Hundred";
    private static final String[] SINGLE_DIGIT = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEN_DIGIT = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS_DIGIT_LARGER_THAN_2 = {"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder res = new StringBuilder();
        int billion = num / 1000000000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;
        if (billion != 0) {
            res.append(three(billion)).append(" Billion");
        }
        if (million != 0) {
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(three(million)).append(" Million");
        }
        if (thousand != 0) {
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(three(thousand)).append(" Thousand");
        }
        if (rest != 0) {
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(three(rest));
        }
        return res.toString();
    }

    private String three(int num) {
        int hundred = num / 100;
        int rest = num % 100;
        if (hundred * rest > 0) {
            return SINGLE_DIGIT[hundred] + " " + HUNDRED + " " + two(rest);
        } else if (hundred == 0 && rest != 0) {
            return two(rest);
        } else if (hundred != 0 && rest == 0) {
            return SINGLE_DIGIT[hundred] + " " + HUNDRED;
        }
        return "";
    }

    private String two(int num) {
        if (num == 0) {
            return "";
        }
        if (num < 10) {
            return SINGLE_DIGIT[num];
        }
        if (num < 20) {
            return TEN_DIGIT[num - 10];
        }
        if (num % 10 == 0) {
            return TENS_DIGIT_LARGER_THAN_2[num / 10];
        }
        return TENS_DIGIT_LARGER_THAN_2[num / 10] + " " + SINGLE_DIGIT[num % 10];
    }
}
