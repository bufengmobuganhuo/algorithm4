package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuzhang
 * @date 2021/2/7 上午11:02
 * TODO
 */
public class Ex_2_5_20_2 {
    public static void solution(String[] tasks) throws ParseException {
        if (tasks == null || tasks.length == 0) {
            return;
        }
        TimeFrame[] timeFrames = new TimeFrame[tasks.length * 2];
        int index = 0;
        for (String task : tasks) {
            String[] taskInfo = task.split(" ");
            timeFrames[index] = new TimeFrame(taskInfo[0], parseToTimeStamp(taskInfo[1]), false);
            timeFrames[index] = new TimeFrame(taskInfo[0], parseToTimeStamp(taskInfo[2]), true);
            index += 2;
        }
        Arrays.sort(timeFrames);
        long maxFreeTime = 0, maxBusyTime = 0;
        long freeTimeStart = 0, busyTimeStart = 0;
        int jobCount = 0;
        for (TimeFrame timeFrame : timeFrames) {
            if (timeFrame.isStop) {
                if (jobCount == 0) {
                    long busyTime = timeFrame.timestamp - busyTimeStart;
                    maxBusyTime = Math.max(busyTime, maxBusyTime);
                    freeTimeStart = timeFrame.timestamp;
                }
            } else {
                if (jobCount == 0) {
                    long freeTime = timeFrame.timestamp - freeTimeStart;
                    maxFreeTime = Math.max(maxFreeTime, freeTime);
                    busyTimeStart = timeFrame.timestamp;
                }
                jobCount++;
            }
        }
        System.out.println("max busy time: "+maxBusyTime);
        System.out.println("max free time: "+maxFreeTime);
    }

    private static long parseToTimeStamp(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = sdf.parse(time);
        return date.getTime();
    }

    static class TimeFrame implements Comparable<TimeFrame> {
        boolean isStop;
        String taskName;
        long timestamp;

        public TimeFrame(String taskName, long timestamp, boolean isStop) {
            this.taskName = taskName;
            this.timestamp = timestamp;
            this.isStop = isStop;
        }

        @Override
        public int compareTo(TimeFrame o) {
            return (int) (this.timestamp - o.timestamp);
        }
    }
}
