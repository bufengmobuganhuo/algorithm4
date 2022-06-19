package leetcode.rank.year2022.june19;

/**
 * @author yuzhang
 * @date 2022/6/19 11:39
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        String res = Integer.toBinaryString(22);
        System.out.println(Integer.valueOf("0001", 2));
        System.out.println(new Ex3().longestSubsequence("001010101011010100010101101010010", 93951055));
    }

    /**
     * 1. 贪心，统计每个位置的前导零有多少个，如果某个范围[leftPtr...rightPtr]的数<=k，则追加前导零
     * 2. 找上述范围可以使用滑动窗口
     * @param s
     * @param k
     * @return
     */
    public int longestSubsequence(String s, int k) {
        int len = s.length();
        // s[i]前面有多少个0
        int[] left = new int[len];
        char[] chrs = s.toCharArray();
        for (int i = 0, cnt = 0; i < len - 1; i++) {
            if (chrs[i] == '0') {
                cnt++;
            }
            left[i + 1] = cnt;
        }
        int ans = Integer.MIN_VALUE;
        int leftPtr = 0, rightPtr = 0;
        int curNum = 0;
        while (rightPtr < len) {
            // 滑动窗口内的当前值
            curNum = (curNum << 1) + chrs[rightPtr] - '0';
            while (curNum > k) {
                if (chrs[leftPtr] != '0') {
                    curNum -= (1 << (rightPtr - leftPtr));
                }
                leftPtr++;
            }
            ans = Math.max(ans, left[leftPtr] + rightPtr - leftPtr + 1);
            rightPtr++;
        }
        return ans;
    }
}
