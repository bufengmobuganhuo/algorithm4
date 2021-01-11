package chapter2_Sorting.chapter2_5_Applications.exercises;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangyu
 * 2020/3/11 18:47
 * 练习2.5.20：空闲时间
 */
public class EX_2_5_20_FreeTime {
    public static void main(String[] args) throws ParseException {
        String[] tasks = {"1st 01:00 02:00", "2nd 01:30 02:30", "3rd 04:00 04:10", "4th 04:05 05:50"};
        EX_2_5_20_FreeTime ex_2_5_20_freeTime = new EX_2_5_20_FreeTime();
        System.out.println(ex_2_5_20_freeTime.solution(tasks).toString());
    }

    public Map<String, Long> solution(String[] tasks) throws ParseException {
        if (tasks == null || tasks.length == 0) {
            return null;
        }
        TimeFrame[] timeFrames = new TimeFrame[tasks.length * 2];
        int index = 0;
        //将传入参数转换为时间节点
        for (int i = 0; i < tasks.length; i++) {
            String[] taskMsg = tasks[i].split(" ");
            timeFrames[index] = new TimeFrame(taskMsg[0], parseTimestamp(taskMsg[1]), false);
            timeFrames[index + 1] = new TimeFrame(taskMsg[0], parseTimestamp(taskMsg[2]), true);
            index += 2;
        }
        Arrays.sort(timeFrames);
        //空闲和忙碌的开始时间
        long freeStart = 0;
        long busyStart = 0;
        Long maxFreeTime = 0L;
        Long maxBusyTime = 0L;
        //事件计时器，遇到开始事件+1，遇到停止事件-1
        int jobCounter = 0;
        for (TimeFrame timeFrame : timeFrames) {
            //停止事件
            if (timeFrame.isStop) {
                jobCounter--;
                //jobCounter=0,表示当前时间节点之前所有事件执行结束，空闲时间开始
                if (jobCounter == 0) {
                    freeStart = timeFrame.timestamp;
                    //从忙碌开始到结束经历的时间
                    long busyTime = timeFrame.timestamp - busyStart;
                    maxBusyTime = Math.max(maxBusyTime, busyTime);
                }
            } else {
                //jobCounter=0,表示当前时间节点之前是空闲的，忙碌时间开始
                if (jobCounter == 0) {
                    busyStart = timeFrame.timestamp;
                    //从空闲开始到结束经历的时间
                    long freeTime = timeFrame.timestamp - freeStart;
                    maxFreeTime = Math.max(maxFreeTime, freeTime);
                }
                jobCounter++;

            }
        }
        Map<String, Long> res = new HashMap<>();
        res.put("maxBusyTime", maxBusyTime);
        res.put("maxFreeTime", maxFreeTime);
        return res;
    }

    private static long parseTimestamp(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = sdf.parse(time);
        return date.getTime();
    }

    class TimeFrame implements Comparable<TimeFrame> {
        String taskName;
        Long timestamp;
        //是否是停止节点
        boolean isStop;

        public TimeFrame(String taskName, long timestamp, boolean isStop) {
            this.taskName = taskName;
            this.timestamp = timestamp;
            this.isStop = isStop;
        }

        @Override
        public int compareTo(TimeFrame o) {
            return timestamp.compareTo(o.timestamp);
        }
    }
}
