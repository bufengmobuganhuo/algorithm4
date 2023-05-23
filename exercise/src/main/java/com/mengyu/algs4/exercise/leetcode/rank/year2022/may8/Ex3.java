package com.mengyu.algs4.exercise.leetcode.rank.year2022.may8;

/**
 * @author yuzhang
 * @date 2022/5/8 10:44
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        String pressedKeys =
                "88888888888888888888888888888999999999999999999999999999994444444444444444444444444444488888888888888888888888888888555555555555555555555555555556666666666666666666666666666666666666666666666666666666666222222222222222222222222222226666666666666666666666666666699999999999999999999999999999888888888888888888888888888885555555555555555555555555555577777777777777777777777777777444444444444444444444444444444444444444444444444444444444433333333333333333333333333333555555555555555555555555555556666666666666666666666666666644444444444444444444444444444999999999999999999999999999996666666666666666666666666666655555555555555555555555555555444444444444444444444444444448888888888888888888888888888855555555555555555555555555555555555555555555555555555555555555555555555555555555555999999999999999555555555555555555555555555554444444444444444444444444444444555";
        System.out.println(new Ex3().countTexts(pressedKeys));
    }

    private static final int MOD = (int) 1.000000007E9;

    private static final int N = 100010;

    // f(n):长度为n的字符串可能的按键数

    // xxx...xxx,7 + xxx...xxx,77 + xxx...xxx,777 + xxx...xxx,7777
    // f(n)=长度为n-1的可能的按键数 + 长度为n-2的可能的按键数 + 长度为n-3的可能的按键数 + 长度为n-4的可能的按键数
    private static long[] fourDp = new long[N];

    // xxx...xxx,2 + xxx...xxx,22 + xxx...xxx,222
    // f(n)=长度为n-1的可能的按键数 + 长度为n-2的可能的按键数 + 长度为n-3的可能的按键数
    private static long[] threeDp = new long[N];

    static {
        threeDp[0] = 1;
        threeDp[1] = 1;
        threeDp[2] = 2;
        threeDp[3] = 4;
        fourDp[0] = 1;
        fourDp[1] = 1;
        fourDp[2] = 2;
        fourDp[3] = 4;
        for (int i = 4; i < N; i++) {
            threeDp[i] = threeDp[i - 1] + threeDp[i - 2] + threeDp[i - 3];
            threeDp[i] %= MOD;
            fourDp[i] = fourDp[i - 1] + fourDp[i - 2] + fourDp[i - 3] + fourDp[i - 4];
            fourDp[i] %= MOD;
        }
    }

    public int countTexts(String pressedKeys) {
        long ans = 1;
        for (int i = 0; i < pressedKeys.length(); ) {
            int j = i;
            while (j < pressedKeys.length() && pressedKeys.charAt(i) == pressedKeys.charAt(j)) {
                j++;
            }
            boolean isFour = pressedKeys.charAt(i) == '7' || pressedKeys.charAt(i) == '9';
            if (isFour) {
                ans = (ans * fourDp[(j - i)]) % MOD;
            } else {
                ans = (ans  * threeDp[(j - i)]) % MOD;
            }
            i = j;
        }
        return (int) ans;
    }
}
