package bytedance.dec29th;

/**
 * @author yuzhang
 * @date 2020/12/29 上午8:47
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] b = {1, 0};
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.superPow(2, b));
    }

    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) {
            return a;
        }
        int x = 1;
        int mod = 1337;
        for (int i = 0; i < b.length; i++) {
            x += b[i] * 10;
        }
        int res = 1;
        for (int i = 0; i < x; i++) {
            res *= a % mod;
        }
        return res % mod;
    }
}
