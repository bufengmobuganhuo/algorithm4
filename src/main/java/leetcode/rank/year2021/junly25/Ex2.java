package leetcode.rank.year2021.junly25;

/**
 * @author yuzhang
 * @date 2021/7/25 上午10:22
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] change = {0,9,2,3,3,2,5,5,5,5};
        System.out.println(ex2.maximumNumber("334111", change));
    }

    public String maximumNumber(String num, int[] change) {
        StringBuilder sb = new StringBuilder(num);
        boolean isStart = false;
        for (int i = 0; i < num.length(); i++) {
            int chr = num.charAt(i) - '0';
            if (chr < change[chr]) {
                sb.setCharAt(i, (char) (change[chr] + '0'));
                isStart = true;
            } else if (chr == change[chr] && isStart) {
            }else if (chr > change[chr] && isStart){
                break;
            }
        }
        return sb.toString();
    }
}
