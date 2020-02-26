package chapter2_Sorting.chapter2_1_ElementarySorts.exercises;

import chapter2_Sorting.SortTemplate;
import utils.ArrayUtil;

import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/25 11:22
 * 练习2.1.25：不需要交换的排序
 */
public class EX_2_1_25_InsertionX implements SortTemplate {
    public static void main(String[] args) {
        EX_2_1_25_InsertionX insertionX=new EX_2_1_25_InsertionX();
        Random random=new Random();
        for (int i=0;i<1000;i++){
            int length=random.nextInt(1000);
            Comparable[] arr= ArrayUtil.createInt(length,(length+1)*2);
            Comparable[] temp=arr.clone();
            insertionX.sort(arr);
            if (!insertionX.isSorted(arr)){
                insertionX.show("排序前:",temp);
            }
        }

    }
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        int exchCount=0;
        //将最小元素放到第一个位置
        for (int i=arr.length-1;i>0;i--){
            if (less(arr[i],arr[i-1])){
                exchange(arr,i,i-1);
                exchCount++;
            }
        }
        //如果没有过交换，说明数组是有序的（后一个元素总是>它的前一个元素）
        if (exchCount==0){
            return;
        }
        for (int i=2;i<arr.length;i++){
            int j=i;
            //将要排序的元素保存下来
            Comparable value=arr[i];
            while (j>0&&less(value,arr[j-1])){
                //如果要排序的元素<前一个元素，则将较大元素（前一个元素）右移
                arr[j]=arr[j-1];
                j--;
            }
            //j保存了要排序元素应该在的位置
            arr[j]=value;
        }
    }
}
