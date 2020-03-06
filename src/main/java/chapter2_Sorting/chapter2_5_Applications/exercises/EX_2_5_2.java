package chapter2_Sorting.chapter2_5_Applications.exercises;

import utils.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangyu
 * 2020/3/5 16:24
 * 练习2.5.2：查找连接词
 * 1.将输入字符串数组words[]按照字符串长度排序，那么可能的连接词的最小长度为words[0].length*2，
 *      找到该字符串的索引mayCompoundIdx
 * 2.从mayCompoundIdx开始循环查找：
 *      （1）从words[0]开始查找：word[i].length+word[j].length=word[mayCompoundIdx],转化成了2Sum问题，
 *           2Sum问题中，首先确定一个元素，然后使用二分法查找另外一个元素，但该问题含有重复元素，
 *           故需要在二分法中找到要查询元素的最小索引
 *      （2）找到索引后，需要判断word[i]+word[j]是否等于word[mayCompoundIdx],是算连接词
 */
public class EX_2_5_2 {
    public static void main(String[] args) {
        for (int i=0;i<10000;i++){
            String[] words= ArrayUtil.createCompoundStrs(8);
            //String[] words={"d", "dd", "jUj", "Stb", "ddd", "lUV3", "fHOz", "dfHOz"};
            List<String> res1=solution2(words);
            List<String> res2=solution1(words);

            if (!equals(res1,res2)){

                System.out.println(Arrays.toString(words));
            }
        }
    }
    public static List<String> solution1(String[] words){
        List<String> res=new ArrayList<>();
        if (words==null||words.length==0){
            return res;
        }
        //将字符串按照长度排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return new Integer(o1.length()).compareTo(o2.length());
            }
        });
        int mayCompoundIdx=0;
        //找到可能的连接词的最小长度的所在索引
        while (mayCompoundIdx<words.length&&words[mayCompoundIdx].length()<words[0].length()*2){
            mayCompoundIdx++;
        }
        while (mayCompoundIdx<words.length){
            int sumLength=words[mayCompoundIdx].length();
            //2 sum问题，首先确定一个数，然后使用二分法查找另一个数的索引
            for (int i=0;i<mayCompoundIdx;i++){
                //找到被查找元素的最小索引
                int start=binarySearch(words,sumLength-words[i].length(),i+1,mayCompoundIdx);
                if (start!=-1){
                    while (words[start].length()+words[i].length()==sumLength){
                        //可能会有两种组合顺序
                        if (words[mayCompoundIdx].equals( words[start]+words[i])){
                            res.add(words[mayCompoundIdx]);
                        }else if(words[mayCompoundIdx].equals( words[i]+words[start])){
                            res.add(words[mayCompoundIdx]);
                        }
                        start++;
                    }
                }
            }
            mayCompoundIdx++;
        }
        return res;
    }
    private static int binarySearch(String[] words,int targetLength,int start,int end){
        while (start<=end){
            int mid=start+(end-start)/2;
            if (words[mid].length()==targetLength){
                //因为可能有重复元素，故找到被查找元素的最小索引
                while (mid>=start&&words[mid].length()==targetLength){
                    mid--;
                }
                return mid+1;
            }else if(words[mid].length()<targetLength){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return -1;
    }

    /**
     * 暴力解法
     * @param words
     * @return
     */
    public static List<String> solution2(String[] words){
        List<String> res=new ArrayList<>();
        if (words==null||words.length==0){
            return res;
        }
        for (int i=0;i<words.length;i++){
            for (int j=i+1;j<words.length;j++){
                findStr(words,words[i]+words[j],res);
                if (!(words[i]+words[j]).equals(words[j]+words[i])){
                    findStr(words,words[j]+words[i],res);
                }
            }
        }
        return res;
    }
    private static void findStr(String[] words,String str,List<String> res){
        for (int i=0;i<words.length;i++){
            if (words[i].equals(str)){
                res.add(words[i]);
            }
        }
    }
    public static boolean equals(List<String> list1,List<String> list2){
        if (list1.size()!=list2.size()){
            return false;
        }
        for (String item:list1){
            if (!list2.contains(item)){
                return false;
            }
        }
        return true;
    }
}
