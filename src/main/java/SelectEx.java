import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/9/29 10:46 上午
 * TODO
 */
public class SelectEx {
    public static void main(String[] args) {
        System.out.println(witchOne(14));
        System.out.println(witchOne(16));
    }
    private static int witchOne(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}
