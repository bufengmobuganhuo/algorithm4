package com.mengyu.algs4.exercise.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yuzhang
 * @date 2020/7/29 4:40 下午
 * 判断合法ip
 */
public class Ex4 {
    private final static String YES="YES";
    private final static String NO="NO";
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String ip="";
        while ((ip=reader.readLine())!=null){
            String[] part = ip.split("\\.");
            for (int i = 0; i < part.length; i++) {
                Integer num=Integer.parseInt(part[i]);
                if (num<=255&&num>=0){
                    if (i==part.length-1){
                        System.out.println(YES);
                    }
                }
                else{
                    System.out.println(NO);
                    break;
                }
            }
        }
    }
}
