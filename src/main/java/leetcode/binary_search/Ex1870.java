package leetcode.binary_search;

/**
 * @author yuzhang
 * @date 2021/5/23 上午10:28
 * TODO
 */
public class Ex1870 {
    public static void main(String[] args) {
        Ex1870 ex1870 = new Ex1870();
        int[] dist = {1, 3, 2};
        System.out.println(ex1870.minSpeedOnTime(dist, 6));
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        // 因为整点到达，所以最少会使用dist.len个小时
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        // 对速度做二分查找
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果可以到达，则缩小范围，尝试减少速度
            if (canReach(dist, mid, hour)) {
                right = mid;
                // 否则尝试增大速度
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canReach(int[] dist, int speed, double hour) {
        double count = 0.0;
        for (int i = 0; i < dist.length - 1; i++) {
            // 对除了最后一个站点外的时间向上取整
            count += Math.ceil((double) dist[i] / speed);
        }
        count += (double) dist[dist.length - 1] / speed;
        return count <= hour;
    }
}
