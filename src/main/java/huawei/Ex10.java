package huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2021/3/21 上午10:00
 * TODO
 */
public class Ex10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] count;
        while (scanner.hasNext()) {
            int c = scanner.nextInt();
            int b = scanner.nextInt();
            count = new int[b];
            int res = 0;
            for (int i = 0; i < 10; i++) {
                int a = scanner.nextInt();
                int sum = 0;
                int bit = 0;
                for (int j = 0; j < 4; j++) {
                    bit = a & 0xFF;
                    a = a >> 8;
                    sum += bit;
                }
                int mod = sum % b;
                if (mod < c){
                    count[mod]++;
                    res = Math.max(res, count[mod]);
                }
            }
            System.out.println(res);
        }
    }
}
