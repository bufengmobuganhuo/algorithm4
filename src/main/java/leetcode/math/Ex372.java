package leetcode.math;

/**
 * @author yuzhang
 * @date 2020/12/29 上午8:47
 * TODO
 */
public class Ex372 {
    public static void main(String[] args) {
        int[] b = {2, 0, 0};
        Ex372 ex372 = new Ex372();
        System.out.println(ex372.superPow(2147483647, b));
    }

    private final static int mod = 1337;

    /**
     * 1. 对于a^{1,5,6,4}，可以有如下规律:
     *      a^{1,5,6,4}
     *    = a^4 * a^{1,5,6,0}
     *    = a^4 * (a^{1,5,6})^10
     *      求幂时，可以采用高效求幂算法《算法第四版--第一章》
     * 2. 对于取模运算，还有如下规律：
     *      (a*b)%c = (a%c)(b%c)%c
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length - 1);
    }

    private int superPow(int a, int[] b, int index) {
        if (index < 0) {
            return 1;
        }
        int part1 = pow(a, b[index]);
        int part2 = pow(superPow(a, b, index - 1), 10);
        return (part1 * part2) % mod;
    }

    /**
     * 高效求幂方法
     * @param a
     * @param b
     * @return
     */
    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        // 因为要取模，所以此处需要%mod
        a %= mod;
        if (b % 2 == 0) {
            int sub = pow(a, b / 2);
            return (sub * sub) % mod;
        } else {
            // 取模
            return (a * pow(a, b - 1)) % mod;
        }
    }
}
