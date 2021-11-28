package huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2020/8/1 9:56 上午
 * TODO
 */
public class Ex7 {
    private static final int THRESHOLD=500;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String fileSize=scanner.nextLine();
            String[] filesSplit=fileSize.split(",");
            int[] files=new int[filesSplit.length];
            for (int i = 0; i < files.length; i++) {
                files[i]=Integer.parseInt(filesSplit[i]);
            }
            Arrays.sort(files);
            int count=0;
            int left=0,right=files.length;
            while(left<right){
                while (files[--right]>=THRESHOLD){
                    count++;
                    if (right<left){
                        break;
                    }
                }
                if (right<left){
                    break;
                }
                int sum=files[right];
                while(sum<500){
                    if (left>=right){
                        break;
                    }
                    sum+=files[left++];
                }
                count++;
                if (sum>500){
                    left--;
                }
                if (left>=right){
                    break;
                }
            }
            System.out.println(count);
        }
    }
}
