package leetcode.prefix_sum;

/**
 * @author yuzhang
 * @date 2021/6/7 上午8:32
 * TODO
 */
public class Ex1758 {
    /**
     * 1. 要么变成0，1，0，1...，要么变成1，0，1，0...
     * 2. 上述两个字符串有一个性质：第一种：偶数位的值=0，即s[i] = i % 2; 第二种：偶数位的值=1，即s[i] != i %2
     * 3. 比较变成两种情况的的转换次数即可(不符合上述规律的就是需要转换)
     */
    public int minOperations(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        // 第一种和第二种的转换次数
        int count0 = 0, count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - '0' == i % 2){
                count1++;
            }else {
                count0++;
            }
        }
        return Math.min(count0, count1);
    }
}
