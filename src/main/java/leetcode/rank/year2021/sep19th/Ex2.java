package leetcode.rank.year2021.sep19th;

/**
 * @author yuzhang
 * @date 2021/12/19 10:33 上午
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {

    }
    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        for (int space : spaces) {
            sb.insert(space + count, ' ');
            count++;
        }
        return sb.toString();
    }
}
