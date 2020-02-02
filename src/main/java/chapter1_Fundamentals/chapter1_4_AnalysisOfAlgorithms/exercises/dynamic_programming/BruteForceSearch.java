package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

/**
 *暴力搜索方法
 */
public class BruteForceSearch {
    public static int search(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        return process1(arr,0,aim);
    }

    /**
     * arr=[5,10,25,1] aim=1000
     * 1. 用0张5元，让剩下的[10,25,1]组成剩下的1000
     * 2. 用1张5元，让剩下的[10,25,1]组成剩下的995
     * ...
     * 201. 用200张5元，让剩下的[10，25，1]组成剩下的0
     */
    public static int process1(int[] arr,int index,int aim){
        int res=0;
        if (index==arr.length){
            res=aim==0?1:0;
        }else{
            for (int i=0;i*arr[index]<=aim;i++){
                res+=process1(arr,index+1,aim-i*arr[index]);
            }
        }
        return res;
    }

}
