package chapter2_Sorting.chapter2_2_MergeSort.exercises;

import chapter2_Sorting.chapter2_2_MergeSort.MergeFromBottomToTop;
import com.sun.media.sound.RIFFInvalidDataException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/2/14 11:53
 * 练习2.2.21：一式三份
 */
public class EX_2_2_21 {
    public static void main(String[] args) {
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new ArrayList<>();
        Random random=new Random();
        for (int i=0;i<10;i++){
            int chr=97+random.nextInt(25);
            list1.add(Character.toString((char)chr));
            chr=97+random.nextInt(25);
            list2.add(Character.toString((char)chr));
            chr=97+random.nextInt(25);
            list3.add(Character.toString((char)chr));
        }
        int chr=97+random.nextInt(25);
        list1.add(Character.toString((char)chr));
        list2.add(Character.toString((char)chr));
        list3.add(Character.toString((char)chr));
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(solution(list1,list2,list3));
    }
    public static String solution(List<String> list1,List<String> list2,List<String> list3){
        if (list1==null||list2==null||list3==null){
            return null;
        }
        MergeFromBottomToTop mergeFromBottomToTop=new MergeFromBottomToTop();
        Comparable[] arr1=new Comparable[list1.size()];
        mergeFromBottomToTop.sort(list1.toArray(arr1));
        Comparable[] arr2=new Comparable[list2.size()];
        mergeFromBottomToTop.sort(list2.toArray(arr2));
        Comparable[] arr3=new Comparable[list3.size()];
        mergeFromBottomToTop.sort(list3.toArray(arr3));
        for (Comparable word:arr1){
            if (binarySearch(arr2,word,0,arr2.length-1)&&binarySearch(arr3,word,0,arr2.length-1)){
                return (String) word;
            }

        }
        return null;
    }
    public static boolean binarySearch(Comparable[] arr, Comparable key,int start,int end){
        int mid=start+(end-start)/2;
        while (start<=end){
            Comparable word=arr[mid];
            if (key.compareTo(word)==0){
                return true;
            }else if(key.compareTo(word)>0){
                start=mid+1;
                return binarySearch(arr,key,start,end);
            }else{
                end=mid-1;
                return binarySearch(arr,key,start,end);
            }
        }
        return false;
    }
}
