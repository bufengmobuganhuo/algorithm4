package xingyeshujin;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2020/8/9 8:36 上午
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String a=scanner.nextLine();
            String b=scanner.nextLine();
            System.out.println((a+a).contains(b)?"True":"False");
        }
    }
}
