package leetcode.math;

/**
 * @author yuzhang
 * @date 2021/7/4 上午10:13
 * TODO
 */
public class Ex5802 {
    public static void main(String[] args) {
        Ex5802 ex5802 = new Ex5802();
        System.out.println(ex5802.countGoodNumbers2(50));
    }

    private int mod = (int) (Math.pow(10, 9) + 7);
    private int count = 0;

    /**
     * 1. 上述暴力解法发现一个规律：
     * 1:5
     * 2:20
     * 3:100
     * 4:400
     * 5:2000
     * 可知n=5时，res = 5*4*5*4*5=2000
     * 也就是说，令i从1开始递增到n，当i为奇数时*5，当i为偶数时*4，最终的结果就是5^(1到n中奇数的个数) * 4^(1到n中偶数的个数)
     * 因为偶数个数<=奇数个数，所以还可以化简为:20^(偶数的根数)*(如果n是奇数*5)
     * 2. 使用快速幂加速求幂的过程
     */
    public int countGoodNumbers2(long n) {
        // 偶数的个数
        long evenCount = n / 2;
        int five = n % 2 == 0 ? 1 : 5;
        return (int) ((pow(20, evenCount) * five) % mod);
    }

    private long pow2(long a, long b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return pow2(a * a, b / 2);
        } else {
            return a * pow2(a, b - 1);
        }
    }

    // a^b
    private long pow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long res = 1;
        while (b != 0) {
            // 二进制最后一位是1，表明是奇数
            if ((b & 1) == 1) {
                res *= a;
                res %= mod;
            }
            a *= a;
            a %= mod;
            // 如果是偶数，不停的向右移动一位，到最后肯定是变成了2，再移动一位后，最低位是1
            b = b >> 1;
        }
        return res;
    }

    /**
     * 回溯，枚举每种情况
     */
    private void backtracking(int n, StringBuilder sb) {
        if (sb.length() >= n) {
            int i = 0;
            for (; i < n; i++) {
                int digit = sb.charAt(i) - '0';
                if (i % 2 == 0) {
                    if (digit % 2 != 0) {
                        break;
                    }
                } else if (digit != 2 && digit != 3 && digit != 5 && digit != 7) {
                    break;
                }
            }
            if (i == n - 1) {
                count++;
            }
            return;
        } else {
            for (int i = 0; i < 10; i++) {
                if (sb.length() == 0 && i == 0) {
                    continue;
                }
                backtracking(n, sb.append(i));
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
