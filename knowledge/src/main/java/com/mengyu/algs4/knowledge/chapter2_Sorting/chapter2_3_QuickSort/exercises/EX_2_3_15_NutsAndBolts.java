package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_3_QuickSort.exercises;

import com.mengyu.algs4.utils.SortTemplate;
import com.mengyu.algs4.utils.ArrayUtil;

/**
 * @author zhangyu
 * 2020/2/17 10:20
 * 练习2.3.15：螺丝和螺母，实际上是两个具有完全相同元素，但是顺序不同数组的排序
 * 1.确定螺丝数组中的切分元素，并在螺母数组中找到相等元素
 * 2.保证上述二者在同一位置（start位置）
 * 3.对两个数组进行切分，快速排序
 */
public class EX_2_3_15_NutsAndBolts implements SortTemplate {
    public static void main(String[] args) {
        Comparable[] nuts= ArrayUtil.createInt(8,200,false);
        //Comparable[] nuts={5 ,0, 0, 3, 4, 7, 0, 1};
        Comparable[] bolts=nuts.clone();
        //Comparable[] bolts={3, 7, 1, 0, 4, 0, 0, 5};
        EX_2_3_15_NutsAndBolts ex_2_3_15_nutsAndBolts=new EX_2_3_15_NutsAndBolts();
        ex_2_3_15_nutsAndBolts.shuffle(bolts);
        ex_2_3_15_nutsAndBolts.show("匹配前nuts:",nuts);
        ex_2_3_15_nutsAndBolts.show("匹配前boltss:",bolts);
        ex_2_3_15_nutsAndBolts.solution(nuts,bolts);
        ex_2_3_15_nutsAndBolts.show("匹配后nuts:",nuts);
        ex_2_3_15_nutsAndBolts.show("匹配后boltss:",bolts);
    }
    public void solution(Comparable[] nuts,Comparable[] bolts){
        if (nuts==null||nuts.length==0||bolts==null||bolts.length==0){
            return;
        }
        shuffle(nuts);
        shuffle(bolts);
        quickSort(nuts,bolts,0,nuts.length-1);
    }
    public void quickSort(Comparable[] nuts,Comparable[] bolts,int start,int end){
        if (start>=end){
            return;
        }
        int partitionIdx=partition(nuts,bolts,start,end);
        quickSort(nuts,bolts,start,partitionIdx-1);
        quickSort(nuts,bolts,partitionIdx+1,end);
    }
    public int partition(Comparable[] nuts,Comparable[] bolts,int start,int end){
        int left=start,right=end+1;
        //螺丝中的切分元素
        Comparable partitionKey=nuts[start];
        //找到螺母中对应的切分元素，并保证螺母螺丝中切分位置相同
        for (int i=start;i<=end;i++){
            if (bolts[i].compareTo(partitionKey)==0){
                exchange(bolts,i,start);
                break;
            }
        }
        while (true){
            //从左边开始扫描，直到遇到一个元素>=切分元素
            while (less(nuts[++left],partitionKey)){
                if (left==end){
                    break;
                }
            }
            //从右边开始扫描，直到遇到一个元素<=
            while (less(partitionKey,nuts[--right])){
                if (right==start){
                    break;
                }
            }
            //如果两个指针相遇，说明切分完成，切分元素找到了应该在的位置
            if (left>=right){
                break;
            }
            //保证切分元素左边的元素不大于切分元素，右边元素不小于切分元素
            exchange(nuts,left,right);
        }
        //找到切分元素应该在的位置后，将其置于该位置
        exchange(nuts,start,right);

        //对螺母进行切分
        left=start;
        right=end+1;
        while (true){
            while (less(bolts[++left],partitionKey)){
                if (left==end){
                    break;
                }
            }
            while (less(partitionKey,bolts[--right])){
                if (right==start){
                    break;
                }
            }
            if (left>=right){
                break;
            }
            exchange(bolts,left,right);
        }
        exchange(bolts,start,right);
        return right;
    }
}
