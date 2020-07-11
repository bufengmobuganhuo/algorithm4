package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2020/7/8 11:40 上午
 * TODO
 */
public class Ex1137 {
    private int[] num;
    public int tribonacci(int n) {
        num=new int[38];
        num[0]=0;
        num[1]=1;
        num[2]=1;
        return recursive(n);
    }
    private int recursive(int k){
        if (k==0){
            return 0;
        }
        if (num[k]!=0){
            return num[k];
        }
        num[k]=recursive(k-1)+recursive(k-2)+recursive(k-3);
        return num[k];
    }
}
