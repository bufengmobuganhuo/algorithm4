package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import chapter2_Sorting.SortTemplate;

/**
 * @author zhangyu
 * 2020/2/10 16:40
 * 练习2.2.11：改进归并排序算法，只改进了前两项
 */
public class EX_2_2_11_ImproveMerge implements SortTemplate {
    private int CUTOFF=15;
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        Comparable[] dst =arr.clone();
        sort(dst,arr,0,arr.length-1);
    }

    private void sort(Comparable[] src,Comparable[] dst,int left,int right){
        //改进一：对于小数组，使用插入排序
        if (right<=left+CUTOFF){
            insertionSort(dst,left,right);
            return;
        }
        int mid=(left+right)/2;
        //此处交换了位置，对src内的小数组选择排序
        sort(dst,src,left,mid);
        sort(dst,src,mid+1,right);
        //改进二：如果src[mid]<=src[mid+1]则跳过归并过程，（src[mid]前后的两个数组都是有序的）此时递归调用不受影响，
        // 任意有序的子数组算法的运行时间变为线性
        if (!less(src[mid+1],src[mid])){
            //将src的数据保存下来，以便下一次归并
            System.arraycopy(src,left,dst,left,right-left+1);
            return;
        }
        //src中是已经小数组排好序的，dst是小数组排序之前的，所以要将src归并到dst
        merge(src,dst, left,mid,right);
    }


    public void merge(Comparable[] src,Comparable[] dst,int left,int mid,int right){
        int i=left,j=mid+1;
        for (int k=left;k<=right;k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > right) {
                dst[k] = src[i++];
            } else if (less(src[i], src[j])) {
                dst[k] = src[i++];
            } else {
                dst[k] = src[j++];
            }
        }
    }
    //插入排序
    public void insertionSort(Comparable[] arr,int left,int right){
        for (int i=left+1;i<=right;i++){
            for (int j=i;j>left&&less(arr[j],arr[j-1]);j--){
                exchange(arr,j,j-1);
            }
        }
    }

}
