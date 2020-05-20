package utils;

import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

public class ArrayUtil {
    private static Set<Integer> set=new HashSet<Integer>();
    public static Integer[] createInt(int length,int max){
        if (length>max){
            return null;
        }
        Integer[] arr=new Integer[length];
        for (int i=0;i<arr.length;i++){
            arr[i]= StdRandom.uniform(max);
        }
        return arr;
    }
    /**
     * @return 生成不重复的数组(有正有负)
     */
    public static Integer[] createInt(int length, int max,boolean needN){
        if (length>max){
            return null;
        }
        int size=0;
        Random random=new Random();
        Integer[] res=new Integer[length];
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
    public static Integer[] createDoubleToneInt(int length,int max){
        Integer[] left=createInt(length/2,max,false);
        Arrays.sort(left);
        Integer[] right=createInt(length/2,left[left.length-1],false);
        Comparator<Integer> cmp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        };
        Arrays.sort(right,cmp);
        Integer[] res=new Integer[length];
        System.arraycopy(left,0,res,0,left.length);
        System.arraycopy(right,0,res,left.length,right.length);
        set.clear();
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

    /**
     * @param len
     * @return 生成含有连接词的字符串
     */
    public static String[] createCompoundStrs(int len){
        Random random=new Random();
        String[] res=new String[len];
        int compoundStrNum=random.nextInt(len-2);
        for (int i=0;i<len-compoundStrNum;i++){
            res[i]=createStr(5);
        }
        for (int i=len-compoundStrNum;i<len;i++){
            int idx1=random.nextInt(len-compoundStrNum);
            int idx2=random.nextInt(len-compoundStrNum);
            res[i]=res[idx1]+res[idx2];
        }
        return res;
    }

    public static String createDepStr(int maxLen){
        Random random=new Random();
        int len=random.nextInt(maxLen);
        while (len<=2){
            len=random.nextInt(maxLen);
        }
        String str=createStr(len);
        return str+str.substring(0,str.length()-2);
    }

    public static String createStr(int maxLen){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        int len=random.nextInt(maxLen);
        while (len<=2){
            len=random.nextInt(maxLen);
        }
        while (sb.length()<len){
            int idx=random.nextInt(62);
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }

    public static void shuffle(Object[] arr){
        Random random=new Random();
        for (int i=0;i<arr.length;i++){
            int randomIdx=i+random.nextInt(arr.length-i);
            Object temp=arr[randomIdx];
            arr[randomIdx]=arr[i];
            arr[i]=temp;
        }
    }
}
