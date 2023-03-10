package leetcode.enumration;

/**
 * @author yu zhang
 */
public class Ex1653 {
    /**
     * 情况1: 都是a ---> 此时需要统计原字符串中b的个数
     * 情况2: 都是b ---> 此时需要统计原字符串中a的个数
     * 情况3: 有a有b ---> 此时需要有一个分割线，分割线左边都是a，右边都是b。我们枚举每个位置作为分割线，从而得到需要删除的字符数
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        // 分割线右边的a
        int rightA = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                rightA++;
            }
        }
        // ans = rightA：删除所有的a需要的次数
        int ans = rightA, leftB = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                // 以当前位置分割，右边的a
                rightA--;
            } else {
                // 以当前位置分割，左边的b
                leftB++;
            }
            ans = Math.min(ans, rightA + leftB);
        }
        return ans;
    }
}
