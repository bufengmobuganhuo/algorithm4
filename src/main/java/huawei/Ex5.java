package huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2020/8/1 9:08 上午
 * TODO
 */
public class Ex5 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            int branchNum=n/3;
            long sum=0;
            for (int i = 1; i <= branchNum; i++) {
                long generateDay=3*i;
                long leafNum= 2*(n-generateDay);
                sum+=leafNum;
            }
            System.out.println(sum);
        }
    }
}
