package leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex1578 {

    /**
     * 效率更高没有计算
     */
    public int minCost2(String colors, int[] neededTime) {
        int res = 0;
        for (int i = 1; i < colors.length(); i++) {
            // 如果发现相邻的两个颜色相同，则删除消耗较小的那个
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                int delete = 0;
                if (neededTime[i] < neededTime[i - 1]) {
                    delete = neededTime[i];
                    // 因为colors[i]已经被删除了，则后面不会在产生影响，为了防止以下情况发生：
                    // aaa ->[4,1,2]，当遍历到colors[1]时，判断需要删除colors[1]，
                    // 此时如果不更新needTime，则变成了aa -> [4,1,2]
                    // 那么如果发现colors[2]也一样，删除的还是colors[1]，不对
                    neededTime[i] = neededTime[i - 1];
                } else {
                    delete = neededTime[i - 1];
                }
                res += delete;
            }
        }
        return res;
    }


    public int minCost(String colors, int[] neededTime) {
        int i = 0, res = 0;
        while (i < colors.length()) {
            char color = colors.charAt(i);
            // 需要保留的最大消耗时间
            int maxValueNeedRemain = 0;

            int sum = 0;
            // 如果颜色一直相同，则一直向右找
            while (i < colors.length() && color == colors.charAt(i)) {
                // 保留下消耗时间最长的位置对应的颜色
                maxValueNeedRemain = Math.max(maxValueNeedRemain, neededTime[i]);
                // 相同颜色的总消耗时间
                sum += neededTime[i];
                i++;
            }
            // 总消耗时间 - 要保留的最大消耗时间 = 移除气球时的消耗时间
            res += (sum - maxValueNeedRemain);
        }
        return res;
    }
}
