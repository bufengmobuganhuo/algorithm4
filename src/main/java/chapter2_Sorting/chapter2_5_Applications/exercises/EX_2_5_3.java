package chapter2_Sorting.chapter2_5_Applications.exercises;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import utils.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/3/5 18:45
 * 练习2.5.3：去除数组中的重复元素
 */
public class EX_2_5_3 {
    public static void main(String[] args) {
        for (int j=0;j<10;j++){
          char[] a= ArrayUtil.createDepStr(15).toCharArray();
            String[] arr=new String[a.length];
            for (int i=0;i<a.length;i++){
                arr[i]=String.valueOf(a[i]);
            }
            //String[] arr={"p","5","v","p"};
            String[] res= solution(arr);
            if (isDep(res)){
                System.out.print("{");
                for (int i=0;i<a.length;i++){
                    System.out.print("\""+arr[i]+"\",");
                }
                System.out.println("}");
            }
        }

    }
    public static String[] solution(String[] a){
        if (a==null||a.length==0){
            return null;
        }
        quickSort(a);
        String[] res=new String[a.length];
        res[0]=a[0];
        int j=1;
        for (int i=1;i<a.length;i++){
            if (a[i].compareTo(a[i-1])!=0){
                res[j++]=a[i];
            }
        }
        return res;
    }

    public static boolean isDep(String[] arr){
        for (int i=1;i<arr.length;i++){
            if (arr[i]!=null&&arr[i-1]!=null&&arr[i-1].equals(arr[i])){
                return true;
            }
        }
        return false;
    }

    private static void quickSort(String[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(String[] arr,int start,int end){
        if (start>=end){
            return;
        }
        int partitionIdx=partition(arr,start,end);
        quickSort(arr,start,partitionIdx-1);
        quickSort(arr,partitionIdx+1,end);
    }

    private static int partition(String[] arr,int start,int end){
        int left=start,right=end+1;
        String partitionKey=arr[start];
        while (true){
            //左指针向右查找，直到遇到一个元素>=partitionKey
            while (partitionKey.compareTo(arr[++left])>0){
                if (left==end){
                    break;
                }
            }
            //右指针向左查找，直到遇到一个元素<=partitionKey
            while (partitionKey.compareTo(arr[--right])<0){
                if (right==start){
                    break;
                }
            }
            if (left >=right){
                break;
            }
            exch(arr,left,right);
        }
        //找到悲切分匀速应该在的位置
        exch(arr,right,start);
        return right;
    }
    private static void exch(String[] arr,int i,int j){
        String temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
