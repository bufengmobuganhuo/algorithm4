package leetcode.heap;

/**
 * @author yuzhang
 * @date 2021/3/15 上午10:11
 * TODO
 */
public class Ex263 {
    public boolean isUgly(int n) {
        if(n == 0){
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
