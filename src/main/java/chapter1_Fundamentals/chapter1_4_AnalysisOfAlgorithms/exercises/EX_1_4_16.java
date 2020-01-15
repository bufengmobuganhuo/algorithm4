package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 练习1.4.16：最接近的两个数
 */
public class EX_1_4_16 {
    public static void main(String[] args) {
        double[] arr= ArrayUtil.createDouble(5,10);
        double[] res=closestNumber(arr);
        System.out.println("原数组："+Arrays.toString(arr)+" 结果："+Arrays.toString(res));
    }
    public static double[] closestNumber(double[] arr){
        if (arr==null||arr.length==0){
            return null;
        }
        Arrays.sort(arr);
        double minValue=Double.MAX_VALUE;
        double[] res=new double[2];
        for(int i=0;i<arr.length-1;i++){
            double sub=Math.abs(arr[i]-arr[i+1]);
            if (minValue>sub){
                minValue=sub;
                res[0]=arr[i];
                res[1]=arr[i+1];
            }
        }
        return res;
    }


}
