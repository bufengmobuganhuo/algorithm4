package leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/12/18 上午8:59
 * TODO
 */
public class Ex342 {
    public static void main(String[] args) {
        Ex342 ex342 = new Ex342();
        System.out.println(ex342.isPowerOfFour2(-32));
    }

    /**
     * 1. 如果不是2的幂，则一定不是4的幂：
     * 如果 x > 0 && x & (x-1) ==0，说明是2的幂
     * 2. 如果是2的幂的话，有两种情况
     * 1⃣️ 2^2k = 4^k = (3+1)^k ---> (3+1)^k%3 =1
     * 2⃣️ 2^(2k+1) = 2*4^k = 2*(3+1)^k ---> 2*(3+1)^k%3=2
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour2(int n) {
        return (n > 0 && ((n & (n - 1)) == 0)) && n % 3 == 1;
    }

    private Power power = new Power();

    /**
     * 1. 预运算：
     * 1⃣️ 对于n < 0 的情况，直接false
     * 2⃣️ 因为n的范围是2^31-1，则log4(2^31-1)=15，则最多有15个数符合条件，可以先把这五个数算出来
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour1(int n) {
        if (n <= 0) {
            return false;
        }
        return power.set.contains(n);
    }

    static class Power {
        int count = 15;
        Set<Integer> set;

        public Power() {
            set = new HashSet<>();
            int num = 1;
            for (int i = 0; i <= count; i++) {
                set.add(num);
                num *= 4;
            }
        }
    }
}
