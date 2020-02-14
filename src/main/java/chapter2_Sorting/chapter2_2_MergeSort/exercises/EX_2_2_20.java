package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;
import chapter2_Sorting.chapter2_1_ElementarySorts.Template;

import javax.print.attribute.standard.PrinterResolution;
import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/2/14 11:41
 * 练习2.2.20：间接排序
 */
public class EX_2_2_20 implements Template {
    private static int[] temp;
    private static int[] index;

    public static void main(String[] args) {
        Comparable[] arr= ArrayUtil.createInt(15,20);
        EX_2_2_20 ex_2_2_20=new EX_2_2_20();
        ex_2_2_20.show("排序前:",arr);
        ex_2_2_20.sort(arr);
        ex_2_2_20.show("排序后:",arr);
        System.out.println("索引数组:"+Arrays.toString(index));
    }
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        index=new int[arr.length];
        temp=new int[arr.length];
        for (int i=0;i<arr.length;i++){
            index[i]=i;
        }
        sort(arr,0,arr.length-1);
    }
    public void sort(Comparable[] arr,int left,int right){
        if (left>=right){
            return;
        }
        int mid=left+(right-left)/2;
        sort(arr,left,mid);
        sort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }
    public void merge(Comparable[] arr,int left,int mid,int right){
        int i=left,j=mid+1;
        System.arraycopy(index, left,temp,left,right-left+1);
        for (int k=left;k<=right;k++){
            if (i>mid){
                index[k]=temp[j++];
            }else if(j>right){
                index[k]=temp[i++];
            }else if(less(arr[temp[j]],arr[temp[i]])){
                index[k]=temp[j++];
            }else{
                index[k]=temp[i++];
            }
        }
    }
}
