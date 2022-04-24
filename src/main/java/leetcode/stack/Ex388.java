package leetcode.stack;

/**
 * @author yu zhang
 */
public class Ex388 {
    /**
     * 1. 可以使用\t的个数表示深度
     * 2. 对于一个深度=depth的目录/文件，他的上一层目录是深度为depth-1的最新目录
     * 那么截止到depth，他的长度就是上一个目录长度+当前目录长度+1（有一个/）
     */
    public int lengthLongestPath(String input) {
        int len = input.length();
        // 维护深度对应的路径长度（这个路径上都是目录，而不是文件，这样才能在此基础上append新目录/文件）
        int[] depths = new int[len + 1];
        int idx = 0, ans = 0;
        while (idx < len) {
            int depth = 1;
            // 计算深度
            while (idx < len && input.charAt(idx) == '\t') {
                depth++;
                idx++;
            }
            boolean isFile = false;
            // 计算当前目录的长度
            int curLen = 0;
            while (idx < len && input.charAt(idx) != '\n') {
                if (input.charAt(idx) == '.') {
                    isFile=true;
                }
                idx++;
                curLen++;
            }
            // \n 不算长度
            idx++;
            // 不是开头的目录
            if (depth > 1) {
                curLen += depths[depth - 1] + 1;
            }
            if (isFile) {
                ans = Math.max(ans, curLen);
            } else {
                // 维护深度对应的路径长度，对于文件结尾的路径，后面不能再添加更长路径，所以不可以更新深度对应的路径长度
                depths[depth] = curLen;
            }
        }
        return ans;
    }
}
