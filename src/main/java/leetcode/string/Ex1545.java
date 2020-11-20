package leetcode.string;

/**
 * @author yuzhang
 * @date 2020/11/10 11:30 上午
 * TODO
 */
public class Ex1545 {
    public static void main(String[] args) {
        Ex1545 ex1545 = new Ex1545();
        System.out.println(ex1545.findKthBit(4, 11));
    }

    public char findKthBit(int n, int k) {
        if (k == 1){
            return '0';
        }
        int len = (int) (Math.pow(2, n) - 1);
        int mid = len / 2 + 1;
        if (k == mid) {
            return '1';
        } else if (k > mid) {
            k = 2 * mid - k;
            return invert(findKthBit(n-1, k));
        } else {
            return findKthBit(n-1, k);
        }
    }

    private char invert(char chr) {
        return chr == '0' ? '1' : '0';
    }
}
