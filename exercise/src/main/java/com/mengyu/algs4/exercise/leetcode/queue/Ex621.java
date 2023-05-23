package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/7/9 11:23 上午
 * leetcode621
 * 1. 对于任务调度，应该先安排出现次数多的任务，以避免后续的空闲时间
 * 2. 规定n+1个任务为一轮，进行安排任务，这样可以保证同一轮中一个任务只能被安排一次
 *      1⃣️ 在每一轮中，将当前的任务按照剩余的次数降序排序，并选择次数最多的n+1个任务依次执行
 *      2⃣️ 如果任务的种类t少于n+1，则让剩下的时间空闲
 * 3. 上述可以保证：这一轮的第k个任务距离上一次执行至少有n个单位的冷却时间
 *      反证：1⃣️ 假设r轮中某个任务在第k个执行，那么说明他在第r轮时为数量第k多的任务
 *           2⃣️ 在第r轮结束后，第1多到第k多的任务次数都会-1，
 *              因此在第r+1轮，这个任务最多也只能是数量第k多，因此他如果被执行，一定满足冷却时间
 *              （这也是每轮安排n+1个任务的原因）
 */
public class Ex621 {
    public int leastInterval(char[] tasks, int n) {
        // 1.统计每个任务的出现次数
        int[] mapCount=new int[26];
        for (char task:tasks){
            // 字符，用数字就可以映射
            mapCount[task-'A']++;
        }
        // 2.将上述次数的映射进行排序(结果是小 -> 大)
        Arrays.sort(mapCount);
        int time=0;
        // 出现次数最大的在mapCount[25]，并且每次都会重新排序更新，
        // 当他=0时，则安排完了
        while(mapCount[25]!=0){
            int i=0;
            // 每轮安排n+1个任务
            while(i<=n){
                if (mapCount[25]==0){
                    break;
                }
                // 假如可分配任务数<n,则不分配，空闲
                if (i<26&&mapCount[25-i]>0){
                    mapCount[25-i]--;
                }
                i++;
                time++;
            }
            // 重新排序
            Arrays.sort(mapCount);
        }
        return time;
    }
}
