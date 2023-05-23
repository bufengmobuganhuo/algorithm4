package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/26 11:37
 * 练习2.4.28：欧几里得距离的topK问题,找出距离最小的K个元素
 */
public class EX_2_4_28 {
    public static void main(String[] args) {
        EX_2_4_28 ex_2_4_28=new EX_2_4_28();
        Random random=new Random();
        List<Position> positions=new ArrayList<>();
        for (int i=0;i<10;i++){
            double x=10*random.nextDouble();
            double y=10*random.nextDouble();
            double z=10*random.nextDouble();
            System.out.print(ex_2_4_28.getEuclideanDistance(x,y,z)+" ");
            positions.add(ex_2_4_28.new Position(x,y,z));
        }
        System.out.println();
        List<Double> res=ex_2_4_28.solution(positions,5);
        for (Double item:res){
            System.out.print(item+" ");
        }
    }
    public List<Double> solution(List<Position> positions,int K){
        //使用大顶堆
        PriorityQueue<Double> priorityQueue=new PriorityQueue<>();
        for (int i=0;i<positions.size();i++){
            Position position=positions.get(i);
            double distance=getEuclideanDistance(position.x,position.y,position.z);
            //先加入K个元素
            if (i<K){
                priorityQueue.insert(distance);
                //如果要加入的元素比K个元素中的最大值要大，则肯定不是最小的K个元素之一,不需要加入队列
                //否则需要加入队列
            }else if(distance<priorityQueue.max()){
                priorityQueue.delMax();
                priorityQueue.insert(distance);
            }
        }
        List<Double> res=new ArrayList<>(K);
        while (!priorityQueue.isEmpty()){
            res.add(priorityQueue.delMax());
        }
        return res;
    }
    public double getEuclideanDistance(double x,double y,double z){
        return Math.pow(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2),0.5);
    }
    public class Position{
        double x;
        double y;
        double z;

        public Position(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
