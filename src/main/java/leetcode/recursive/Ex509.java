package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2020/12/8 下午2:33
 * TODO
 */
public class Ex509 {
    public static void main(String[] args) {
        Ex509 ex509 = new Ex509();
        System.out.println(ex509.fib(4));
    }
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        return fib(N - 1) + fib(N - 2);
    }
}
