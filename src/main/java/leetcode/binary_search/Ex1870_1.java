package leetcode.binary_search;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1870_1 {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canReach(dist, mid, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canReach(int[] dist, int speed, double hour) {
        double count = 0.0;
        for (int i = 0; i < dist.length - 1; i++) {
            count += Math.ceil((double) dist[i] / speed);
        }
        count += (double) dist[dist.length - 1] / speed;
        return count <= hour;
    }
}
