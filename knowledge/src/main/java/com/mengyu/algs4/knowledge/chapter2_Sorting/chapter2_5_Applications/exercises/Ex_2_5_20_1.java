package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/10/30 11:49 上午
 * TODO
 */
public class Ex_2_5_20_1 {
    public static void main(String[] args) {
        String[] tasks={"1st 01:00 02:00","2nd 01:30 02:30","3rd 04:00 04:10","4th 04:05 05:50"};
        System.out.println(solution(tasks));
    }
    public static List<Long> solution(String[] jobs){
        // 转化
        TimeFrame[] timeFrames = new TimeFrame[jobs.length*2];
        int index = 0;
        for (int i = 0; i < jobs.length; i++) {
            String[] params = jobs[i].split(" ");
            timeFrames[index]=new TimeFrame(params[0],parse2Timestamp(params[1]),false);
            timeFrames[index+1]=new TimeFrame(params[0],parse2Timestamp(params[2]),true);
            index+=2;
        }
        sort(timeFrames);
        long maxBusyTime = 0;
        long maxFreeTime = 0;
        long busyStartTime = 0;
        long freeStartTime = 0;
        int jobCounter=0;
        for (int i = 0; i < timeFrames.length; i++) {
            if (timeFrames[i].isStop){
                jobCounter--;
                if (jobCounter==0){
                    freeStartTime = timeFrames[i].timestamp;
                    long busyTime = timeFrames[i].timestamp-busyStartTime;
                    maxBusyTime=Math.max(busyTime,maxBusyTime);
                }
            }else{
                if (jobCounter==0){
                    busyStartTime = timeFrames[i].timestamp;
                    long freeTime = timeFrames[i].timestamp-freeStartTime;
                    maxFreeTime=Math.max(maxFreeTime,freeTime);
                }
                jobCounter++;
            }
        }
        List<Long> ans = new ArrayList<>();
        ans.add(maxBusyTime);
        ans.add(maxFreeTime);
        return ans;
    }

    private static void sort(TimeFrame[] timeFrames){
        for (int i = 0; i < timeFrames.length; i++) {
            int minIdx = i;
            for (int j = i+1; j < timeFrames.length; j++) {
                if (timeFrames[j].compareTo(timeFrames[minIdx])<0){
                    minIdx=j;
                }
            }
            exch(timeFrames,minIdx,i);
        }
    }

    private static void exch(TimeFrame[] timeFrames,int i,int j){
        TimeFrame tmp = timeFrames[i];
        timeFrames[i]=timeFrames[j];
        timeFrames[j]=tmp;
    }

    private static long parse2Timestamp(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static class TimeFrame implements Comparable<TimeFrame>{
        String jobName;
        long timestamp;
        boolean isStop;

        public TimeFrame(String jobName, long timestamp, boolean isStop) {
            this.jobName = jobName;
            this.timestamp = timestamp;
            this.isStop = isStop;
        }

        @Override
        public int compareTo(TimeFrame o) {
            return (int) (this.timestamp-o.timestamp);
        }
    }
}
