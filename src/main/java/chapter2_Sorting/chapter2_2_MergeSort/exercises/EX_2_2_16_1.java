package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/21 14:48
 * 练习2.2.16：自然的归并排序，第一次尝试
 */
public class EX_2_2_16_1 {
    public static void main(String[] args) {
        Integer[] arr= ArrayUtil.createInt(15,20);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(Integer[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        int left=0;
        int mid=0;
        Integer[] temp=new Integer[arr.length];
        while (!(left==0&&mid==arr.length-1)){
            left=0;
            while (left<arr.length){
                mid=left+findBlockSize(arr,left)-1;
                if (mid==arr.length-1){
                    break;
                }
                int right=mid+findBlockSize(arr,mid+1);
                inPlaceMerge(arr,left,mid,right,temp);
                left=right+1;
            }
        }
    }

    private static int findBlockSize(Integer[] arr,int start){
        int size=1;
        while (start<arr.length-1&&arr[start]<=arr[start+1]){
            size++;
            start++;
        }
        return size;
    }

    public static void inPlaceMerge(Integer[] arr,int left,int mid,int right,Integer[] temp){
        System.arraycopy(arr,left,temp,left,right-left+1);
        int i=left,j=mid+1;
        for (int k=left;k<right+1;k++){
            if (i>mid){
                arr[k]=temp[j++];
            }else if(j>right){
                arr[k]=temp[i++];
            }else if(temp[i]<temp[j]){
                arr[k]=temp[i++];
            }else{
                arr[k]=temp[j++];
            }
        }
    }

}
