package chapter2_Sorting.chapter2_5_Applications.exercises;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangyu
 * 2020/3/10 19:07
 * 练习2.5.17：强制稳定
 */
public class EX_2_5_17<T extends Comparable<T>> {
    public static void main(String[] args) {
        Integer[] arr={5,7,3,4,7,3,6,3,3};
        EX_2_5_17<Integer> ex_2_5_17=new EX_2_5_17<>();
        ex_2_5_17.stableSort(arr);
    }
    public void stableSort(T[] arr){
        Wrapper[] wrappers=new Wrapper[arr.length];
        for (int i=0;i<arr.length;i++){
            wrappers[i]=new Wrapper<T>(arr[i],i);
        }
        Arrays.sort(wrappers);
        exch(wrappers,0,3);
        //排序过后，相等的元素被放在一起，对这些元素的下标排序
        for (int i=0;i<wrappers.length-1;){
            //插入排序
            while (i<wrappers.length-1&&wrappers[i].compareTo(wrappers[i+1])==0){
                for (int j=i+1;j>0&&wrappers[j].index<wrappers[j-1].index;j--){
                    if (wrappers[j].compareTo(wrappers[j-1])!=0){
                        break;
                    }
                    exch(wrappers,j,j-1);
                }
                i++;
            }
            i++;
        }
        System.out.println(Arrays.toString(wrappers));
    }

    private void exch(Wrapper[] wrappers,int i,int j){
        Wrapper temp=wrappers[i];
        wrappers[i]=wrappers[j];
        wrappers[j]=temp;
    }

    class Wrapper<WT extends Comparable> implements Comparable<Wrapper>{
        WT value;
        int index;

        public Wrapper(WT value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Wrapper o) {
            return value.compareTo(o.value);
        }

        @Override
        public String toString() {
            return "Wrapper{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }
    }
}
