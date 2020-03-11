package chapter2_Sorting.chapter2_5_Applications.exercises;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangyu
 * 2020/3/10 18:46
 * 练习2.5.16：公正的选举
 */
public class EX_2_5_16 {
    public static void main(String[] args) {
        String[] arr={"RJ","RQ","Q","J","O"};
        sort(arr, new Comparator<String>() {
            private String standard="RWQOJ";
            @Override
            public int compare(String o1, String o2) {
                for (int i=0;i<Math.min(o1.length(),o2.length());i++){
                    char chr1=o1.charAt(i);
                    char chr2=o2.charAt(i);
                    int compareRes=standard.indexOf(chr1)-standard.indexOf(chr2);
                    if (compareRes<0){
                        return -1;
                    }else if(compareRes>0){
                        return 1;
                    }
                }
                return o1.length()-o2.length();
            }
        });
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(String[] arr, Comparator<String> comparator){
        if (arr==null||arr.length==0||comparator==null){
            return;
        }
        for (int i=1;i<arr.length;i++){
            for (int j=i;j>0&&comparator.compare(arr[j],arr[j-1])<0;j--){
                exch(arr,j,j-1);
            }
        }
    }
    private static void exch(String[] arr,int i,int j){
        String temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
