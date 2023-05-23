package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_4_PriorityQueues.exercises;

import java.util.Random;

/**
 * @author zhangyu
 * 2020/3/2 19:05
 * 练习2.4.35：离散概率分布的取样
 * 翻译错误：要求返回索引i的概率为p[i]/T
 */
public class EX_2_4_35_Sample {
    public static void main(String[] args) {
        double[] data={0.1,0.1,0.2,0.2,0.1,0.3};
        EX_2_4_35_Sample ex_2_4_35_sample=new EX_2_4_35_Sample(data);
        ex_2_4_35_sample.Random();
    }
    private double[] P;
    private double[] sumP;
    private double T;
    private Random random;

    public EX_2_4_35_Sample(double[] data) {
        random=new Random();
        this.P = new double[data.length+1];
        for (int i=0;i<data.length;i++){
            P[i+1]=data[i];
            T+=data[i];
        }
        this.sumP =new double[data.length+1];
        for (int i = sumP.length-1; i/2>0; i--){
            sumP[i/2]= P[i];
        }
    }

    public int Random(){
        double percentage=random.nextDouble()*T;
        int index=1;
        while (index*2<= P.length){
            if (percentage<= P[index]){
                break;
            }
            percentage-=P[index];
            index*=2;
            if (percentage<=sumP[index]+P[index]){
                continue;
            }
            percentage-=sumP[index]+P[index];
            index++;
        }
        return index-1;
    }
    public void change(int i,double v){
        i++;
        P[i]=v;
        sumP[i]= P[i*2]+sumP[i*2];
        if (i*2+1<P.length){
            sumP[i]+=P[i*2+1]+sumP[i*2+1];
        }
    }
}
