package chapter5_Strings.chapter5_1_StringSorts;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/6/1 16:30
 * 三向字符串快速排序
 */
public class Quick3String {
    public static void main(String[] args) {
        String[] arr={
                "152",
                "348",
                "539",
                "539",
                "077",
                "483",
                "691",
        };
        Quick3String.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(String[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        sort(arr,0,arr.length-1,0);
    }

    /**
     * 以数组中每个字符串的第index个字符为键排序
     * @param arr
     * @param lo
     * @param hi
     * @param index
     */
    private static void sort(String[] arr,int lo,int hi,int index){
        if (hi<=lo){
            return;
        }
        int lessPointer=lo,greaterPointer=hi;
        int i=lo+1;
        //切分元素
        int partitionEle=charAt(arr[lo],index);
        //切分过程
        while (i<=greaterPointer){
            int ele=charAt(arr[i],index);
            if (ele>partitionEle){
                //因为交换后charAt(arr[i],index)与切分元素的大小关系未知，
                // 所以i不可以变
                exch(arr,greaterPointer--,i);
            }else if(ele < partitionEle){
                //因为一开始切分元素=charAt(arr[lt],index),
                //所以交换后，charAt(arr[i],index)一定<=切分元素，所以i可以+1
                exch(arr,lessPointer++,i++);
            }else{
                i++;
            }
        }
        /**
         * 排序结束后，arr[lo...lt-1]<partitionEle=arr[lt...gt]<arr[gt+1...hi]
         * 对于左右两边的，还是应该使用第index个字符作为切分元素来排序（因为这些各不相同）
         * 对于中间的，由于第index位都是一样的，所以忽略这一位字符，使用下一位字符比较
         * */
        sort(arr,lo,lessPointer-1,index);
        if (partitionEle>=0){
            sort(arr,lessPointer,greaterPointer,index+1);
        }
        sort(arr,greaterPointer+1,hi,index);
    }

    private static void exch(String[] arr,int i,int j){
        String temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


    private static int charAt(String str, int index){
        if (index<str.length()){
            return str.charAt(index);
        }else{
            return -1;
        }
    }
}
