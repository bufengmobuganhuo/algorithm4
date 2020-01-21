package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import chapter1_Fundamentals.utils.ArrayUtil;
import edu.princeton.cs.algs4.StdOut;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * 练习1.4.19：矩阵局部最小值
 */
public class EX_1_4_19 {
    public static void main(String[] args) {
        for (int i=0;i<10000;i++){
            //int[][] arr= ArrayUtil.createMatrix(10,10,100);
           int[][] arr={
                    {0, 12, 30, 42, 62, 95},
                    {52, 68, 82, 66, 98, 4},
                    {54, 16, 18, 36, 73, 7},
                    {72, 10, 97, 27, 11, 89},
                    {49, 51, 79, 59, 78, 81},
                    {22, 80, 31, 75, 74, 65},
            };
            try {
                int[] res= runSolution2(arr);
                if (res==null){
                    for (int j=0;j<arr.length;j++){
                        StdOut.println(Arrays.toString(arr[j]));
                    }
                    continue;
                }
                int row=res[0];
                int col=res[1];
                int up=row-1>=0?arr[row-1][col]: Integer.MAX_VALUE;
                int down=row+1<arr.length?arr[row+1][col]:Integer.MAX_VALUE;
                int left=col-1>=0?arr[row][col-1]:Integer.MAX_VALUE;
                int right=col+1<arr[row].length?arr[row][col+1]:Integer.MAX_VALUE;
                if(!(arr[row][col]<up&&arr[row][col]<down&&arr[row][col]<left&&arr[row][col]<right)){
                    for (int j=0;j<arr.length;j++){
                        StdOut.println(Arrays.toString(arr[j]));
                    }
                    StdOut.println("res:"+Arrays.toString(res));
                    continue;
                }
            }catch (StackOverflowError r){
                for (int j=0;j<arr.length;j++){
                    StdOut.println(Arrays.toString(arr[j]));
                }
                StdOut.println("------------");
            }

        }

    }
    private static int[] runSolution2(int[][] arr){
        if (arr==null||arr[0].length<1){
            return null;
        }
        return solution2(arr,0,arr.length-1,0,arr[0].length-1);
    }
    /**
     * 解法二：O(n)
     * 仍然基于以下事实：对于一个具有不同整数的数组，其局部最小值一定存在
     * 将矩阵用坐标轴分成四个象限：
     * 1.在坐标轴上寻找最小值，判断是否符合条件，符合则返回
     * 2.如果这个最小值不符合条件：
     *  在较小一半的象限内重复上述过程
     */
    private static int[] solution2(int[][] arr,int rowStart,int rowEnd,int colStart,int colEnd){
        int midCol=(colStart+colEnd)/2;
        int midRow=(rowStart+rowEnd)/2;
        int[] minIdx=findLocalMinimum(arr,midCol,rowStart,rowEnd,midRow,colStart,colEnd);
        int minRow=minIdx[0];
        int minCol=minIdx[1];
        int item=arr[minRow][minCol];
        int up=minRow-1>=0?arr[minRow-1][minCol]:Integer.MAX_VALUE;
        int down=minRow+1<arr.length?arr[minRow+1][minCol]:Integer.MAX_VALUE;
        int left=minCol-1>=0?arr[minRow][minCol-1]:Integer.MAX_VALUE;
        int right=minCol+1<arr[minRow].length?arr[minRow][minCol+1]:Integer.MAX_VALUE;
        if (item<Math.min(Math.min(up,down),Math.min(left,right))){
            return new int[]{minRow,minCol};
        }
        if(item>up){
            //向矩阵的上半部分查找，但是应当考虑是左上角还是右上角
            return minCol>midCol?solution2(arr,rowStart,midRow-1,midCol+1,colEnd):solution2(arr,rowStart,midRow-1,colStart,midCol-1);
        }
        if (item>down){
            return minCol>midCol?solution2(arr,midRow+1,rowEnd,midCol+1,colEnd):solution2(arr,midRow+1,rowEnd,colStart,midCol-1);
        }
        if (item>left){
            return minRow>midRow?solution2(arr,midRow+1,rowEnd,colStart,midCol-1):solution2(arr,rowStart,midRow-1,colStart,midCol-1);
        }
        if (item>right){
            return minRow>midRow?solution2(arr,midRow+1,rowEnd,midCol+1,colEnd):solution2(arr,rowStart,midRow-1,colStart,midCol-1);
        }
        return null;
    }

    private static int[] findLocalMinimum(int[][] arr,int colIdx, int rowStart,int rowEnd,int rowIdx,int colStart,int colEnd){
        int[] res=new int[2];
        int temp=Integer.MAX_VALUE;
        for (int i=rowStart;i<=rowEnd;i++){
            if (temp>arr[i][colIdx]){
                temp=arr[i][colIdx];
                res[0]=i;
                res[1]=colIdx;
            }
        }
        for (int i=colStart;i<=colEnd;i++){
            if (temp>arr[rowIdx][i]){
                temp=arr[rowIdx][i];
                res[0]=rowIdx;
                res[1]=i;
            }
        }
        return res;
    }

    private static int[] runSolution1(int[][] arr){
        if (arr==null||arr[0].length<1){
            return null;
        }
        return solution1(arr,0,arr[0].length-1);
    }

    /**
     * https://www.jianshu.com/p/b4f5cb071f04
     * 解法一：复杂度：nlgn
     * 1. 从矩阵中间列中找到最小值，再看这个值是否符合左右条件（均小于左右的值），符合返回
     * 2. 如果不是，有两种情况
     * （此时他已经满足了上下条件，那么只需要满足左右条件即可，此时就变成了在一个一维数组中查找局部最小值）：
     *  （1）/（他左边的数<他）:从左半边递归查找（此时右边已经满足）
     *  （2）\（他左边的数>他>他右边的数）：从右半边递归查找
     *  （3）/\：不可能，因为有边界条件（递归是从小到大范围，更小范围时的边界条件已经判断过这种情况了，如果边界条件满足，已经返回了）
     */
    protected static int[] solution1(int[][] arr, int startCol, int endCol){
        int minRow=findLocalMinimumCol(arr,startCol,endCol);
        int up=minRow-1>=0?arr[minRow-1][startCol]:Integer.MAX_VALUE;
        int down=minRow+1<arr.length?arr[minRow+1][startCol]:Integer.MAX_VALUE;
        int right=startCol+1<arr[minRow].length?arr[minRow][startCol+1]:Integer.MAX_VALUE;
        int item=arr[minRow][startCol];
        if (item<right&&item<up&&item<down){
            return new int[]{minRow,startCol} ;
        }
        up=minRow-1>=0?arr[minRow-1][endCol]:Integer.MAX_VALUE;
        down=minRow+1<arr.length?arr[minRow+1][endCol]:Integer.MAX_VALUE;
        int left=endCol-1>=0?arr[minRow][endCol-1]:Integer.MAX_VALUE;
        item=arr[minRow][endCol];
        if (item<left&&item<up&&item<down){
            return new int[]{minRow,endCol};
        }
        int mid=(startCol+endCol)/2;
        left=mid-1>=0?arr[minRow][mid-1]:Integer.MAX_VALUE;
        right=mid+1<arr[minRow].length?arr[minRow][mid+1]:Integer.MAX_VALUE;
        if (arr[minRow][mid]<left&&arr[minRow][mid]<right){
            return new int[]{minRow,mid};
        }
        if (left<arr[minRow][mid]){
            return solution1(arr,startCol,mid-1);
        }
        if (arr[minRow][mid]>right){
            return solution1(arr,mid+1,endCol);
        }
        return null;
    }
    protected static int findLocalMinimumCol(int[][]arr,int startCol,int endCol){
        int min=Integer.MAX_VALUE;
        int minIndex=-1;
        int mid=(startCol+endCol)/2;
        for (int i=0;i<arr.length;i++){
            if (min>arr[i][mid]){
                min=arr[i][mid];
                minIndex=i;
            }
        }
        return minIndex;
    }

}
