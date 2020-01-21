package chapter1_Fundamentals.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArrayUtil {
    /**
     * @return 生成不重复的数组(有正有负)
     */
    public static int[] createInt(int length, int max,boolean needN){
        if (length>max){
            return null;
        }
        Set<Integer> set=new HashSet<Integer>(length);
        int size=0;
        Random random=new Random();
        int[] res=new int[length];
        while (size<length){
            int item;
            if (needN){
                item=(Math.random()>0.5?-1:1)*random.nextInt(max);
            }else {
                item =random.nextInt(max);
            }
            if (!set.contains(item)){
                set.add(item);
                res[size]=item;
                size++;
            }
        }
        return res;
    }

    /**
     * @return 生成不重复的数组(有正有负)
     */
    public static double[] createDouble(int length, int max){
        if (length>max){
            return null;
        }
        int size=0;
        Random random=new Random();
        double[] res=new double[length];
        while (size<length){
            double item=(Math.random()>0.5?-1:1)*random.nextInt(max)*Math.random();
            res[size]=item;
            size++;
        }
        return res;
    }

    public static int[][] createMatrix(int maxRow,int maxCol,int maxValue){
        HashSet<Integer> set=new HashSet<Integer>(maxRow*maxCol);
        Random random=new Random();
        int row=0;
        while (row<=0){
            row=random.nextInt(maxRow);
        };
        int col=row;
        while (col<=0){
            col=random.nextInt(maxCol);
        }
        int[][] arr=new int[row][];
        for (int i=0;i<row;i++){
            arr[i]=new int[col];
            for (int j=0;j<col;j++){
                int item=0;
                while (set.contains(item)){
                    item=random.nextInt(maxValue);
                }
                set.add(item);
                arr[i][j]=item;
            }
        }
        return arr;
    }
}
