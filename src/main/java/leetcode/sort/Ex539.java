package leetcode.sort;

import java.util.List;

/**
 * @author yu zhang
 */
public class Ex539 {
    private static final int last = 23 * 60 + 59;

    public int findMinDifference(List<String> timePoints) {
        timePoints.sort(String::compareTo);
        int idx = 0;
        while (idx < timePoints.size() && timePoints.get(idx).equals("00:00")) {
            idx++;
        }
        if (idx == timePoints.size() || idx > 1) {
            return 0;
        }
        int ans = 0;
        int time1 = getDuration(timePoints.get(idx));
        int time2 = getDuration(timePoints.get(1));
        if (time1 == 0) {
            timePoints.add("00:00");
            int time3 = getDuration(timePoints.get(2));
            ans = (time3 == 0 ? last + 1 : time3) - time2;
        } else {
            ans = time2 - time1;
        }
        if (timePoints.size() <= 2) {
            return ans;
        }
        int time3 = getDuration(timePoints.get(timePoints.size() - 2));
        int time4 = getDuration(timePoints.get(timePoints.size() - 1));
        ans = Math.max(ans, (time4 == 0 ? last + 1 : time4) - time3);
        return ans;
    }

    private int getDuration(String time) {
        return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
    }
}
