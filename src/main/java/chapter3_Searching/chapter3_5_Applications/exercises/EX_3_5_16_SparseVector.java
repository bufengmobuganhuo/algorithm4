package chapter3_Searching.chapter3_5_Applications.exercises;

import edu.princeton.cs.algs4.SparseVector;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;

/**
 * @author zhangyu
 * 2020/4/14 11:30
 * TODO
 */
public class EX_3_5_16_SparseVector {
    public static void main(String[] args) {
        EX_3_5_16_SparseVector sparseVector1=new EX_3_5_16_SparseVector();
        sparseVector1.put(2,0.36);
        sparseVector1.put(3,0.36);
        sparseVector1.put(4,0.18);
        EX_3_5_16_SparseVector sparseVector2=new EX_3_5_16_SparseVector();
        sparseVector2.put(0,0.47);
        sparseVector2.put(2,0.47);
        EX_3_5_16_SparseVector res=sparseVector1.add(sparseVector2);
        for (Integer i:res.map.keySet()){
            System.out.println(i+":"+res.map.get(i));
        }
    }
    private HashMap<Integer,Double> map ;

    public EX_3_5_16_SparseVector() {
        this.map = new HashMap<>();
    }

    public void put(int i,double value){
        map.put(i,value);
    }

    public double get(int i){
        return map.get(i);
    }

    public double getOrDefault(int i,double defaultValue){
        return map.getOrDefault(i,defaultValue);
    }

    public double dot(double[] vector){
        if (vector==null||vector.length<map.size()){
            return 0;
        }
        double sum=0;
        for (Integer i:map.keySet()){
            sum+=map.get(i)*vector[i];
        }
        return sum;
    }

    public EX_3_5_16_SparseVector add(EX_3_5_16_SparseVector sparseVector){
        if (sparseVector==null){
            return null;
        }
        EX_3_5_16_SparseVector res=new EX_3_5_16_SparseVector();
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
}
