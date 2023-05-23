package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/9 11:42 上午
 * leetcode621:第二种解法
 * 使用优先队列（大顶堆），代替排序，
 * 1. 在每轮中，都从其中拿出n+1个任务进行安排
 * 2. 安排完后，再将其放入优先队列中
 */
public class Ex621_1 {
    public static void main(String[] args) {
        int[] nums={5,8,2,0,3,1};
        InnerPriorityQueue priorityQueue=new InnerPriorityQueue(6);
        for (int val : nums) {
            priorityQueue.add(val);
        }
        while(!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }
    public int leastInterval(char[] tasks, int n) {
        // 1.统计任务出现的次数
        int[] mapCount=new int[26];
        for (char task : tasks) {
            mapCount[task-'A']++;
        }
        // 2.将统计好的次数放入大顶堆
        InnerPriorityQueue priorityQueue=new InnerPriorityQueue(26);
        for (int i = 0; i < mapCount.length; i++) {
            if (mapCount[i]!=0){
                priorityQueue.add(mapCount[i]);
            }
        }
        int time=0;
        while(!priorityQueue.isEmpty()){
            int i=0;
            // 负责临时存储安排的任务
            List<Integer> tmpTask=new ArrayList<>();
            // 每轮安排n+1个任务
            while(i<=n){
                // 如果为空，说明任务安排完，发生空闲
                if (!priorityQueue.isEmpty()){
                    // 保证不把次数=0的任务重新放入队列中
                    if (priorityQueue.peek()>1){
                        tmpTask.add(priorityQueue.poll()-1);
                    }else{
                        priorityQueue.poll();
                    }
                }
                time++;
                // 所有任务全部安排完毕，提前结束
                if (priorityQueue.isEmpty()&&tmpTask.size()==0){
                    break;
                }
                i++;
            }
            //将还未安排完的任务重新放回优先队列
            for (Integer task :
                    tmpTask) {
                priorityQueue.add(task);
            }
        }

        return time;
    }
    // 大顶堆
    static class InnerPriorityQueue {
        int[] priorityArr;
        int size;

        public InnerPriorityQueue(int len) {
            priorityArr=new int[len+1];
        }

        public boolean isEmpty(){
            return size==0;
        }

        public int peek(){
            return priorityArr[1];
        }

        public int poll(){
            int res=priorityArr[1];
            exch(1,size--);
            sink(1);
            return res;
        }

        public void add(int val){
            priorityArr[++size]=val;
            swim(size);
        }

        // 上浮
        private void swim(int k){
            while (k>1&&priorityArr[k]>priorityArr[k/2]){
                exch(k,k/2);
                k/=2;
            }
        }

        // 下沉
        private void sink(int k){
            while(2*k<=size){
                int j=2*k;
                if (j<size&&priorityArr[j+1]>priorityArr[j]){
                    j++;
                }
                if (priorityArr[k]>priorityArr[j]){
                    break;
                }
                exch(k,j);
                k=j;
            }
        }

        private void exch(int i,int j){
            int temp=priorityArr[i];
            priorityArr[i]=priorityArr[j];
            priorityArr[j]=temp;
        }

    }
}
