package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.ThreeSum;
import chapter1_Fundamentals.utils.ArrayUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 练习1.4.15 仿照 TwoSumFast，编写平方级别的3-sum（数不重复）
 */
public class EX_1_4_15_ThreeSumFast {
    public static void main(String[] args) {
        Random random=new Random();
        //生成随机数组进行测试
        for (int i=0;i<10000;i++) {
            int length = random.nextInt(200);
            if (length <= 0) {
                continue;
            }
            int[] arr = ArrayUtil.create(length, 300);
            int count1= ThreeSum.getResult(arr);
            int count2=get3SumCount(arr,0);
            if (count1!=count2){
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Sus");
    }
    public static int get3SumCount(int[] arr,int target){
        if (arr==null||arr.length==0){
            return 0;
        }
        int count=0;
        for (int i=0;i<arr.length;i++){
            //使用2-sum查找和为(target-arr[i])的个数，即为所求
            count+=get2SumCount(arr,i+1,arr.length-1,target-arr[i]);
        }
        return count;
    }
    public static int get2SumCount(int[] arr,int start,int end,int target){
        if (arr==null||arr.length==0||start>=end||start<0||end>arr.length){
            return 0;
        }
        int left=start;
        int right=end;
        int count=0;
        while (left<right){
            if (arr[left]+arr[right]==target){
                left++;
                right--;
                count++;
            }else if(arr[left]+arr[right]<target){
                left++;
            }else{
                right--;
            }
        }
        return count;
    }
}
