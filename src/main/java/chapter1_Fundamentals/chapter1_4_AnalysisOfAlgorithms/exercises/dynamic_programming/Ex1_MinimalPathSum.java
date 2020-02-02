package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

import edu.princeton.cs.algs4.StdOut;

/**
 * 最小路径和问题
 */
public class Ex1_MinimalPathSum {
    public static void main(String[] args) {
        int[][] m={
                {1,3,5,9},
                {8,1,3,4},
                {5,0,6,1},
                {8,8,4,0}
        };
        StdOut.println(solution(m));
    }

    public static int solution(int[][] m){
        if (m==null||m.length==0){
            return 0;
        }
        //边界：对于边界上的点，最小路径和就是累加
        for (int i=1;i<m.length;i++){
            m[i][0]+=m[i-1][0];
        }
        for (int i=1;i<m[0].length;i++){
            m[0][i]+=m[0][i-1];
        }
        //对于m[i][j]=m[i][j]+min(m[i][j-1],m[i-1][j])
        for (int i=1;i<m.length;i++){
            for (int j=1;j<m[i].length;j++){
                m[i][j]+=Math.min(m[i][j-1],m[i-1][j]);
            }
        }
        return m[m.length-1][m[m.length-1].length-1];
    }
}
