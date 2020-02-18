package chapter2_Sorting.chapter2_2_MergeSort;

import chapter2_Sorting.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/8 17:18
 * 自底向上归并
 */
public class MergeFromBottomToTop implements SortTemplate {
    private Comparable[] temp;
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        temp=new Comparable[arr.length];
        //两两归并->四四归并->八八归并...，所以size以两倍速度增长
        for (int size=1;size<arr.length;size+=size){
            for (int left=0;left<arr.length-size;left+=size+size){
                /**
                 * left,mid,right表示的是在原数组中的索引
                 * 两两归并的话，归并的是(arr[0],arr[1]),(arr[2],arr[3])...,
                 * 最后一次归并时，第二个数组可能比第一个数组小
                 * left=0 right=min(left+size+size-1,arr.length-1)
                 * */
                inPlaceMerge(arr,left,left+size-1,Math.min(left+size+size-1,arr.length-1));
            }
        }
    }
    private void inPlaceMerge(Comparable[] arr,int left,int mid,int right){
        int i=left,j=mid+1;
        System.arraycopy(arr,left,temp,left,right-left+1);
        for (int k=left;k<=right;k++){
            //如果左边取完，从右边取
            if (i>mid){
                arr[k]=temp[j++];
                //右边取完从左边取
            }else if(j>right){
                arr[k]=temp[i++];
                //如果左边元素<右边元素，取左边元素
            }else if(less(temp[i],temp[j])){
                arr[k]=temp[i++];
            }else{
                arr[k]=temp[j++];
            }
        }
    }
}
