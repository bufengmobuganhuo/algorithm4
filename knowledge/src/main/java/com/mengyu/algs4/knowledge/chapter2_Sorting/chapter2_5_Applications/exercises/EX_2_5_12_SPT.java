package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

import java.util.Scanner;

/**
 * @author zhangyu
 * 2020/3/9 15:46
 * 练习2.5.12：调度
 */
public class EX_2_5_12_SPT{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String tasks=scanner.nextLine();
        EX_2_5_12_SPT ex_2_5_12_spt=new EX_2_5_12_SPT();
        for (String item:tasks.split(" ")){
            String[] params=item.split(",");
            ex_2_5_12_spt.insert(params[0],Float.parseFloat(params[1]));
        }
        while (!ex_2_5_12_spt.isEmpty()){
            System.out.println(ex_2_5_12_spt.delMin());
        }
    }
    private Task[] minPriorityQue;
    private int size;

    public EX_2_5_12_SPT() {
        minPriorityQue= new Task[16];
    }

    public String delMin(){
        Task min=minPriorityQue[1];
        exch(1,size);
        minPriorityQue[size--]=null;
        sink(1);
        return min.toString();
    }

    public void insert(String taskName,Float exeTime){
        minPriorityQue[++size]=new Task(taskName, exeTime);
        swim(size);
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void sink(int k){
        while (2*k<=size){
            int j=2*k;
            if (j<size&&less(minPriorityQue[j+1],minPriorityQue[j])){
                j++;
            }
            if (less(minPriorityQue[k],minPriorityQue[j])){
                break;
            }
            exch(k,j);
            k=j;
        }
    }

    private void swim(int k){
        while (k>1&&less(minPriorityQue[k],minPriorityQue[k/2])){
            exch(k,k/2);
            k/=2;
        }
    }

    private void exch(int i, int j){
        Task task=minPriorityQue[i];
        minPriorityQue[i]=minPriorityQue[j];
        minPriorityQue[j]=task;
    }

    private boolean less(Task value1,Task value2){
        return value1.compareTo(value2)<0;
    }

    class Task implements Comparable<Task>{
        String taskName;
        Float exeTime;

        public Task(String taskName, float exeTime) {
            this.taskName = taskName;
            this.exeTime = exeTime;
        }

        @Override
        public int compareTo(Task o) {
            return exeTime.compareTo(o.exeTime);
        }

        @Override
        public String toString() {
            return "taskName:"+taskName+" exeTime:"+exeTime;
        }
    }
}
