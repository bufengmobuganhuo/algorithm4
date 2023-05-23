package leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex1849 {
    public static void main(String[] args) {
        System.out.println(new Ex1849().splitString("20000000000000000001"));
    }

    // 确定了第一个数字后，后面的数字就是依次-1
    public boolean splitString(String s) {
        int i = 0;
        // 去除前导0，因为后面的不会出现负数
        while (i < s.length() - 1 && s.charAt(i) == '0') {
            i++;
        }
        if (i >= s.length() - 1) {
            return false;
        }
        // 枚举第一个数
        StringBuilder sb = new StringBuilder();
        // 不能超过long的最大位数
        for (int j = i; j < Math.min(i + 18, s.length()); j++) {
            sb.append(s.charAt(j));
            if (j == s.length() - 1) {
                return false;
            }
            if (dfs(s, j + 1, Long.parseLong(sb.toString()) - 1)) {
                return true;
            }
        }
        return false;
    }

    // 找后面的数
    private boolean dfs(String s, int i, long targetNum) {
        if (i >= s.length()) {
            return true;
        }
        // 后面不会出现负数
        if (targetNum == 0) {
            while (i < s.length()) {
                if (s.charAt(i) != '0') {
                    return false;
                }
                i++;
            }
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            sb.append(s.charAt(j));
            long num = Long.parseLong(sb.toString());
            if (num > targetNum) {
                return false;
            }
            if (num == targetNum) {
                return dfs(s, j + 1, num - 1);
            }
        }
        return false;
    }
}
