package leetcode.rank.year2022.july3;

/**
 * @author yuzhang
 * @date 2022/7/3 10:23
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        System.out.println(new Ex3().peopleAwareOfSecret(6, 1, 2));
    }

    private static final int mod = (int) (Math.pow(10, 9) + 7);

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] days = new long[n + 1];
        long[] newDays = new long[n + 1];
        long[] forgetDays = new long[n + 1];
        days[1] = 1;
        if (1 + delay < n + 1) {
            newDays[1 + delay] = 1;
        }
        if (1 + forget < n + 1) {
            forgetDays[1 + forget] = 1;
        }
        for (int i = 1 + delay + 1; i < Math.min(1 + forget, n + 1); i++) {
            newDays[i] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            if (newDays[i] > 0) {
                long count = newDays[i];
                if (i + delay < n + 1) {
                    newDays[i + delay] = (newDays[i + delay] + count) % mod;
                }

                if (i + forget < n + 1) {
                    forgetDays[i + forget] = (forgetDays[i + forget] + count) % mod;
                }
                for (int j = i + delay + 1; j < Math.min(i + forget, n + 1); j++) {
                    newDays[j] = (newDays[j] + count) % mod;
                }
            }
            // 减法后取余数有可能会出现负数，所以需要加mod
            days[i] = (days[i] + days[i - 1] + newDays[i] - forgetDays[i] + mod) % mod;
        }
        return (int) (days[n] % mod);
    }
}
