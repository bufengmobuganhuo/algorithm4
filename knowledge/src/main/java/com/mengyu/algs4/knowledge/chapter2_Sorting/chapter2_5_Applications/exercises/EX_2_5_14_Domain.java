package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/3/9 16:40
 * 练习2.5.14：逆域名排序
 */
public class EX_2_5_14_Domain implements Comparable<EX_2_5_14_Domain>{
    public static void main(String[] args) {

    }
    private String[] fields;
    private int length;
    public EX_2_5_14_Domain(String url){
        fields=url.split("\\.");
        length=fields.length;
    }

    @Override
    public int compareTo(EX_2_5_14_Domain o) {
        for (int i=0;i<Math.min(this.length,o.length);i++){
            String thisField=fields[this.length-i-1];
            String thatField=o.fields[o.length-i-1];
            int compareRes=thisField.compareTo(thatField);
            if (compareRes<0){
                return -1;
            }else if(compareRes>0){
                return 1;
            }
        }
        return this.length-o.length;
    }

    @Override
    public String toString() {
        if (length == 0){
            return "";
        }
        String s = fields[0];
        for (int i = 1; i < length; i++){
            s = fields[i] + "." + s;
        }
        return s;
    }
}
