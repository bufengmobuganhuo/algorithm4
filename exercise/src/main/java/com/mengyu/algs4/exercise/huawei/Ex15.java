package com.mengyu.algs4.exercise.huawei;

/**
 * @author yuzhang
 * @date 2021/3/27 上午11:03
 * TODO
 */
public class Ex15 {
    public static void main(String[] args) {
        String str = test();
        System.out.println(str);
    }

    private static String test(){
        String str = "init";
        try{
            str = "try";
        }finally {
            str = "finally";
        }
        return str;
    }

}
