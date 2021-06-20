package leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex18888 {
    /**
     * 1. 经过变换后，只能有两种情况："0,1,0,1...0,1"(pattern1)或"1,0,1,0...1,0"(pattern2)
     * 2. 暴力解法：拼串str = s + s表示经过类型1变换后的各种情况，随后将移位后的各种情况与上述pattern比较，不同的字符个数的最小值就是类型2的操作个数
     * 3. 对于偶数个字符的s：
     * 最终的结果只可能是"0,1,0,1...0,1"或"1,0,1,0...1,0"，这种是不需要移位的(移位并不能让类型2的操作减少)，此时可以采用leetcode1758的方式计算
     * 2. 对于奇数个字符的s：
     * 最终的结果只可能是"0,1,0,1...0,1,0"或"1,0,1,0...1,0,1"，
     * 对于"0,1,0,1,0｜1,0"，他可以从"1,0｜0,1,0,1,0"(source1)通过类型1的移位操作变换过来，这样可以减少类型2操作次数
     * 对于"1,0,1,0,1｜0,1"，他可以从"0,1｜1,0,1,0,1"(source2)通过类型2的移位操作变换过来，这样可以减少类型2操作次数
     * 3. 也就是说当把原字符串经过类型2的变换后变成source1或source2时就可以通过移位操作实现结果
     * 4. count1[i]：把字符串的（0～i）范围变为pattern1模式时类型2的操作次数，count2[i]：把字符串的（0～i）范围变为pattern2模式时类型2的操作次数
     * 那么把字符串的(i+1~j)变为pattern1需要的类型2操作数=count1[j]-count1[i]（前缀和）
     * 则如果要变成source1（从第i位开始移位，他的两边都是偶数个字符）: count2[i] + (count1[s.length-1] - count1[i])
     * 如果要变成source2（从第i位开始移位，他的两边都是偶数个字符）: count1[i] + (count2[s.length-1] - count2[i])
     * 二者最小值即为结果
     */
    public int minFlips(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        // 0，1，0，1模式
        int[] count1 = new int[len];
        // 1，0，1，0模式
        int[] count2 = new int[len];
        // 计算类型2的操作个数
        for (int i = 0; i < len; i++) {
            count1[i] = (i > 0 ? count1[i - 1] : 0) + (s.charAt(i) - '0' != i % 2 ? 1 : 0);
            count2[i] = (i > 0 ? count2[i - 1] : 0) + (s.charAt(i) - '0' == i % 2 ? 1 : 0);
        }
        int res = Math.min(count1[len - 1], count2[len - 1]);
        // 判断s字符个数为奇数的情况
        if (len % 2 != 0) {
            for (int i = 0; i < len; i++) {
                res = Math.min(count1[i] + count2[len - 1] - count2[i], res);
                res = Math.min(count1[len - 1] - count1[i] + count2[i], res);
            }
        }
        return res;
    }
}
