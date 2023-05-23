package com.mengyu.algs4.exercise.huawei;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/1 9:23 上午
 * TODO
 */
public class Ex6 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int N=scanner.nextInt();
            int[][] matrix=new int[N][N];
            // 每行和 -> 个数
            Map<Integer,LinkedList<Integer>> row=new HashMap<>();
            for (int i = 0; i < N; i++) {
                int tmp=0;
                for (int j = 0; j < N; j++) {
                    matrix[i][j]=scanner.nextInt();
                    tmp+=matrix[i][j];
                }
                LinkedList<Integer> list=row.getOrDefault(tmp,new LinkedList<>());
                list.offer(i);
                row.put(tmp,list);
            }
            Map<Integer, LinkedList<Integer>> col=new HashMap<>();
            for (int i = 0; i < N; i++) {
                int tmp=0;
                for (int j = 0; j < N; j++) {
                  tmp+=matrix[j][i];
                }
                LinkedList<Integer> list=col.getOrDefault(tmp,new LinkedList<>());
                list.offer(i);
                col.put(tmp,list);
            }
            Integer rowNum=0;
            int realSum=0;
            for (Map.Entry<Integer,LinkedList<Integer>> entry: row.entrySet()) {
                if (entry.getValue().size()==1){
                    rowNum=entry.getValue().getFirst();
                    continue;
                }
                realSum=entry.getKey();
            }
            Integer colNum=0;
            for (Map.Entry<Integer,LinkedList<Integer>> entry: col.entrySet()) {
                if (entry.getValue().size()==1){
                    colNum=entry.getValue().getFirst();
                    break;
                }
            }
            for (int i = 0; i < N; i++) {
                if (i!=colNum){
                    realSum-=matrix[rowNum][i];
                }
            }
            System.out.println((rowNum+1)+" "+(colNum+1)+" "+realSum);
        }
    }


}
