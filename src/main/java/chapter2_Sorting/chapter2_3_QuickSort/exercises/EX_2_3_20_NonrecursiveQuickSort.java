package chapter2_Sorting.chapter2_3_QuickSort.exercises;

import chapter2_Sorting.SortTemplate;
import javafx.scene.chart.StackedAreaChart;
import utils.ArrayUtil;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/2/18 16:48
 * 练习2.3.20：非递归的快速排序
 */
public class EX_2_3_20_NonrecursiveQuickSort implements SortTemplate {
    public static void main(String[] args) {
        Comparable[] arr= ArrayUtil.createInt(8,15);
        EX_2_3_20_NonrecursiveQuickSort ex_2_3_20_nonrecursiveQuickSort=new EX_2_3_20_NonrecursiveQuickSort();
        ex_2_3_20_nonrecursiveQuickSort.show("排序前:",arr);
        ex_2_3_20_nonrecursiveQuickSort.sort(arr);
        ex_2_3_20_nonrecursiveQuickSort.show("排序后:",arr);
    }
    @Override
    public void sort(Comparable[] arr) {
        if (arr==null||arr.length==0){
            return;
        }
        shuffle(arr);
        Stack<Integer> stack=new Stack<>();
        stack.push(0);
        stack.push(arr.length-1);
        while (stack.size()>1){
            int end=stack.pop();
            int start=stack.pop();
            if (start>=end){
                continue;
            }
            int partitionIdx=partition(arr,start,end);
            if (end-(partitionIdx+1)>(partitionIdx-1)-start){
                stack.push(partitionIdx+1);
                stack.push(end);
                stack.push(start);
                stack.push(partitionIdx-1);
            }else{
                stack.push(start);
                stack.push(partitionIdx-1);
                stack.push(partitionIdx+1);
                stack.push(end);
            }
        }
    }
    private int partition(Comparable[] arr,int start,int end){
        int left=start,right=end+1;
        Comparable partitionKey=arr[start];
        while (true){
            while (less(arr[++left],partitionKey)){
                if (left==end){
                    break;
                }
            }
            while (less(partitionKey,arr[--right])){};
            if (left>=right){
                break;
            }
            exchange(arr,left, right);
        }
        exchange(arr,start,right);
        return right;
    }
}
