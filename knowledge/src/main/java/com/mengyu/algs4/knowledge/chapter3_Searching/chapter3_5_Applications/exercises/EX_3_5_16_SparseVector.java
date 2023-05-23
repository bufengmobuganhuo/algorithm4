package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

import java.util.HashMap;

/**
 * @author zhangyu
 * 2020/4/14 11:30
 * TODO
 */
public class EX_3_5_16_SparseVector {
    public static void main(String[] args) {
        EX_3_5_16_SparseVector sparseVector1=new EX_3_5_16_SparseVector(5);
        sparseVector1.put(2,0.36);
        sparseVector1.put(3,0.36);
        sparseVector1.put(4,0.18);
        EX_3_5_16_SparseVector sparseVector2=new EX_3_5_16_SparseVector(5);
        sparseVector2.put(0,0.47);
        sparseVector2.put(2,0.47);
        EX_3_5_16_SparseVector res=sparseVector1.add(sparseVector2);
        for (Integer i:res.map.keySet()){
            System.out.println(i+":"+res.map.get(i));
        }
        System.out.println(sparseVector1.dot(sparseVector2));

    }
    private HashMap<Integer,Double> map ;
    //n维向量
    private int n;

    public EX_3_5_16_SparseVector(int n) {
        this.map = new HashMap<>();
        this.n=n;
    }

    public EX_3_5_16_SparseVector(Double[] values){
        n=values.length;
        map=new HashMap<>();
        for (int i=0;i<values.length;i++){
            if (values[i]!=0){
                map.put(i,values[i]);
            }
        }
    }

    public int getN() {
        return n;
    }

    public void put(int i, double value){
        map.put(i,value);
    }

    public double get(int i){
        return map.get(i);
    }

    public double getOrDefault(int i,double defaultValue){
        return map.getOrDefault(i,defaultValue);
    }

    public double dot(double[] vector){
        if (vector==null||vector.length!=n){
            return 0;
        }
        double sum=0;
        for (Integer i:map.keySet()){
            sum+=map.get(i)*vector[i];
        }
        return sum;
    }

    public double dot(EX_3_5_16_SparseVector vector){
        if (n!=vector.n){
            throw new IllegalArgumentException();
        }
        double sum=0;
        if (map.size()>=vector.map.size()){
            for (Integer key:vector.map.keySet()){
                sum+=vector.get(key)*getOrDefault(key,0);
            }
        }else{
            for (Integer key:map.keySet()){
                sum+=map.get(key)*vector.getOrDefault(key,0);
            }
        }
        return sum;
    }

    public EX_3_5_16_SparseVector add(EX_3_5_16_SparseVector sparseVector){
        if (sparseVector==null){
            return null;
        }
        EX_3_5_16_SparseVector res=new EX_3_5_16_SparseVector(n);
        //先把被加数放入res的map
        for (Integer i:sparseVector.map.keySet()){
            res.put(i,sparseVector.map.get(i));
        }
        //再根据加数来与上述res的map相加
        for (Integer i:map.keySet()){
            double value1=map.get(i);
            double value2=res.getOrDefault(i,0);
            double sum=value1+value2;
            if (sum!=0){
                res.put(i,sum);
            }
        }
        return res;
    }

    public void println(){
        for(int i=0;i<n;i++){
            System.out.print(map.getOrDefault(i,0d)+" ");
        }
        System.out.println();
    }
}
