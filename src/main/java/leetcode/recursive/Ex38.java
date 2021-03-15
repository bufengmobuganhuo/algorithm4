package leetcode.recursive;

/**
 * @author yuzhang
 * @date 2021/5/11 上午8:46
 * TODO
 */
public class Ex38 {
    public static void main(String[] args) {
        Ex38 ex38 = new Ex38();
        System.out.println(ex38.countAndSay(5));
    }
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String str = countAndSay(n - 1);
        return countAndSay(str);
    }

    private String countAndSay(String str) {
        int count = 1;
        char num = str.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            if (num==str.charAt(i)){
                count++;
            }else {
                sb.append(count);
                sb.append(num);
                count = 1;
                num = str.charAt(i);
            }
        }
        sb.append(count);
        sb.append(num);
        return sb.toString();
    }
}
