package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex29 {
    public static void main(String[] args) {
        Ex29 ex29 = new Ex29();
        ex29.divide(10 ,3);
    }
    /**
     * 1. z <= x/y <= z + 1，转化后(假设y < 0)：zy >= x >= (z + 1)y
     * 2. 考虑到溢出的问题，令x<0, y<0，这样zy是一个负数(最大可以是-2^31)，则只要找一个1<= z <= (2^32-1)
     * 3. 此时可以使用二分查找，找到这样的一个z，使zy >= x
     * 4. 由于不允许使用乘法，则使用快速乘
     */
    public int divide(int dividend, int divisor) {
        // (-2^31) / divisor
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return dividend;
            }
            // 负负得正，会溢出
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // dividend / (-2^31)
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        if (dividend == 0) {
            return 0;
        }
        // 将两个数都转化成负数
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }
        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickMulti(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }

    // 判断z * y >= x
    private boolean quickMulti(int z, int y, int x) {
        int res = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                // 判断res+z < x，为了避免加法的溢出，使用减法
                if (res < x - z) {
                    return false;
                }
                res += z;
            }
            // 这里只是为了判断 z * y >= x，并不需要求出z * y的值
            if (y != 1) {
                // 判断z + z < x
                if (z < x - z) {
                    return false;
                }
                z += z;
            }
            y >>= 1;
        }
        return true;
    }
}
